package lesson22.homeTasks;

//    Сделать пиццерию на очередях:
//        клиенты складывают заказы в очередь 1, официанты из нее заказы забирают и добавляют в очередь 2,
//        из которой повар заказ забирает и готовит, после чего он готовые блюда складывает в очередь 3,
//        из который клиенты будут забирать заказы. Сами же клиенты блокируются на очереди 3,
//        официанты на очереди 1, а повара - на очереди 2.


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PizzeriaBlocking {

    private List<Thread> listThread = new ArrayList<>();

    BlockingQueue<DishClient> orders = new ArrayBlockingQueue(3, true);
    BlockingQueue<DishClient> dishesToCooking = new ArrayBlockingQueue(3, true);
    BlockingQueue<DishClient> closedOrders = new ArrayBlockingQueue(3, true);

    Integer clientCount = 0;
    boolean flagClose = false;

    public void start(){
        for (Thread thread : listThread)
            thread.start();
    }

    public void close(){

        flagClose = true;

        boolean someoneThere = true;

        while (someoneThere) {
            for (Thread thread : listThread) {
                thread.interrupt();
            }

            someoneThere = false;
            for (Thread thread : listThread) {
                someoneThere |= thread.isAlive();
            }

            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addThread(Thread thread){
        listThread.add(thread);
    }

    public static void main(String[] args) {

        PizzeriaBlocking pizzeria = new PizzeriaBlocking();
        pizzeria.addThread(new Thread(new Cooker("Повар1", pizzeria)));
        pizzeria.addThread(new Thread(new Cooker("Повар1", pizzeria)));
        pizzeria.addThread(new Thread(new Cooker("Повар2", pizzeria)));
        pizzeria.addThread(new Thread(new Waiter("Официант1", pizzeria)));

        Client client1 = new Client("Клиент1", pizzeria);
        client1.addDish(new Dish("Блюдо1", 100));
        client1.addDish(new Dish("Блюдо2", 500));
        client1.addDish(new Dish("Блюдо3", 200));
        Client client2 = new Client("Клиент2", pizzeria);
        client2.addDish(new Dish("Блюдо1", 100));

        pizzeria.addThread(new Thread(client1));
        pizzeria.addThread(new Thread(client2));

        pizzeria.start();

        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pizzeria.close();

    }
}


class Client extends Thread{
    String name;
    PizzeriaBlocking pizza;

    List<Dish> dishes = new ArrayList<>();
    List<DishClient> waitDishes = new ArrayList<>();

    public Client(String name, PizzeriaBlocking pizza) {
        this.name = name;
        this.pizza = pizza;
    }

    public void addDish(Dish dish){
        this.dishes.add(dish);
    }

    @Override
    public void run() {

        if (!pizza.flagClose) {
            synchronized (pizza.clientCount) {
                pizza.clientCount++;
            }
        } else return;

        int countOrders = 0;
        while (true) {
            try {
                for (int i = 0; i < dishes.size(); i++) {
                    DishClient dishClient = new DishClient(dishes.get(i), this);
                    pizza.orders.add(dishClient);
                    waitDishes.add(dishClient);
                    countOrders++;
                    System.out.println(name + " сделал заказ " + dishes.get(i).name);
                }
            } catch (IllegalStateException e) {
//                e.printStackTrace();   // очередь заказов полна
            }

            do {
                for (int i = 0; i < waitDishes.size(); i++) {
                    try {
                        if (pizza.closedOrders.remove(waitDishes.get(i))) {
                            System.out.println(name + " получил готовый заказ " + waitDishes.get(i).dish.name);
                            waitDishes.remove(waitDishes.get(i--));
                        }
                    } catch (IllegalStateException e) {
//                                e.printStackTrace();   // нет нужного готового заказа
                    }
                }
            } while (waitDishes.size() > 0 && countOrders == dishes.size());

            if (waitDishes.size() == 0 && countOrders == dishes.size()) {
                synchronized (pizza.clientCount) {
                    pizza.clientCount--;
                    break;
                }
            }
        }
    }
}


class Waiter extends Thread{
    String name;
    PizzeriaBlocking pizza;

    public Waiter(String name, PizzeriaBlocking pizza) {
        this.name = name;
        this.pizza = pizza;
    }

    @Override
    public void run() {

        while (true) {

            DishClient dishClient = null;
            try {
                dishClient = pizza.orders.take();
            } catch (InterruptedException e) {
                synchronized (pizza.clientCount) {
                    if (pizza.flagClose && pizza.clientCount == 0) {
                        System.out.println(name + " закончил работу.");
                        break;
                    } else {
                        System.out.println(name + " - работаем до последнего клиента!)");
                        continue;  // до последнего клиента)
                    }
                }
            }

            while (!pizza.dishesToCooking.offer(dishClient)) {
                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + " передал на кухню " + dishClient.dish.name);
        }
    }
}


class Cooker extends Thread{
    String name;
    PizzeriaBlocking pizza;

    public Cooker(String name, PizzeriaBlocking pizza) {
        this.name = name;
        this.pizza = pizza;
    }

    @Override
    public void run() {

        while (true) {
            DishClient dishClient = null;
            try {
                dishClient = pizza.dishesToCooking.take();
            } catch (InterruptedException e) {
                synchronized (pizza.clientCount) {
                    if (pizza.flagClose && pizza.clientCount == 0) {
                        System.out.println(name + " закончил работу.");
                        break;
                    } else {
                        System.out.println(name + " - работаем до последнего клиента!)");
                        continue;  // до последнего клиента)
                    }
                }
            }

            System.out.println(name + " начал готовку " + dishClient.dish.name);
            try {
                sleep(dishClient.dish.timeCooking);
            } catch (InterruptedException e) {
//                e.printStackTrace();   не спать, проверим еще раз)
            }

            while (!pizza.closedOrders.offer(dishClient)) {
                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + " передал клиенту " + dishClient.dish.name);
        }
    }
}


class Dish {

    String name;
    int timeCooking;

    public Dish(String name, int timeCooking) {
        this.name = name;
        this.timeCooking = (timeCooking > 0)? timeCooking : 10;
    }
}

class DishClient {
    Dish dish;
    Client client;
    private boolean cooked = false;

    public DishClient(Dish dish, Client client) {
        this.dish = dish;
        this.client = client;
    }

    public void setCooked() {
        cooked = true;
    }

    public boolean isCooked() {
        return cooked;
    }
}
