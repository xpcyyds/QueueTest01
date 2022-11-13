package com.example.demo.Config;

import com.example.demo.Dao.GoodsDao;
import com.example.demo.Service.GoodsService;
import com.example.demo.Service.impl.GoodsServiceImpl;
import com.example.demo.Utils.BeanUtils;
import com.example.demo.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class QueueManager implements Runnable{
    @Autowired
    GoodsService goodsService;
    /**
     * 订单的缓冲队列
     */
    BlockingQueue msgQueue = new ArrayBlockingQueue(10000);

    private Goods good;

    public QueueManager(){
        // 将任务对象传递给线程执行
        Thread thread = new Thread(this::run,"线程1");
        System.out.println(goodsService);
        // 开启线程
        thread.start();
    }

    //向队列添加数据
    public void addQueue(Goods goods){
        msgQueue.add(goods);
        System.out.println("成功插入一次数据");
    }

    //从队列中取出数据
    public Goods takeQueue() throws InterruptedException {
        Goods goods = new Goods();
        goods = (Goods) msgQueue.take();
        System.out.println("当前线程数量"+Thread.activeCount());
        return goods;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(goodsService);
//            while (!msgQueue.isEmpty()) {
            System.out.println("线程进入队列");
            try {
                good = takeQueue();
                System.out.println("成功释放一次数据");
                goodsService.saveGoods(good);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程阻塞");
        }
    }

    public BlockingQueue getMsgQueue() {
        return msgQueue;
    }

    public void setMsgQueue(BlockingQueue msgQueue) {
        this.msgQueue = msgQueue;
    }
    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }
}
