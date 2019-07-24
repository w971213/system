package com.byzx.mapper;

import java.util.HashMap;
import java.util.List;

import com.byzx.vo.AuthInfo;
import com.byzx.vo.Role;
import com.byzx.vo.RoleAuth;

public interface RoleInfoMapper {

	List<Role> selectRolelist(HashMap map);//查询角色
	int updateUserStateById(Role role);//更改角色状态
	int deleteRoleById(Integer roleId);//删除角色
	int addRole(Role role);//添加角色
	int updateRole(Role role);//修改角色信息
	Role selectRoleInfo(Role role);// ajax 模糊查询
	List<AuthInfo> selectAuthByRoleId(Integer roleId);//查询角色的权限
	int  deleteRoleAuth(Integer roleId);//删除角色所有权限
	int  addRoleAuth(RoleAuth roleAuth);//添加角色权限
}
