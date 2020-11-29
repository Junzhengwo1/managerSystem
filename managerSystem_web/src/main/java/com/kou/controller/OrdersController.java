package com.kou.controller;

import com.github.pagehelper.PageInfo;
import com.kou.domain.Orders;
import com.kou.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * @author JIAJUN KOU
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //查询全部订单 这是没有分页的
    /*
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        System.out.println("findOrders.....");
        ModelAndView mv=new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mv.addObject("ordersList",ordersList);
        mv.setViewName("orders-list");
        return mv;
    }

     */

    //这是查询订单分页之后的
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name ="page",required = true,defaultValue ="1" )int page,@RequestParam(name = "size",required = true,defaultValue = "5")int size){
        System.out.println("findOrders_page.....");
        ModelAndView mv=new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);
        //pageHelper提供的构造；就是一个分页的Bean
        PageInfo pageInfo=new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)int ordersId){
        System.out.println("查询订单详情。。。。");
        ModelAndView mv=new ModelAndView();
        Orders orders=ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");

        return mv;
    }
}
