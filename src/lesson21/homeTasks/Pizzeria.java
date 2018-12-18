package lesson21.homeTasks;

//  Пиццерия.
//  Есть клиент (main), официант (waiter), повар (cooker).
//  официант и повар спят, пока не появится клиент.
//  Клиент составляет заказ и будит официанта, официант, относит заказ повару и будит его.
//  Повар готовит и возвращает блюдо. Каждый из потоков после выполнения задачи засыпает.
//
//    а) Повар просто возвращает блюдо (пишет в консоль).
//    б) Повар будит клиента и отдает ему блюдо.
//    в) Повар будит официанта и передает ему блюдо, официант будит клиента и отдает заказ.


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Pizzeria {

    Queue<DishClient> dishesToCooking = new ArrayDeque<>();

    Queue<DishClient> orders = new ArrayDeque<>();
    List<DishClient> closedOrders = new ArrayList<>();

    Integer clientCount = 0;
    Boolean flagClose = false;


    public void close(){

        synchronized(flagClose){
            flagClose = true;
        }

        synchronized (orders) {
            orders.notifyAll();
        }

        synchronized (dishesToCooking) {
            dishesToCooking.notifyAll();
        }
    }



    public static void main(String[] args) {

        Pizzeria pizzeria = new Pizzeria();
        new Thread(new Cooker("Повар1", pizzeria)).start();
        new Thread(new Cooker("Повар2", pizzeria)).start();
        new Thread(new Waiter("Официант1", pizzeria)).start();

        Client client1 = new Client("Клиент1", pizzeria);
        client1.addDish(new Dish("Блюдо1", 100));
        client1.addDish(new Dish("Блюдо2", 500));
        client1.addDish(new Dish("Блюдо3", 200));
        Client client2 = new Client("Клиент2", pizzeria);
        client2.addDish(new Dish("Блюдо1", 100));

        new Thread(client1).start();
        new Thread(client2).start();

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pizzeria.close();

    }
}


class Client extends Thread{
    String name;
    Pizzeria pizza;

    List<Dish> dishes = new ArrayList<>();
    List<DishClient> waitDishes = new ArrayList<>();

    public Client(String name, Pizzeria pizza) {
        this.name = name;
        this.pizza = pizza;
    }

    public void addDish(Dish dish){
        this.dishes.add(dish);
    }

    @Override
    public void run() {

        synchronized(pizza.flagClose){
            if (!pizza.flagClose) {
                synchronized (pizza.clientCount) {pizza.clientCount++;}
            }
            else return;
        }

        for (int i = 0; i < dishes.size(); i++) {
            DishClient dishClient = new DishClient(dishes.get(i), this);
            synchronized (pizza.orders)
            {
                pizza.orders.add(dishClient);
                waitDishes.add(dishClient);
                System.out.println(name + " сделал заказ " + dishes.get(i).name);
                pizza.orders.notifyAll();
            }
        }


        while(waitDishes.size() > 0){
            synchronized (pizza.closedOrders) {
                for (int i = 0; i < waitDishes.size(); i++) {
                    if (pizza.closedOrders.indexOf(waitDishes.get(i)) >= 0) {
                        pizza.closedOrders.remove(waitDishes.get(i));

                        System.out.println(name + " получил готовый заказ " + dishes.get(i).name);
                        waitDishes.remove(waitDishes.get(i--));
                    }
                }

                if (waitDishes.size() == 0) {
                    synchronized (pizza.clientCount) {pizza.clientCount--;}
                    break;
                }
                try {
                    System.out.println(name + " уснул.");
                    pizza.closedOrders.wait();
                    System.out.println(name + " проснулся.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Waiter extends Thread{
    String name;
    Pizzeria pizza;

    public Waiter(String name, Pizzeria pizza) {
        this.name = name;
        this.pizza = pizza;
    }

    @Override
    public void run() {

        while (true) {

            DishClient dishClient;
            synchronized (pizza.orders) {
                dishClient = pizza.orders.poll();
            }

            if (dishClient != null) {
                if (dishClient.isCooked()) {
                    System.out.println(name + " забрал с кухни " + dishClient.dish.name);
                    synchronized (pizza.closedOrders) {
                        pizza.closedOrders.add(dishClient);
                        System.out.println(name + " передал клиенту " + dishClient.dish.name);
                        pizza.closedOrders.notifyAll();
                    }
                } else {

                    System.out.println(name + " принял закал " + dishClient.dish.name);
                    synchronized (pizza.dishesToCooking) {
                        pizza.dishesToCooking.offer(dishClient);
                        System.out.println(name + " передал заказ на кухню " + dishClient.dish.name);
                        pizza.dishesToCooking.notifyAll();
                    }
                }
            } else {
                synchronized (pizza.clientCount) {
                    synchronized (pizza.flagClose) {
                        if ((pizza.clientCount == 0) && pizza.flagClose) break;
                    }
                }

                synchronized (pizza.orders) {
                    try {
                        System.out.println(name + " уснул.");
                        pizza.orders.wait();
                        System.out.println(name + " проснулся.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Cooker extends Thread{
    String name;
    Pizzeria pizza;

    public Cooker(String name, Pizzeria pizza) {
        this.name = name;
        this.pizza = pizza;
    }

    @Override
    public void run() {

        while (true) {
            DishClient dishClient;
            synchronized (pizza.dishesToCooking) {
                dishClient = pizza.dishesToCooking.poll();
            }

            if (dishClient!= null) {
                System.out.println(name + " начал готовку " + dishClient.dish.name);
                try {
                    sleep(dishClient.dish.timeCooking);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (pizza.orders) {
                    dishClient.setCooked();
                    pizza.orders.offer(dishClient);
                    System.out.println(name + " приготовил " + dishClient.dish.name);
                    pizza.orders.notifyAll();
                }

            } else {

                synchronized (pizza.clientCount) {
                    synchronized (pizza.flagClose) {
                        if ((pizza.clientCount == 0) && pizza.flagClose) break;
                    }
                }

                synchronized (pizza.dishesToCooking) {
                    try {
                        System.out.println(name + " уснул.");
                        pizza.dishesToCooking.wait();
                        System.out.println(name + " проснулся.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
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
