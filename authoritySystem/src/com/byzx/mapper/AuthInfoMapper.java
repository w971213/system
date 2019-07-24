package com.byzx.mapper;

import java.util.HashMap;
import java.util.List;

import com.byzx.vo.AuthInfo;
import com.byzx.vo.UserAuth;

public interface AuthInfoMapper {
	
	List<AuthInfo> selectUserInfoAuth(HashMap<String,Integer> map);// 用户登录时 查询用户的权限 分等级
	List<AuthInfo> selectAllAuthInfo(HashMap map);//查询所有的角色  点击更改权限
	List<AuthInfo> selectUserInfoAllAuth(HashMap<String,Integer> map);//查询用户权限不分等级
    int deleteUserAuthById(Integer userId);//删除用户的所有权限
    int addUserAuth(UserAuth userAuth);
    int updateAuth(AuthInfo authInfo);//修改权限
    int addAuthInfo(AuthInfo authInfo);//添加权限
    int updateStateByAuthId(Integer authId);//恢复状态
    int updateStateByAuthIdOff(Integer authId);//禁用状态
    int deleteAuthByAuthId(Integer authId); //删除权限
}