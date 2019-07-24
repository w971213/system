package com.byzx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.byzx.service.AuthInfoService;
import com.byzx.service.UserInfoService;
import com.byzx.vo.AuthInfo;
import com.byzx.vo.UserInfo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/auth")
public class Auth {
	
	@Autowired
	public AuthInfoService authInfoService;
	
	//修改权限的名称和描述
	@RequestMapping("/updateAuth.action")
	public void updateAuth(AuthInfo authInfo,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	boolean flag=authInfoService.updateAuth(authInfo);
		if(flag) {
			response.getWriter().print("<script>location.href='../user/showAllAuth.action';</script>");
		}else {
			response.getWriter().print("<script>alert('修改失败！');location.href='../user/showAllAuth.action';</script>");
		}
	}	
	//验证更改的用户名是否存在
	@RequestMapping("/isAvailableAuthName.action")
	@ResponseBody
	public JSONObject isAvailableAuthName(HashMap<String,String> map,AuthInfo authInfo,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		map.put("authName", authInfo.getAuthName());
		map.put("authCode", authInfo.getAuthCode());
		map.put("authUrl", authInfo.getAuthUrl());
		boolean flag=authInfoService.selectAllAuthInfo(map);
		JSONObject json=new JSONObject();
		json.put("msg",flag);
		return json;
	}
	@RequestMapping("/insertAuth.action")
	@ResponseBody
	public JSONObject insertAuth(AuthInfo authInfo,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println(authInfo.getAuthName());
	System.out.println(authInfo.getParentId());
	boolean flag=authInfoService.addAuthInfo(authInfo);
	JSONObject json =new JSONObject();
	json.put("msg",flag);
	return json;
	}
	//回复权限
	@RequestMapping("/reinAuth.action")
	public void reinAuth(Integer authId,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	boolean flag=authInfoService.updateStateByAuthId(authId);
		if(flag) {
			response.getWriter().print("<script>location.href='../user/showAllAuth.action';</script>");
		}else {
			response.getWriter().print("<script>alert('修改失败！');location.href='../user/showAllAuth.action';</script>");
		}
	}	
	//禁用权限
	@RequestMapping("/banAuth.action")
	public void banAuth(Integer authId,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	boolean flag=authInfoService.updateStateByAuthIdOff(authId);
		if(flag) {
			response.getWriter().print("<script>location.href='../user/showAllAuth.action';</script>");
		}else {
			response.getWriter().print("<script>alert('修改失败！');location.href='../user/showAllAuth.action';</script>");
		}
	}	
	//删除权限
	@RequestMapping("/deleteAuth.action")
	@ResponseBody
	public JSONObject deleteAuth(Integer authId,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	boolean flag=authInfoService.deleteAuthByAuthId(authId);
	JSONObject json =new JSONObject();
	json.put("msg",flag);
	return json;
	}	
}
