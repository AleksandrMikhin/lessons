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
import java.util.*;
import java.util.concurrent.*;

public class Bank {

    private volatile int userMaxId = 0;
    private volatile int accountMaxId = 0;

    private HashMap<String, Integer> usersIdMap = new HashMap<>();
    private HashMap<String, Integer> accountsIdMap = new HashMap<>();

    private ExecutorService executorService;
    ConcurrentHashMap<Integer, AccountInf> accountsMap = new ConcurrentHashMap<>();
    Vector<TransferMoney> transfers = new Vector<>();


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

    public void transfer(String userName, String accountSrc, String accountDst, int amount) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                StringBuilder res = new StringBuilder("Перевод суммы " + amount + " от клиента " + userName + " с " + accountSrc + " на " + accountDst + ": ");

                if (usersIdMap.get(userName) == null) res.append("Клиент " + userName + " не найден!");
                else if (accountsIdMap.get(accountSrc) == null) res.append("Аккаунт " + accountSrc + " не найден!");
                else if (accountsIdMap.get(accountDst) == null) res.append("Аккаунт " + accountDst + " не найден!");
                else if (accountsMap.get(accountsIdMap.get(accountSrc)).userId != usersIdMap.get(userName).intValue())
                    res.append("Пользователю " + userName + " не принадлежит аккаунт " + accountSrc);
                else if (accountSrc.equals(accountDst)) res.append("Аккаунты не могут быть равны!");
                else {
                    int src = accountsIdMap.get(accountSrc);
                    int dst = accountsIdMap.get(accountDst);

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

                                try {
                                    Thread.sleep(new Random().nextInt(1000));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                res.append("Ok!");
                                return new AccountInf(value.userId, value.balance - amount2);
                            } else {
                                res.append("Недостаточно средств!");
                                throw new TransferDenided(); //недостаточно средств
                            }
                        });
                        transfers.add(new TransferMoney(new Date(), accountSrc, accountDst, amount, true));
                        System.out.println(res);
                        return;
                    } catch (TransferDenided e) {}
                }
                transfers.add(new TransferMoney(new Date(), accountSrc, accountDst, amount, false));
                System.out.println(res);
            }
        });
    }


    public void start(){
        executorService = new ThreadPoolExecutor(
                1, 5,
                30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5));
    }

    public void stop(){
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n--- List transfers ---");
        for (TransferMoney transfer : transfers) {
            System.out.println(transfer);
        }
        System.out.println();
    }



    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount("User1", "Account1", 100);
        bank.addAccount("User1", "Account2", 300);
        bank.addAccount("User3", "Account3", 50);
        bank.addAccount("User2", "Account4", 25);
        bank.listAllBalance();

        bank.start();

        bank.transfer("User1", "Account7", "Account3", 125);
        bank.transfer("User1", "Account1", "Account2", 255);
        bank.transfer("User1", "Account2", "Account3", 125);
        bank.transfer("User2", "Account4", "Account1", 20);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bank.transfer("User3", "Account3", "Account2", 125);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bank.stop();
        bank.listAllBalance();

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

    class TransferDenided extends RuntimeException{}
}

