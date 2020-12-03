package com.kou.service.impl;

import com.github.pagehelper.PageHelper;
import com.kou.dao.IProductDao;
import com.kou.domain.Product;
import com.kou.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JIAJUN KOU
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> findAll(Integer page,Integer size) {
        //代表一页显示五条；必须在正常查询代码之前
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

}
