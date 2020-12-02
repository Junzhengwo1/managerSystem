package com.kou.controller;
import com.kou.domain.Permission;
import com.kou.domain.Role;

import com.kou.service.IRoleService;
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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)int roleId){
        ModelAndView mv=new ModelAndView();
        Role role=roleService.findById(roleId);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/deleteRole.do")
    public String deleteRole(@RequestParam(name = "id",required = true) int roleId){
        roleService.deleteRole(roleId);
        return "redirect:findAll.do";
    }

    /**
     * 查询角色，并且查询出可以添加的权限
     */
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)int roleId){
        //1.根据roleId查询role
        Role role=roleService.findById(roleId);
        //2.根据roleId查询可以添加的权限
        List<Permission> otherPermissions= roleService.findOtherPermissions(roleId);
        ModelAndView mv=new ModelAndView();
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)int roleId,
                                      @RequestParam(name = "ids",required = true)int[] permissionIds){

        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
