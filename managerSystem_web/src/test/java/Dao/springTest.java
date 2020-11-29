package Dao;

import com.kou.domain.Orders;
import com.kou.domain.Product;
import com.kou.service.IOrdersService;
import com.kou.service.IProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author JIAJUN KOU
 */
public class springTest {
    @Test
    public void findAllProduct(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        IProductService productService = (IProductService) ac.getBean("productService");
        System.out.println(productService);
        List<Product> products = productService.findAll();
        for (Product product : products) {
            System.out.println(product);
        }

    }

//    @Test
//    public void findAllOrders(){
//        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
//        IOrdersService ordersService = (IOrdersService) ac.getBean("ordersService");
//        System.out.println(ordersService);
//        List<Orders> ordersList = ordersService.findAll();
//        for (Orders orders : ordersList) {
//            System.out.println(orders);
//        }
//    }
}
