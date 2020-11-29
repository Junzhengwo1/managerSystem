package com.kou.dao;

import com.kou.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dell
 */
@Repository
public interface IProductDao {

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    public Product findById(int id);

    /**
     * 查询所有产品信息
     * @return
     */
    @Select("select * from product")
    public List<Product> findAll();

    /**
     *新建产品持久层接口
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)" +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);
}
