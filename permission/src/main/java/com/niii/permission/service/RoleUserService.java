package com.niii.permission.service;

import com.niii.permission.mapper.UserRoleMapper;
import com.niii.permission.pojo.UserRole;
import com.niii.permission.pojo.UserRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class RoleUserService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void assign(Integer id, List<Integer> roleList){
        try{
            UserRoleExample userRoleExample = new UserRoleExample();
            userRoleExample.createCriteria().andUseridEqualTo(id);

            userRoleMapper.deleteByExample(userRoleExample);

            for(int i=0;i<roleList.size();i++){
                UserRole userRole = new UserRole();
                userRole.setUserid(id);
                userRole.setRid(roleList.get(i));
                userRoleMapper.insert(userRole);
            }
        }
        catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("设置失败");
        }


    }
}
