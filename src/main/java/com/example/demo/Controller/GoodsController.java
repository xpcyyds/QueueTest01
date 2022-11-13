package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.example.demo.Config.TestThreadPoolManager;
//import com.example.demo.Config.QueueManager;
import com.example.demo.Config.QueueManager;
import com.example.demo.Dao.GoodsDao;
import com.example.demo.Service.GoodsService;
import com.example.demo.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    QueueManager queueManager;

    //@Autowired
    //TestThreadPoolManager testThreadPoolManager;



    /*
    1.添加商品操作@RequestParam("goodsId") String goodsId,
                            @RequestParam("goodsName") String goodsName,
                            @RequestParam("goodsClass") String goodsClass,
                            @RequestParam("goodsPrice") String goodsPrice
    */

    /**
     * 1.添加商品操作
     * @param
     * @return
     */
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    public Result saveGoods(@RequestParam("goodsId") Integer goodsId,
//                            @RequestParam("goodsName") String goodsName,
//                            @RequestParam("goodsClass") String goodsClass,
//                            @RequestParam("goodsPrice") String goodsPrice) {
//        //开启多线程
//        Random i = new Random();
//        Integer integer = i.nextInt(9999);
//
//        String orderNo = ""+integer+"";
//        Goods goods = new Goods(goodsId,goodsName,goodsClass,goodsPrice,orderNo);
//        System.out.println("开启了线程服务，处理的信息条数："+orderNo);
//        testThreadPoolManager.addOrders(goods);
//
//        boolean flag1 = true;
//        String msg = "成功";
//        return new Result(flag1 ? Code.ADD_OK : Code.ADD_ERR, msg);
//    }

    /**
     * 1.添加商品操作
     * @param
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result saveGoods(@RequestParam("goodsName") String goodsName,
                            @RequestParam("goodsClass") String goodsClass,
                            @RequestParam("goodsPrice") String goodsPrice) {

        Goods goods = new Goods(goodsName,goodsClass,goodsPrice);

        queueManager.addQueue(goods);

        boolean flag1 = true;
        String msg = "成功";
        return new Result(flag1 ? Code.ADD_OK : Code.ADD_ERR, msg);
    }

    /**
     * 2.通过商品ID查询商品操作
     * @param
     * @return
     */
    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    public Result selectByIdGoods(@RequestParam("goodsId") Long goodsId) {

        Goods goods = goodsDao.selectByGoodsId(goodsId);
        System.out.println(goods.toString());
        System.out.println(goods.getGoodsId());
        boolean flag1 = true;
        String msg = "成功";
        return new Result(flag1 ? Code.ADD_OK : Code.ADD_ERR, msg);
    }

    /**
     * 3.通过商品ID删除商品操作
     * @param
     * @return
     */
    @RequestMapping(value = "/deleteById",method = RequestMethod.DELETE)
    public Result deleteByIdGoods(@RequestParam("goodsId") Long goodsId) {

        goodsDao.deleteByGoodsId(goodsId);
        boolean flag1 = true;
        String msg = "成功";
        return new Result(flag1 ? Code.ADD_OK : Code.ADD_ERR, msg);
    }

    /**
     * 4.更改商品操作
     * @param
     * @return
     */
    @RequestMapping(value = "/updateGoods",method = RequestMethod.GET)
    public Result updateGoods(@RequestParam("goodsId") Long goodsId,
                              @RequestParam("goodsName") String goodsName,
                              @RequestParam("goodsClass") String goodsClass,
                              @RequestParam("goodsPrice") String goodsPrice) {
        Goods goods = new Goods(goodsId,goodsName,goodsClass,goodsPrice);
        goodsDao.updateTheGoods(goods);
        boolean flag1 = true;
        String msg = "成功";
        return new Result(flag1 ? Code.ADD_OK : Code.ADD_ERR, msg);
    }
    /**
     * 2.删除商品操作
     * @param id
     * @return
     */
//    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
//    public Result deleteGoods(@RequestParam("goodsId") Integer id) {
//        Goods goods = new Goods(id);
//        boolean flag = goodsService.deleteGoods(goods);
//        Integer code = flag ? Code.DELETE_OK : Code.DELETE_ERR;
//        String msg = flag  ? "" : "数据删除失败，请重试！";
//        /*if(flag){
//            int count = goodsService.selectCount(), ID;
//            for(ID = id + 1; ID <= count + 1; ID++){
//                goodsService.updateID(ID,ID-1);
//            }
//        }*/
////        goodsDao.deleteById(id);
//        //logger.info("商品删除成功");
//        return new Result(code, msg);
//    }

    /*
    3.修改商品操作@RequestParam("goodsId") Integer id,
                              @RequestParam("goodsName") String goodsName,
                              @RequestParam("goodsClass") String goodsClass,
                              @RequestParam("goodsPrice") String goodsPrice
                              @RequestBody Goods goods
    */

//    /**
//     * 3.修改商品操作
//     * @param goods
//     * @return
//     */
//    @RequestMapping(value = "/edit",method = RequestMethod.POST)
//    public Result updateGoods(@RequestBody Goods goods) {
////        Goods goods = new Goods(id,goodsName,goodsClass,goodsPrice);
//        if(goods.getGoodsName().length() > 45){
//            Integer code = 400;
//            String msg = "输入字符过长，请重新输入";
//            return new Result(code,msg);
//        }
//        boolean flag = goodsService.updateGoods(goods);
//        /*boolean flag = true;
//        goodsDao.updateById(goods);*/
//        String msg = flag  ? "" : "数据更新失败，请重试！";
//        //logger.info("user edit  success goodsName==="+goods.getGoodsName()+"   goodsClass==="+goods.getGoodsClass()+ " goodPrice==="+goods.getGoodsPrice());
//        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, msg);
//    }

    /*
    4.查询单个商品操作@RequestParam("query") String goodsName,@RequestParam("pageNum") Integer current, @RequestParam("pageSize") Integer size
    */

    /**
     * 4.查询商品操作
     * @param queryPage
     * @return
     */
    /*@RequestMapping(value = "/getGoods",method = RequestMethod.GET)
    public Result getByName(QueryPage queryPage) {
        logger.info("query==="+queryPage.getQuery()+"pageNum==="+queryPage.getPageNum()+"pageSize==="+queryPage.getPageSize());
//        Lis t<Goods> goodsList =  goodsService.getByName(goodsName);
        //1.进行模糊查询并利用wrapper容器进行
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("goods_Name",queryPage.getQuery());
        //2.接收wrapper容器中的数据进行分页操作
        IPage page = new Page(queryPage.getPageNum(),queryPage.getPageSize());
        goodsDao.selectPage(page,wrapper);
        //3.用goodList来接收分页操作后的数据
        List<Goods> goodsList =  page.getRecords();
        logger.info("分页查询数据成功");

        Integer code = goodsList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = goodsList != null ? "" : "数据查询失败，请重试！";
        //4.搜索进行模糊查询后的数据总数
        Long total = goodsDao.selectCount(wrapper);
        logger.info("数据总数==="+total);
        return new Result(code, goodsList, msg, total);
    }*/

    /*
    5.查询所有商品操作@RequestParam("username") String loginName@RequestParam("password") String loginPsw@RequestBody User user
    */
    /*@RequestMapping(value = "/getByName",method = RequestMethod.POST)
    @CrossOrigin
    public Result getAll(@RequestBody User user) {
        //User user = new User(loginName, loginPsw);
        System.out.println("user login success !!! username==="+user.getUsername()+"   password==="+user.getPassword());
        System.out.println("查询所有商品成功");
        List<Goods> goodsList =  goodsService.getAll();
        Integer code = goodsList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = goodsList != null ? "" : "数据查询失败，请重试！";
        Integer total = goodsService.selectCount();
        return new Result(code,goodsList,msg,total);
    }*/

    /*
    6.分页查询
    */
    /*@RequestMapping(value = "/getAll",method = RequestMethod.POST)
    @CrossOrigin
    public Result getByPage(@RequestParam("pageNum") Integer current, @RequestParam("pageSize") Integer size) {
        //User user = new User(loginName, loginPsw);
//        System.out.println("user login success !!! username==="+user.getUsername()+"   password==="+user.getPassword());
        System.out.println("分页查询商品成功");
        IPage page = new Page(current,size);
        goodsDao.selectPage(page,null);
        List<Goods> goodsList =  page.getRecords();
        Integer code = goodsList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = goodsList != null ? "" : "数据查询失败，请重试！";
        Integer total = goodsService.selectCount();
        return new Result(code,goodsList,msg,total);
    }*/
}
