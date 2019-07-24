package com.byzx.serviceimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byzx.mapper.AuthInfoMapper;
import com.byzx.mapper.RoleInfoMapper;
import com.byzx.service.RoleInfoService;
import com.byzx.vo.AuthInfo;
import com.byzx.vo.Role;
import com.byzx.vo.RoleAuth;
@Service
public class RoleInfoImpl implements RoleInfoService {

	@Autowired
	public RoleInfoMapper roleInfoMapper;
	@Autowired
	public AuthInfoMapper authInfoMapper;
	
	@Override
	public List<Role> selectRolelist(HashMap map) {
		return roleInfoMapper.selectRolelist(map);
	}
	@Override
	public boolean updateUserStateById(Role role) {
		int num=roleInfoMapper.updateUserStateById(role);
		return num==1?true:false;
	}
	@Override
	public boolean deleteRoleById(Integer roleId) {
		int num=roleInfoMapper.deleteRoleById(roleId);
		return num==1?true:false;
	}
	@Override
	public boolean addRole(Role role) {
		int num=roleInfoMapper.addRole(role);
		return num==1?true:false;
	}
	@Override
	public boolean updateRole(Role role) {
		int num=roleInfoMapper.updateRole(role);
		return num==1?true:false;
	}
	@Override
	public boolean selectRoleInfo(Role role) {
		Role roles=roleInfoMapper.selectRoleInfo(role);
		return roles==null?true:false;
	}
	@Override
	public List<AuthInfo> selectAuthByRoleId(Integer roleId) {
		return roleInfoMapper.selectAuthByRoleId(roleId);
	}
	@Override
	public List<AuthInfo> selectAllAuthInfo(HashMap map) {
		return authInfoMapper.selectAllAuthInfo(map); 
	}
	@Override
	@Transactional
	public boolean updateRoleAuth(Integer roleId,String ids) {
		HashMap<String, Integer> map=new HashMap<String,Integer>();
		map.put("userId", roleId);
		map.put("authState", 1);
		List<AuthInfo> roleAuths= roleInfoMapper.selectAuthByRoleId(roleId);
		if(roleAuths.size()!=0) {   
			int num=roleInfoMapper.deleteRoleAuth(roleId);
			System.out.println("num:"+num);
			if(num==0) {
				return false;
			}
		}
		RoleAuth roleAuth =new RoleAuth();
		String[] id=ids.split(",");
		roleAuth.setRoleId(roleId);
		for(String authid :id) {
			roleAuth.setAuthId(Integer.parseInt(authid));
			int num1=roleInfoMapper.addRoleAuth(roleAuth);
			if(num1!=1) {
			 return false;
			}
		}
		return true;
	}
	@Override
	public boolean deleteRoleAuthById(Integer roleId) {
		int num=roleInfoMapper.deleteRoleById(roleId);
		return num==1?true:false;
	}
}
