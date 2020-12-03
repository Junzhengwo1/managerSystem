package com.kou.service;

import com.kou.domain.Product;

import java.util.List;

/**
 * @author dell
 */
public interface IProductService {
    /**
     * 查询所有产品信息
     * @param page
     * @param size
     * @return
     */
    public List<Product> findAll(Integer page,Integer size);

    /**
     * 添加产品
     * @param product
     */
    void save(Product product);
}
