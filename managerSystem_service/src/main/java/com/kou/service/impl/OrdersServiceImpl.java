package com.kou.service.impl;

import com.github.pagehelper.PageHelper;
import com.kou.dao.IOrdersDao;
import com.kou.domain.Orders;
import com.kou.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JIAJUN KOU
 */
@Service("ordersService")
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page,int size) {
        //代表一页显示五条；必须在正常查询代码之前
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(int ordersId) {
        return ordersDao.findById(ordersId);
    }
}
