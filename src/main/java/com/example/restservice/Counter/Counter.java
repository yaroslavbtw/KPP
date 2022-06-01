package com.example.restservice.Counter;
import com.example.restservice.logger.MyLogger;

import java.util.concurrent.Semaphore;

public class Counter {
    private static int requests = 0;
    static Semaphore semaphore = new Semaphore(1, true);
    public static void inc() {
        try {
            semaphore.acquire();
            requests++;
            semaphore.release();
        } catch (InterruptedException e) {
            MyLogger.warn(Thread.currentThread().getName() + "was interrupted");
        }

    }

    public static int getRequestsCount() {
        return requests;
    }
}
