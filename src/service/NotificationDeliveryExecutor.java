package service;

import model.DeliveryResult;

import java.util.concurrent.*;

public class NotificationDeliveryExecutor {
    private final ThreadPoolExecutor executor;

    public NotificationDeliveryExecutor(int corePoolSize, int maxPoolSize, int capacity){
        if(corePoolSize<=0){
            throw new IllegalArgumentException("Core pool size cannot be <=0");
        }
        if(corePoolSize>maxPoolSize){
            throw new IllegalArgumentException("Core pool size cannot be >maxPoolSize");
        }
        if(capacity <= 0) {
            throw new IllegalArgumentException(
                    "Queue capacity must be greater than zero"
            );
        }
        this.executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,60L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(capacity), new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     *
     task.call() executes your existing task and returns its DeliveryResult.
     supplyAsync(..., executor) schedules the task on your existing ThreadPoolExecutor,
     preserving its bounded queue, thread limits, and rejection policy.
     It does not use the common pool.
     */
    public CompletableFuture<DeliveryResult> submit(Callable<DeliveryResult> task){
        if(task==null){
            throw new IllegalArgumentException("Task cannot be null");
        }
      return CompletableFuture.supplyAsync(()->{
        try {
            return task.call();
        }catch (Exception exception){
            throw new CompletionException(exception);
        }
      },executor);
    }

    public void shutdown(){
        executor.shutdown();
    }
}
