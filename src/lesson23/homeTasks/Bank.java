package lesson23.homeTasks;

//        User {id, name}
//        Account {id, idUser, amount}
//        БанкДанных {Список аккаунтов}
//           + методПеревода(откуда, куда, сколько)
//             откуда!=куда + достаточноСредств
//
//        transferMoney {
//            date,
//            src,
//            dist,
//            amount}

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Bank {

    private volatile int userMaxId = 0;
    private volatile int accountMaxId = 0;

    private HashMap<String, Integer> usersIdMap = new HashMap<>();
    private HashMap<String, Integer> accountsIdMap = new HashMap<>();

    private ExecutorService executorService;
    ConcurrentHashMap<Integer, AccountInf> accountsMap = new ConcurrentHashMap<>();
    ArrayList<TransferMoney> transfers = new ArrayList<>();

    public boolean addAccount(String userName, String accountName, int balance) {
        int userId;
        if (usersIdMap.containsKey(userName)) {
            userId = usersIdMap.get(userName);
        } else {
            usersIdMap.put(userName, ++userMaxId);
            userId = usersIdMap.get(userName);
        }
        if (accountsIdMap.containsKey(accountName)) return false;

        accountsIdMap.put(accountName, ++accountMaxId);
        accountsMap.put(accountsIdMap.get(accountName), new AccountInf(userId, balance));
        return true;
    }

    public void listAllBalance(){
        System.out.println("------ Balance -------");
        for (Map.Entry<String, Integer> entry : accountsIdMap.entrySet())
        {
            System.out.println(entry.getKey() + ": " + accountsMap.get(entry.getValue()).balance);
        }
        System.out.println("----------------------");

    }

    public boolean transfer(String userName, String accountSrc, String accountDst, int amount) {

        boolean res = false;
        System.out.println("Перевод суммы " + amount + " от клиента " + userName + " с " + accountSrc + " на " + accountDst);
        if (usersIdMap.get(userName) == null) {
            System.out.println("Клиент " + userName + " не найден!");
        } else
            if (accountsIdMap.get(accountSrc) == null) {
                System.out.println("Аккаунт " + accountSrc + " не найден!");
            } else
                if (accountsIdMap.get(accountDst) == null) {
                    System.out.println("Аккаунт " + accountDst + " не найден!");
                } else
                    if (accountsMap.get(accountsIdMap.get(accountSrc)).userId != usersIdMap.get(userName).intValue()) {
                        System.out.println("Пользователю " + userName + " не принадлежит аккаунт " + accountSrc);
                    } else
                        if (accountSrc.equals(accountDst)) {
                            System.out.println("Аккаунты не могут быть равны!");
                        } else
                            if (!transferSum(accountsIdMap.get(accountSrc), accountsIdMap.get(accountDst), amount )) {
                                System.out.println("Операция отклонена: на аккаунте " + accountSrc + " недостаточно средств!");
                            } else {
                                res = true;
                                System.out.println("Перевод осуществлен.");
                            }

        transfers.add(new TransferMoney(new Date(), accountSrc, accountDst, amount, res));
        System.out.println();
        return res;
    }

    public boolean transferSum(int src, int dst, int amount) {

        Future<Boolean> future = executorService.submit(new Callable<Boolean>() {
            public Boolean call() {

                int accSrc, accDst, amount2;
                if (src < dst) {
                    accSrc = src;
                    accDst = dst;
                    amount2 = amount;
                } else {
                    accSrc = dst;
                    accDst = src;
                    amount2 = -amount;
                }

                try {
                    accountsMap.computeIfPresent(accSrc, (key, value) -> {
                        if (value.balance >= amount2) {
                            accountsMap.computeIfPresent(accDst, (keyDst, valueDst) ->
                                    new AccountInf(valueDst.userId, valueDst.balance + amount2));
                            return new AccountInf(value.userId, value.balance - amount2);
                        } else
                            throw new NotEnoughMoney(); //недостаточно средств
                    });
                } catch (NotEnoughMoney e) {
                    return false;
                }

            return true;
            }
        });

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void start(){
        executorService = new ThreadPoolExecutor(
                1, 5,
                30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5));
    }

    public void stop(){
        executorService.shutdown();

        System.out.println("\n--- List transfers ---");
        for (TransferMoney transfer : transfers) {
            System.out.println(transfer);
        }
    }



    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount("User1", "Account1", 125);
        bank.addAccount("User1", "Account2", 325);
        bank.addAccount("User2", "Account1", 25);
        bank.addAccount("User3", "Account3", 25);
        bank.listAllBalance();

        bank.start();

        bank.transfer("User1", "Account7", "Account3", 125);
        bank.transfer("User1", "Account1", "Account2", 255);
        bank.listAllBalance();

        bank.transfer("User1", "Account2", "Account3", 125);
        try {
            Thread.currentThread().sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bank.listAllBalance();

        bank.transfer("User3", "Account3", "Account2", 125);
        bank.listAllBalance();

        bank.stop();
    }






    class AccountInf {
        int userId;
        int balance;

        public AccountInf(int userId, int balance) {
            this.userId = userId;
            this.balance = balance;
        }
     }

    class TransferMoney {
        Date date;
        String src;
        String dist;
        int amount;
        boolean isDone;

        @Override
        public String toString() {
            return "Transfer{ " +
                    new SimpleDateFormat("yyy.MM.dd hh:mm:ss.SSS").format(date) +
                    " : src='" + src + '\'' +
                    ", dist='" + dist + '\'' +
                    ", amount=" + amount +
                    ", isDone=" + isDone +
                    " }";
        }

        public TransferMoney(Date date, String src, String dist, int amount, boolean isDone) {
            this.date = date;
            this.src = src;
            this.dist = dist;
            this.amount = amount;
            this.isDone = isDone;
        }
    }
}


class NotEnoughMoney extends RuntimeException{}