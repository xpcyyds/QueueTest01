package com.example.demo.Controller;

//import com.example.demo.Config.TestThreadPoolManager;
import com.example.demo.entity.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2018/5/9.
 */
//@RestController
//public class TestController {
//
//    @Autowired
//    //TestThreadPoolManager testThreadPoolManager;
//
//    /**
//     * 测试模拟下单请求 入口
//     * @param id
//     * @return
//     */
//    @GetMapping("/start")
//    public String start() {
//        //模拟的随机数
////        String orderNo = System.currentTimeMillis() + UUID.randomUUID().toString();
//
//        String orderNo;
//        Random i = new Random();
//        Integer integer = i.nextInt(9999);
//
//        orderNo = ""+integer+"";
//        System.out.println("开启了线程服务，处理的信息条数："+integer);
////        testThreadPoolManager.addOrders(orderNo);
//        System.out.println("一次完成");
//
//        return "Test ThreadPoolExecutor start";
//    }
//
//    /**
//     * 停止服务
//     * @param id
//     * @return
//     */
//    @GetMapping("/end/{id}")
//    public String end(@PathVariable Long id) {
//
//        //testThreadPoolManager.shutdown();
//
//        //Queue q = testThreadPoolManager.getMsgQueue();
//        //System.out.println("关闭了线程服务，还有未处理的信息条数：" + q.size());
//        return "Test ThreadPoolExecutor start";
//    }
//}
