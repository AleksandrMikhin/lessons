package lesson23;

import java.util.concurrent.*;

public class Tst {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> res = service.submit(new SomeClass());
        try {
            Integer integer = res.get(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            res.cancel(true);
            e.printStackTrace();
        }

        service.shutdown();
        service.awaitTermination(2, TimeUnit.SECONDS);
    }



    public static class SomeClass implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            return 123;
        }
    }
}
