//package com.example.demo.Service;
//
//import com.example.demo.Dao.GoodsDao;
//import com.example.demo.Utils.BeanUtils;
//import com.example.demo.entity.Goods;
//
//public class QueueThread implements Runnable{
//
//
//    private GoodsDao goodsDao = BeanUtils.getBean(GoodsDao.class);
//
//    private String acceptStr;
//    private Goods goods;
//
//    public Goods getGoods() {
//        return goods;
//    }
//
//    public void setGoods(Goods goods) {
//        this.goods = goods;
//    }
//
//    public QueueThread(String acceptStr, Goods goods) {
//        this.acceptStr = acceptStr;
//        this.goods = goods;
//    }
//
//    public String getAcceptStr() {
//        return acceptStr;
//    }
//
//    public void setAcceptStr(String acceptStr) {
//        this.acceptStr = acceptStr;
//    }
//
//    @Override
//    public void run() {
//        /*//业务操作
//        System.out.println("多线程已经处理订单插入系统，订单号："+acceptStr);*/
//
//        // 加上同步代码块,把需要同步的代码放入代码块中,同步代码块中的锁对象必须保证一致！
//
//        //线程阻塞
//        System.out.println("多线程"+Thread.currentThread().getName()+"已经处理订单插入系统，订单号："+acceptStr);
//        goodsDao.insertGoods(goods);
////            int count = goodsDao.count() - 2;
////            System.out.println("当前插入第"+count+"个数据");
//    }
//}
