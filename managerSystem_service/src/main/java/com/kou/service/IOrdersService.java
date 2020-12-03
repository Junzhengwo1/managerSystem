package com.kou.service;

import com.kou.domain.Orders;

import java.util.List;

/**
 * @author dell
 */
public interface IOrdersService {

    List<Orders> findAll(Integer page,Integer size);

    Orders findById(Integer ordersId);
}
