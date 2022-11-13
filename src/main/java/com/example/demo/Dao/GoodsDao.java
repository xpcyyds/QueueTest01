package com.example.demo.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO 添加@Mapper
@Mapper
@Repository
public interface GoodsDao extends BaseMapper<Goods> {
    //1.添加商品操作insert into goods values(null,#{goodsname},#{goodsclass},#{goodsprice})
//    @Insert("insert into goods (goods_Name,goods_Class,goods_Price) values(#{goodsName},#{goodsClass},#{goodsPrice})")
//    public void insertGoods(Goods goods);
    @Select("select * from goods where gid = #{goodsId%2}")
    public Goods selectByGoodsId(Long goodsId);

    //2.删除商品操作
    @Delete("delete from goods where gid=#{goodsId%2}")
    public void deleteByGoodsId(Long goodsId);

    //3.修改商品操作
    @Update("update goods set goods_Name=#{goodsName} , goods_Class=#{goodsClass} , goods_Price=#{goodsPrice} where gid=#{goodsId}")
    public void updateTheGoods(Goods goods);

    //4.查询单个商品
    @Select("select * from goods where goods_Name like concat('%',#{goodsName},'%')")
    public List<Goods> getByName(String goodsName);

    //5.查询全部商品
    @Select("select * from goods")
    public List<Goods> getAll();

    //6.查询商品总数
    @Select("select count(*) from goods")
    public int count();

    //7.更改商品ID
    @Update("update goods set goodsId=#{ID} where goodsId=#{id}")
    public void updateID(Integer id,Integer ID);

    //8.查看商品ID是否存在
    @Select("select count(goodsId) from goods where goodsId=#{goodsId}")
    public boolean selectUser(Goods goods);
}
