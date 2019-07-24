package com.byzx.service;

import java.util.HashMap;
import java.util.List;

import com.byzx.vo.AuthInfo;

public interface AuthInfoService {
	boolean updateAuth(AuthInfo authInfo);//修改权限
    boolean addAuthInfo(AuthInfo authInfo);//添加权限
    boolean selectAllAuthInfo(HashMap<String,String> map);//验证权限名字是否可用
    boolean  updateStateByAuthId(Integer authId);//恢复状态
    boolean deleteAuthByAuthId(Integer authId); //删除权限
    boolean updateStateByAuthIdOff(Integer authId);//禁用权限
}
