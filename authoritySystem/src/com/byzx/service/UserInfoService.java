package com.byzx.service;

import java.util.HashMap;
import java.util.List;

import com.byzx.vo.AuthInfo;
import com.byzx.vo.Role;
import com.byzx.vo.UserGroup;
import com.byzx.vo.UserInfo;
import com.byzx.vo.UserList;
import com.byzx.vo.UserRole;

public interface UserInfoService {
	
	boolean selectUserByUserCode(UserInfo userInfo);//判断是否存在账号
	UserInfo selectUserInfoByUserCode(UserInfo userInfo);//将user信息返回
	List<AuthInfo>selectUserInfoAuth(HashMap<String,Integer> map);//查询权限 分等级
	List<UserList> selectAllUser(HashMap<String, Object> map);//用户列表
    List<UserList> selectByPage(HashMap<String, Object> map);//分页展示用户列表
    List<UserGroup> selectAllGroup();//查询组信息
    boolean deleteUserInfo(Integer userId);//删除用户
    boolean updateUserPwdById(Integer userId); //重置密码
    boolean updateUserStateById(UserInfo userInfo); //更改用户状态
    List<Role> selectAllRole();//查找所有的角色
    UserRole selectUserRole(Integer userId); //查找用户的角色
    boolean updateUserInfoById(UserInfo userInfo);  //修改用户
    boolean addUserInfo(UserInfo userInfo);    //添加用户
    boolean selectUserInfoByUserCode(String userCode);//验证用户名是否可用
    boolean  deleteUserRoleById(Integer userId);//删除用户的角色
    boolean addUserRole(Integer userId,String roleIds); //添加用户角色
    List<AuthInfo> selectAllAuthInfo(HashMap<String,Integer> map);//查询权限
    UserInfo selectUserInfoById(Integer userId);//通过userId查用户
    List<AuthInfo> selectUserInfoAllAuth(HashMap<String,Integer> map);//查询用户权限不分等级
    boolean updateAuth(UserInfo userInfo,String ids);//更改权限
    boolean deleteUserAuthById(Integer userId);//删除用户的所有权限
}
