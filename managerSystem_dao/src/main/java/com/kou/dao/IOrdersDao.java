package com.kou.dao;

import com.kou.domain.Member;
import com.kou.domain.Orders;
import com.kou.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author dell
 */
public interface IOrdersDao {

    /**
     * 查询所有订单信息
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,
                    one=@One(select = "com.kou.dao.IProductDao.findById"))
    })
    public List<Orders> findAll();


    /**
     * 根据订单的id查询订单
     * @param ordersId
     * @return
     */

    //多表操作

    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,
                    one=@One(select = "com.kou.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,
                    one =@One(select = "com.kou.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,
                    many = @Many(select = "com.kou.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(int ordersId);
}
