package com.byzx.serviceimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byzx.mapper.AuthInfoMapper;
import com.byzx.service.AuthInfoService;
import com.byzx.vo.AuthInfo;
@Service
public class AuthInfoImpl implements AuthInfoService {
	
	@Autowired
	public AuthInfoMapper authInfoMapper;

	@Override
	public boolean updateAuth(AuthInfo authInfo) {
		int num=authInfoMapper.updateAuth(authInfo);
		return num==1?true:false;
	}

	@Override
	public boolean addAuthInfo(AuthInfo authInfo) {
		int num=authInfoMapper.addAuthInfo(authInfo);
		return num==1?true:false;
	}

	@Override
	public  boolean selectAllAuthInfo(HashMap<String, String> map) {
		List<AuthInfo> authInfo=authInfoMapper.selectAllAuthInfo(map);
		return authInfo.size()==0?true:false;
	}

	@Override
	public boolean updateStateByAuthId(Integer authId) {
		int num=authInfoMapper.updateStateByAuthId(authId);
		return num==1?true:false;
	}

	@Override
	public boolean deleteAuthByAuthId(Integer authId) {
		int num=authInfoMapper.deleteAuthByAuthId(authId);
		return num==1?true:false;
	}

	@Override
	public boolean updateStateByAuthIdOff(Integer authId) {
		int num=authInfoMapper.updateStateByAuthIdOff(authId);
		return num==1?true:false;
	}
	
	
}
