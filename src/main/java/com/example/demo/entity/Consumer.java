package com.example.demo.entity;

import java.util.concurrent.BlockingDeque;

public class Consumer implements Runnable{

    private final BlockingDeque blockingDeque;

    public Consumer(BlockingDeque blockingDeque){
        this.blockingDeque = blockingDeque;
    }
    @Override
    public void run() {
        int i = 100;
        while(i == 0){
            i--;
            try{
                blockingDeque.take();
            }catch (IllegalStateException | InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("生产者生产了第"+i+"件产品！");
        }
    }
}
