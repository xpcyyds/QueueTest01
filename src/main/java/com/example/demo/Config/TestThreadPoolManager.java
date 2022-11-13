//package com.example.demo.Config;
//
//import java.util.Map;
//import java.util.Queue;
//import java.util.concurrent.*;
//
//import com.example.demo.Service.BusinessThread;
//import com.example.demo.entity.Goods;
//import org.apache.naming.factory.BeanFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.BeanFactoryAware;
//import org.springframework.stereotype.Component;
//
///**
// * Created by xpc on 2022/10/29.
// */
//@Component
//public class TestThreadPoolManager  {
//
//    //用于从IOC里取对象
//    private BeanFactory factory; //如果实现Runnable的类是通过spring的application.xml文件进行注入,可通过 factory.getBean()获取，这里只是提一下
//
//    // 线程池维护线程的最少数量
//    private final static int CORE_POOL_SIZE = 5;
//    // 线程池维护线程的最大数量
//    private final static int MAX_POOL_SIZE = 10;
//    // 线程池维护线程所允许的空闲时间
//    private final static int KEEP_ALIVE_TIME = 60;
//    // 线程池所使用的缓冲队列大小
//    private final static int WORK_QUEUE_SIZE = 10000;
//
//
//    //判断是否可以直接来一个线程
//    private static boolean threadKey = false;
//
//
//    public void setBeanFactory(org.springframework.beans.factory.BeanFactory beanFactory) throws BeansException {
//    }
//    /**
//     * 用于储存在队列中的订单,防止重复提交,在真实场景中，可用redis代替 验证重复
//     */
//    Map<Goods, Object> cacheMap = new ConcurrentHashMap<Goods, Object>();
//
//
//    /**
//     * 订单的缓冲队列,当线程池满了，则将订单存入到此缓冲队列
//     */
//    Queue<Object> msgQueue = new LinkedBlockingQueue<>();
//
//
//    /**
//     * 当线程池的容量满了，执行下面代码，将订单存入到缓冲队列
//     */
////    final RejectedExecutionHandler handler = (r, executor) -> {
////        //订单加入到缓冲队列
////        msgQueue.offer(((BusinessThread) r).getAcceptStr());
////        System.out.println("系统任务太忙了,把此订单交给(调度线程池)逐一处理，订单号：" + ((BusinessThread) r).getAcceptStr());
////    };
//
//    /**创建线程池*/
//    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE));
//
//
//    /**将任务加入订单线程池*/
//    public void addOrders(Goods goods){
//        System.out.println("此订单准备添加到线程池，订单号：" + goods.getOrderId());
//
//        //验证当前进入的订单是否已经存在
//        if (cacheMap.get(goods) == null) {
//            cacheMap.put(goods,new Object());
//            int threadCount = (threadPool).getActiveCount();
//            System.out.println("当前进行的线程数量"+threadCount);
//            //判断是否可以直接进线程
//
//            //判断当前是否有空余的线程
//            /*if(threadCount >= CORE_POOL_SIZE){
//                //订单加入到缓冲队列
//                msgQueue.offer(goods);
//                System.out.println("系统任务太忙了,把此订单交给(调度线程池)逐一处理，订单号："+goods.getOrderId());
//            }else {
//                BusinessThread businessThread = new BusinessThread(goods.getOrderId(),goods);
//                //BusinessThread businessThread = new BusinessThread(goods.getOrderId(),goods);
//                threadPool.execute(businessThread);
//            }*/
//            if(threadCount >= CORE_POOL_SIZE || threadKey == true){
//                //订单加入到缓冲队列
//                msgQueue.offer(goods);
//                System.out.println("系统任务太忙了,把此订单交给(调度线程池)逐一处理，订单号："+goods.getOrderId());
//                //不再直接走线程，先进队列
//                threadKey = true;
//            }
//            if(threadKey == false || msgQueue.isEmpty() ){
//                BusinessThread businessThread = new BusinessThread(goods.getOrderId(),goods);
//                //BusinessThread businessThread = new BusinessThread(goods.getOrderId(),goods);
//                threadPool.execute(businessThread);
//            }
//        }
//    }
//
//    /**
//     * 线程池的定时任务----> 称为(调度线程池)。此线程池支持 定时以及周期性执行任务的需求。
//     */
//    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
//
//    /**
//     * 检查(调度线程池)，每10毫秒执行一次，查看订单的缓冲队列是否有 订单记录，则重新加入到线程池
//     */
//    final ScheduledFuture scheduledFuture = scheduler.scheduleAtFixedRate(() -> {
//        //判断缓冲队列是否存在记录
//        if(!msgQueue.isEmpty()){
//            //当线程池的队列容量少于WORK_QUEUE_SIZE，则开始把缓冲队列的订单 加入到 线程池
////            threadPool.getQueue().size() < CORE_POOL_SIZE && threadKey == true
//            System.out.println("缓冲队列不为空");
//            //当线程池的队列容量少于WORK_QUEUE_SIZE，则开始把缓冲队列的订单 加入到 线程池
//            if(threadPool.getQueue().size() < WORK_QUEUE_SIZE) {
//                Goods goods = (Goods) msgQueue.poll();
//                System.out.println("数据" + goods.getOrderId() + "被放出来了");
//                BusinessThread businessThread = new BusinessThread(goods.getOrderId(), goods);
//                threadPool.execute(businessThread);
//                System.out.println("(调度线程池)缓冲队列出现订单业务，重新添加到线程池，订单号：" + goods.getOrderId());
//            }
//        }else {
//            threadKey = false;
//        }
//    }, 0, 10, TimeUnit.MILLISECONDS);
//
//
//    /**获取消息缓冲队列*/
//    public Queue<Object> getMsgQueue() {
//        return msgQueue;
//    }
//
//    /**终止订单线程池+调度线程池*/
//    public void shutdown() {
//        //true表示如果定时任务在执行，立即中止，false则等待任务结束后再停止
//        System.out.println("终止订单线程池+调度线程池："+scheduledFuture.cancel(false));
//        scheduler.shutdown();
//        threadPool.shutdown();
//
//    }
//
//    public BeanFactory getFactory() {
//        return factory;
//    }
//
//    public void setFactory(BeanFactory factory) {
//        this.factory = factory;
//    }
//}
