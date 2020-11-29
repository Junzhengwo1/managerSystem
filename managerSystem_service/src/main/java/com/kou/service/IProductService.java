package com.kou.service;

import com.kou.domain.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询所有产品信息
     * @return
     */
    public List<Product> findAll();

    /**
     * 添加产品
     * @param product
     */
    void save(Product product);
}
