package com.niii.permission.service;

import com.niii.permission.mapper.MenuRoleMapper;
import com.niii.permission.pojo.Menu;
import com.niii.permission.mapper.MenuMapper;
import com.niii.permission.pojo.MenuExample;
import com.niii.permission.pojo.MenuRole;
import com.niii.permission.pojo.MenuRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    public void add(Menu menu){
        menuMapper.insert(menu);
    }

    public List<Menu>findall(){
        MenuExample menuExample = new MenuExample();
        return menuMapper.selectByExample(menuExample);
    }

    public List<Menu>findbyrid(Integer rid){
        MenuRoleExample menuRoleExample = new MenuRoleExample();
        menuRoleExample.createCriteria().andRidEqualTo(rid);
        List<MenuRole>list = menuRoleMapper.selectByExample(menuRoleExample);

        MenuExample menuExample = new MenuExample();
        System.out.println(list);
        if(list.size()==0){
            return new LinkedList<>();
        }
        for(MenuRole menuRole : list){
            MenuExample.Criteria criteria = menuExample.createCriteria();
            criteria.andIdEqualTo(menuRole.getMid());
            menuExample.or(criteria);
        }

        return menuMapper.selectByExample(menuExample);
    }

    public void update(Menu menu) {
         menuMapper.updateByPrimaryKeySelective(menu);
    }

    public void delete(Integer id) {
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andParentidEqualTo(id);
        List<Menu> list = menuMapper.selectByExample(menuExample);
        if(list.size()>0){
            throw new RuntimeException("存在下级目录，无法删除");
        }
        menuMapper.deleteByPrimaryKey(id);
    }
}
