package com.byzx.serviceimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byzx.mapper.AuthInfoMapper;
import com.byzx.mapper.UserInfoMapper;
import com.byzx.service.UserInfoService;
import com.byzx.vo.AuthInfo;
import com.byzx.vo.Role;
import com.byzx.vo.UserAuth;
import com.byzx.vo.UserGroup;
import com.byzx.vo.UserInfo;
import com.byzx.vo.UserList;
import com.byzx.vo.UserRole;

@Service
public class UserInfoImpl implements UserInfoService {
	
	@Autowired
	public UserInfoMapper userInfoMapper;
	@Autowired
	public AuthInfoMapper authInfoMapper;

	
	@Override
	public boolean selectUserByUserCode(UserInfo userInfo) {
		UserInfo userIn=userInfoMapper.selectUserByUserCode(userInfo);
		return userIn==null?false:true;
	}


	@Override
	public UserInfo selectUserInfoByUserCode(UserInfo userInfo) {
		return userInfoMapper.selectUserByUserCode(userInfo);
	}


	@Override
	public List<AuthInfo> selectUserInfoAuth(HashMap<String,Integer> map) {
		return authInfoMapper.selectUserInfoAuth(map);
	}


	@Override
	public List<UserList> selectAllUser(HashMap<String,Object> map) {
		return userInfoMapper.selectAllUser(map);
	}


	@Override
	public List<UserList> selectByPage(HashMap<String,Object> map) {
		return userInfoMapper.selectByPage(map);
		
	}


	@Override
	public List<UserGroup> selectAllGroup() {
		return userInfoMapper.selectAllGroup();
	}


	@Override
	public List<Role> selectAllRole() {
		return userInfoMapper.selectAllRole();
	}


	@Override
	public boolean deleteUserInfo(Integer userId) {
		int num=userInfoMapper.deleteUserInfo(userId);
		return num==1?true:false;
	}


	@Override
	public boolean updateUserPwdById(Integer userId) {
		int num=userInfoMapper.updateUserPwdById(userId);
		return num==1?true:false;
	}


	@Override
	public boolean updateUserStateById(UserInfo userInfo) {
		int num=userInfoMapper.updateUserStateById(userInfo);
		return num==1?true:false;
	}
	

	

	@Override
	public boolean updateUserInfoById(UserInfo userInfo) {
		int num=userInfoMapper.updateUserInfoById(userInfo);
		return num==1?true:false;
	}


	@Override
	public boolean addUserInfo(UserInfo userInfo) {
		int num=userInfoMapper.addUserInfo(userInfo);
		return num==1?true:false;
	}

	@Override
	public boolean selectUserInfoByUserCode(String userCode) {
		UserInfo userInfo=userInfoMapper.selectUserInfoByUserCode(userCode);
		return userInfo==null?true:false;
	}


	@Override
	public UserRole selectUserRole(Integer userId) {
		return userInfoMapper.selectUserRole(userId);
	}

	@Override
	@Transactional
	public boolean addUserRole(Integer userId,String roleIds) {
		UserRole userRoles=userInfoMapper.selectUserRole(userId);//查询原用户是否有角色
		if(userRoles!=null) {            //不为null 则删除原角色
			int num=userInfoMapper.deleteUserRoleById(userId);
			if(num!=1) {
				return false;
			}
		}
		UserRole userRole=new UserRole(); //new 对象 装数据
		String[] roleIdes=roleIds.split(",");
		userRole.setUserId(userId);
		for(String roleId:roleIdes) {
			userRole.setRoleId(Integer.parseInt(roleId));
			int num1=userInfoMapper.addUserRole(userRole);
			if(num1==0) {
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean deleteUserRoleById(Integer userId) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<AuthInfo> selectAllAuthInfo(HashMap<String,Integer> map) {
		
		return authInfoMapper.selectAllAuthInfo(map);
	}


	@Override
	public UserInfo selectUserInfoById(Integer userId) {
		
		return userInfoMapper.selectUserInfoById(userId);
	}

	@Override
	public List<AuthInfo> selectUserInfoAllAuth(HashMap<String, Integer> map) {
		return authInfoMapper.selectUserInfoAllAuth(map);
	}

	@Override
	@Transactional
	public boolean updateAuth(UserInfo userInfo,String ids) {
		HashMap<String, Integer> map=new HashMap<String,Integer>();
		map.put("userId", userInfo.getUserId());
		map.put("authState", 1);
		List<AuthInfo> userAuthInfos=authInfoMapper.selectUserInfoAllAuth(map);
		if(userAuthInfos.size()!=0) {   
			int num=authInfoMapper.deleteUserAuthById(userInfo.getUserId());
			if(num==0) {
				return false;
			}
		}
		UserAuth userAuth =new UserAuth();
		String[] id=ids.split(",");
		userAuth.setUserId(userInfo.getUserId());
		for(String authid :id) {
			userAuth.setAuthId(Integer.parseInt(authid));
			int num1=authInfoMapper.addUserAuth(userAuth);
			if(num1!=1) {
			 return false;
			}
		}
		return true;
	}


	@Override
	public boolean deleteUserAuthById(Integer userId) {
		int num = authInfoMapper.deleteUserAuthById(userId);
		return num==1?true:false;
	}



	
	

}
