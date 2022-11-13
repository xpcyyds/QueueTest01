package com.example.demo.entity;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    private final BlockingQueue Queue;

    public Producer(BlockingQueue blockingQueue){
        Queue = blockingQueue;
    }

    @Override
    public void run() {
        int i = 100;
        while(i == 0){
            i--;
            try{
                Queue.add(i);
            }catch (IllegalStateException e){
                e.printStackTrace();
            }
            System.out.println("生产者生产了第"+i+"件产品！");
        }
    }
}
