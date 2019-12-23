package com.niii.permission.service;

import com.niii.permission.mapper.MenuRoleMapper;
import com.niii.permission.pojo.Menu;
import com.niii.permission.pojo.MenuRole;
import com.niii.permission.pojo.MenuRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class MenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void assign(Integer roleId, List<Integer> menuList){
        try{
            MenuRoleExample menuRoleExample = new MenuRoleExample();
            menuRoleExample.createCriteria().andRidEqualTo(roleId);
            menuRoleMapper.deleteByExample(menuRoleExample);
//            int k = 1/0;
            for(int i=0;i<menuList.size();i++){
                MenuRole menuRole = new MenuRole();
                menuRole.setRid(roleId);
                menuRole.setMid(menuList.get(i));
                menuRoleMapper.insert(menuRole);
            }
        }
        catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("设置失败");
        }


    }

}
