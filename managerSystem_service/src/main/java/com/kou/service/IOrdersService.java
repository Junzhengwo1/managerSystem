package com.kou.service;

import com.kou.domain.Orders;

import java.util.List;

/**
 * @author dell
 */
public interface IOrdersService {

    List<Orders> findAll(int page,int size);

    Orders findById(int ordersId);
}
