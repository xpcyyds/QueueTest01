package com.example.demo.Service.impl;

import com.example.demo.Dao.GoodsDao;
import com.example.demo.Service.GoodsService;
import com.example.demo.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public boolean saveGoods(Goods goods) {

        goodsDao.insert(goods);
        return true;
    }

    @Override
    public boolean deleteGoods(Long goodsId) {
        goodsDao.deleteByGoodsId(goodsId);
        return true;
    }

    @Override
    public boolean updateGoods(Goods goods) {
        goodsDao.updateTheGoods(goods);
        return true;
    }

    @Override
    public List<Goods> getByName(String goodsname) {
        return goodsDao.getByName(goodsname);
    }

    @Override
    public List<Goods> getAll() {
        return goodsDao.getAll();
    }

    @Override
    public int selectCount(){
        return goodsDao.count();
    }

    @Override
    public void updateID(Integer id,Integer ID){
        goodsDao.updateID(id,ID);
    }

    @Override
    public boolean findGoods(Goods goods) {
        boolean bl = goodsDao.selectUser(goods);
        return bl;
    }
}
