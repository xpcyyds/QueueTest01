package com.example.demo.Service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.demo.Dao.GoodsDao;
import com.example.demo.Utils.BeanUtils;
import com.example.demo.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/5/9.
 */
@Component
@Scope("prototype")//spring 多例
public class BusinessThread implements Runnable{

    private GoodsDao goodsDao = BeanUtils.getBean(GoodsDao.class);

    private String acceptStr;
    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public BusinessThread(String acceptStr, Goods goods) {
        this.acceptStr = acceptStr;
        this.goods = goods;
    }

    public String getAcceptStr() {
        return acceptStr;
    }

    public void setAcceptStr(String acceptStr) {
        this.acceptStr = acceptStr;
    }

    @Override
    public void run() {
        /*//业务操作
        System.out.println("多线程已经处理订单插入系统，订单号："+acceptStr);*/

            // 加上同步代码块,把需要同步的代码放入代码块中,同步代码块中的锁对象必须保证一致！

                //线程阻塞
            System.out.println("多线程"+Thread.currentThread().getName()+"已经处理订单插入系统，订单号："+acceptStr);
            goodsDao.insert(goods);
//            int count = goodsDao.count() - 2;
//            System.out.println("当前插入第"+count+"个数据");
    }
}
