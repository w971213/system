package com.byzx.mapper;

import java.util.HashMap;
import java.util.List;

import com.byzx.vo.Role;
import com.byzx.vo.UserGroup;
import com.byzx.vo.UserInfo;
import com.byzx.vo.UserList;
import com.byzx.vo.UserRole;

public interface UserInfoMapper {
   
    //查找该用户的信息
    UserInfo selectUserByUserCode(UserInfo userInfo);
    //用户列表
    List<UserList> selectAllUser(HashMap<String,Object> map);
    //分页展示
    List<UserList> selectByPage(HashMap<String,Object> map);
    //查询组的信息
    List<UserGroup> selectAllGroup();
    //删除用户
    int deleteUserInfo(Integer userId);
    //重置密码
    int updateUserPwdById(Integer userId);
    //更改用户状态
    int updateUserStateById(UserInfo userInfo);
    //查找所有的角色
    List<Role> selectAllRole();
    //查找用户的角色
    UserRole selectUserRole(Integer userId);
    //修改用户
    int updateUserInfoById(UserInfo userInfo);
    //添加用户
    int addUserInfo(UserInfo userInfo);
    //验证用户名是否可用查找用户
    UserInfo selectUserInfoByUserCode(String userCode);
    //删除用户的角色
    int deleteUserRoleById(Integer userId);
    //添加用户角色
    int addUserRole(UserRole userRole);
    //通过userId查用户
    UserInfo selectUserInfoById(Integer userId);
}