package com.byzx.service;

import java.util.HashMap;
import java.util.List;

import com.byzx.vo.GroupRole;
import com.byzx.vo.UserGroup;

public interface GroupInfoService {
	
	public List<UserGroup> selectGrouplist(HashMap map);//查询所有用户组
	public	boolean updateUserStateById(UserGroup group);//更改角色状态
	public	boolean deletegroupById(Integer groupId);//删除角色
	public	boolean addgroup(UserGroup group);//添加角色
	public	boolean updategroup(UserGroup group);//修改角色信息
	public	boolean selectgroupInfo(UserGroup group);// ajax 模糊查询
	public	UserGroup selectGroupRoleId(Integer groupId);//查询当前用户组的角色
	public	boolean updateGroupRole(String ids,GroupRole groupRole);//添加用户组的角色
	//boolean deleteGroupRoleById(Integer groupId);//删除用户组
}
