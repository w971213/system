package com.byzx.serviceimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byzx.mapper.GroupInfoMapper;
import com.byzx.service.GroupInfoService;
import com.byzx.vo.GroupRole;
import com.byzx.vo.UserGroup;
import com.byzx.vo.UserRole;
@Service
public class GroupInfoImpl implements GroupInfoService {
	
	@Autowired
	public GroupInfoMapper groupInfoMapper;
	
	@Override
	public List<UserGroup> selectGrouplist(HashMap map) {
		
		return groupInfoMapper.selectGrouplist(map);
	}

	@Override
	public boolean updateUserStateById(UserGroup group) {
		int num=groupInfoMapper.updateUserStateById(group);
		return num==1?true:false;
	}

	@Override
	public boolean deletegroupById(Integer groupId) {
		int num=groupInfoMapper.deletegroupById(groupId);
		return num==1?true:false;
	}

	@Override
	public boolean addgroup(UserGroup group) {
		int num=groupInfoMapper.addgroup(group);
		return num==1?true:false;
	}

	@Override
	public boolean updategroup(UserGroup group) {
		int num=groupInfoMapper.updategroup(group);
		return num==1?true:false;
	}

	@Override
	public boolean selectgroupInfo(UserGroup group) {
		UserGroup userGroup=groupInfoMapper.selectgroupInfo(group);
		return userGroup==null?true:false;
	}

	@Override
	public UserGroup selectGroupRoleId(Integer groupId) {
		
		return groupInfoMapper.selectGroupRoleId(groupId);
	}

	@Override
	public boolean updateGroupRole(String roleIds,GroupRole groupRole) {
		UserGroup group=groupInfoMapper.selectGroupRoleId(groupRole.getGroupId());//查询原用组户是否有角色
		if(group!=null) {            //不为null 则删除原角色
			int num=groupInfoMapper.deleteGroupRoleById(groupRole.getGroupId());
			if(num==0) {
				return false;
			}
		}
		String[] roleIdes=roleIds.split(",");
		for(String roleId:roleIdes) {
			System.out.println("roleId:"+roleId);
			groupRole.setRoleId(Integer.parseInt(roleId));
			int num1=groupInfoMapper.addGroupRole(groupRole);
			if(num1!=1) {
				return false;
			}
		}
		return true;
	}

}
