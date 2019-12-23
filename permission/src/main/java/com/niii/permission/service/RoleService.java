package com.niii.permission.service;

import com.niii.permission.mapper.UserRoleMapper;
import com.niii.permission.pojo.Role;
import com.niii.permission.mapper.RoleMapper;
import com.niii.permission.pojo.RoleExample;
import com.niii.permission.pojo.UserRole;
import com.niii.permission.pojo.UserRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    public void add(Role role){
        roleMapper.insert(role);
    }

    public List<Role> find(){
        RoleExample roleExample = new RoleExample();
        return roleMapper.selectByExample(roleExample);
    }

    public void delete(Integer id){
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andRidEqualTo(id);
        if(userRoleMapper.selectByExample(userRoleExample).size()>0){
            throw new RuntimeException("无法删除已被分配的角色");
        }
        roleMapper.deleteByPrimaryKey(id);
    }

    public void update(Role role){
        roleMapper.updateByPrimaryKeySelective(role);
    }
}
