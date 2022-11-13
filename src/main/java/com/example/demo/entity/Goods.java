package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("goods")
public class Goods {

    @TableId("gid")
    private Long goodsId;
    private String goodsName;
    private String goodsClass;
    private String goodsPrice;
    private String orderId;

    public Goods(Long goodsId, String goodsName, String goodsClass, String goodsPrice, String orderId) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsClass = goodsClass;
        this.goodsPrice = goodsPrice;
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Goods(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Goods(Long goodsId, String goodsName, String goodsClass, String goodsPrice) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsClass = goodsClass;
        this.goodsPrice = goodsPrice;
    }

    public Goods(String goodsName, String goodsClass, String goodsPrice) {
        this.goodsName = goodsName;
        this.goodsClass = goodsClass;
        this.goodsPrice = goodsPrice;
    }

    public Goods() {
    }

    public Long getGoodsId() {
        return goodsId;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsClass='" + goodsClass + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                '}';
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(String goodsClass) {
        this.goodsClass = goodsClass;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }


}
