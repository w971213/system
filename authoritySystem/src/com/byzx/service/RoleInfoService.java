package com.byzx.service;

import java.util.HashMap;
import java.util.List;

import com.byzx.vo.AuthInfo;
import com.byzx.vo.Role;


public interface RoleInfoService {
	
	
	public List<Role> selectRolelist(HashMap map);//查询角色
	boolean updateUserStateById(Role role);//更改角色状态
	boolean deleteRoleById(Integer roleId);//删除角色
	boolean addRole(Role role);//添加角色
	boolean updateRole(Role role);//修改角色
	boolean selectRoleInfo(Role role);// ajax 验证查询
	List<AuthInfo> selectAuthByRoleId(Integer roleId);//查询角色的权限
	List<AuthInfo> selectAllAuthInfo(HashMap map);// 查询所有角色
	boolean updateRoleAuth(Integer roleId,String ids);
	boolean  deleteRoleAuthById(Integer roleId);//删除角色所有权限
}
