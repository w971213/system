package com.byzx.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.byzx.service.RoleInfoService;
import com.byzx.vo.AuthInfo;
import com.byzx.vo.PageUtil;
import com.byzx.vo.Role;
import com.byzx.vo.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/role")
public class Rolelist {

	@Autowired
	public RoleInfoService roleInfoService;
	
	
	//分页展示 role：模糊字段
	@RequestMapping("/list.action")
	public void list(Role role,PageUtil pageUtil,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("Params", role);//将模糊值存request
		HashMap<String,Object> map=new HashMap<String,Object>();
		role.setRoleName(role.getRoleName()==null?"":role.getRoleName());
		role.setRoleState(role.getRoleState()==null?"":role.getRoleState());
		map.put("role", role);
		StringBuffer param=new StringBuffer();
		param.append("roleName="+role.getRoleName());
		param.append("&roleState="+role.getRoleState());
		pageUtil.setParams(param);
		page(map,pageUtil,param,request,response); //调用分页方法，存储基本数据
	}	
	//封装分页方法
		public void page(HashMap<String, Object> map,PageUtil pageUtil, StringBuffer param, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			List<Role> roles=roleInfoService.selectRolelist(map);
			int count=roles.size(); //一共多少数据
			pageUtil.setPageSize(pageUtil.getPageSize()==null?5:pageUtil.getPageSize());
			pageUtil.setCurrNo(pageUtil.getCurrNo()==null?1:pageUtil.getCurrNo());
			map.put("page", pageUtil);
			List<Role> rolesInfos=roleInfoService.selectRolelist(map);
	  		PageUtil pages=new PageUtil(pageUtil.getPageSize(),count,pageUtil.getCurrNo(),rolesInfos,"role/list.action",param); 
	  		request.getSession().setAttribute("page", pages);//存值 jsp获得
	  		request.getSession().setAttribute("roles", rolesInfos);
	  		request.getRequestDispatcher("../pages/role-list.jsp").forward(request,response);
	}
	//禁用 或者启用
	@RequestMapping("/updateRoleState.action")
	public void updateRoleState(Role role,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(role.getRoleState());
		role.setRoleState(role.getRoleState().equals("启用")?"0":"1");
		boolean flag=roleInfoService.updateUserStateById(role);
		System.out.println(flag);
		if(flag) {
			response.getWriter().print("<script>location.href='list.action';</script>");
		}else {
			response.sendRedirect("../pages/role-list.jsp");
		}
	}
	//删除
	@RequestMapping("/deleteRole.action")
	public void deleteRole(Integer roleId,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("roleId:"+roleId);
		boolean flag=roleInfoService.deleteRoleById(roleId);
		System.out.println("flag:"+flag);
		if(flag) {
			response.getWriter().print("<script>location.href='list.action';</script>");
		}else {
			response.sendRedirect("../pages/role-list.jsp");
		}
	}
	//修改/添加信息--验证名称 地址 code 是否可用 
	@RequestMapping("/isRoleName.action")
	@ResponseBody
	public JSONObject isRoleName(Role role,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=roleInfoService.selectRoleInfo(role);
		JSONObject json =new JSONObject();
		json.put("msg", flag);
		return json;
	}
	//修改信息
	@RequestMapping("/updateRole.action")
	@ResponseBody
	public JSONObject updateRole(Role role,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=roleInfoService.updateRole(role);
		JSONObject json =new JSONObject();
		json.put("msg", flag);
		return json;
	}
	//添加信息
	@RequestMapping("/addRole.action")
	@ResponseBody
	public JSONObject addRole(Role role,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInfo user=(UserInfo) request.getSession().getAttribute("USER");
		role.setCreateBy(user.getUserId());
		boolean flag=roleInfoService.addRole(role);
		JSONObject json =new JSONObject();
		json.put("msg", flag);
		return json;
	}
	//权限跳转 
	@RequestMapping("/selectRoleAuth.action")
	public void selectRoleAuth(HashMap<String,String> map,Integer roleId,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		map.put("authState", "1");
		List<AuthInfo> authInfos= roleInfoService.selectAllAuthInfo(map);//所有的权限
		List<AuthInfo> roleAuths= roleInfoService.selectAuthByRoleId(roleId);
		JSONArray jsonarray= new JSONArray();
		for(AuthInfo authInfo:authInfos) {
			JSONObject json =new JSONObject();
			json.put("id", authInfo.getAuthId());
			json.put("pId", authInfo.getParentId());
			json.put("name", authInfo.getAuthName());
			for(AuthInfo userAuth:roleAuths) {
				if(userAuth.getAuthId()==authInfo.getAuthId()) {
					json.put("checked", "true");
				}
			}
			jsonarray.add(json);
		}
		request.setAttribute("RoleId", roleId);
		request.setAttribute("data", jsonarray);
		request.getRequestDispatcher("../pages/showRoleAuth.jsp").forward(request,response);
		
	}
	//清空权限
	@RequestMapping("/deleteAuth.action")
	public void deleteRoleAuthById(Integer roleId,HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean flag=roleInfoService.deleteRoleAuthById(roleId);
		if(flag) {
			response.getWriter().print("<script>location.href='list.action';</script>");
		}else {
			response.getWriter().print("<script>location.href='showAllAuth.action';</script>");
		}
	}
	@RequestMapping("/updateRoleAuth.action")
	public void updateRoleAuth(Integer roleId,String ids,HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean flag=roleInfoService.updateRoleAuth(roleId, ids);
		if(flag) {
			response.getWriter().print("<script>location.href='list.action';</script>");
		}else {
			response.getWriter().print("<script>location.href='showAllAuth.action';</script>");
		}
	}
}
