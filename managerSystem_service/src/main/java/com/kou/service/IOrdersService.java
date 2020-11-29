package com.kou.service;

import com.kou.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page,int size);

    Orders findById(int ordersId);
}
