package myTestProject;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;

/**
 * @description: 联系Future和Callable使用
 * @author: lizheng29
 * @create: 2019-08-28 15:24
 **/
public class FutureTest {

    private static void futureTest() throws Exception {
        FutureTask<String> future = new FutureTask<>(() -> {
            System.out.println("doing...");
            Thread.sleep(5000);
            return "yeah!";
        });
        new Thread(future).start();
        while (!future.isDone()) {
            Thread.sleep(500);
            System.out.println("done :" + future.isDone());
        }
        System.out.println("result: " + future.get());
    }

    private static void completableFutureTest() throws Exception {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Integer doing ...");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        integerCompletableFuture.thenAccept(result -> System.out.println("integer done " + result));

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("String doing ...");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "nice!";
        });
        stringCompletableFuture.thenAccept(result -> System.out.println("string done " + result));


        CompletableFuture<Object> anyResult = CompletableFuture.anyOf(integerCompletableFuture, stringCompletableFuture);
        System.out.println("any:" + anyResult.get());

        CompletableFuture<Void> allResult = CompletableFuture.allOf(integerCompletableFuture, stringCompletableFuture);
        // 阻塞等待所有完成
        allResult.join();
        System.out.println("all done");

        CompletableFuture<Integer> combineResult = integerCompletableFuture.thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("inner doing");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 200;
                }),
                (result1, result2) -> result1 + result2
        );
        System.out.println("combine:" + combineResult.get());
    }

    public static void main(String[] args) throws Exception {
        futureTest();
//        completableFutureTest();
    }
}
