package com.kou.dao;

import com.kou.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dell
 */
public interface ITravellerDao {

    /**
     * 中间表的 根据ordersId查询出traveller
     * @param ordersId
     * @return
     */
    @Select("SELECT *FROM traveller WHERE id in (SELECT travellerId FROM order_traveller WHERE orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(int ordersId);
}
