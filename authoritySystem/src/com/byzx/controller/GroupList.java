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

import com.byzx.service.GroupInfoService;
import com.byzx.vo.GroupRole;
import com.byzx.vo.PageUtil;
import com.byzx.vo.UserGroup;
import com.byzx.vo.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/group")
public class GroupList {

	@Autowired
	public GroupInfoService groupInfoService;
	
	
	//分页展示 group：模糊字段
	@RequestMapping("/list.action")
	public void list(UserGroup group,PageUtil pageUtil,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("Params", group);//将模糊值存request
		HashMap<String,Object> map=new HashMap<String,Object>();
		group.setGroupName(group.getGroupName()==null?"":group.getGroupName());
		group.setGroupState(group.getGroupState()==null?"":group.getGroupState());
		System.out.println(group.getGroupName());
		System.out.println(group.getGroupState());
		map.put("UserGroup", group);
		StringBuffer param=new StringBuffer();
		param.append("groupName="+group.getGroupName());
		param.append("&groupState="+group.getGroupName());
		pageUtil.setParams(param);
		page(map,pageUtil,param,request,response); //调用分页方法，存储基本数据
	}	
	//封装分页方法
		public void page(HashMap<String, Object> map,PageUtil pageUtil, StringBuffer param, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			List<UserGroup> groups=groupInfoService.selectGrouplist(map);
			int count=groups.size(); //一共多少数据
			pageUtil.setPageSize(pageUtil.getPageSize()==null?5:pageUtil.getPageSize());
			pageUtil.setCurrNo(pageUtil.getCurrNo()==null?1:pageUtil.getCurrNo());
			map.put("page", pageUtil);
			List<UserGroup> groupsInfos=groupInfoService.selectGrouplist(map);
	  		PageUtil pages=new PageUtil(pageUtil.getPageSize(),count,pageUtil.getCurrNo(),groupsInfos,"group/list.action",param); 
	  		request.getSession().setAttribute("page", pages);//存值 jsp获得
	  		request.getSession().setAttribute("GroupInfo", groupsInfos);
	  		request.getRequestDispatcher("../pages/group-list.jsp").forward(request,response);
	}
	//禁用 或者启用
	@RequestMapping("/updategroupState.action")
	public void updategroupState(UserGroup group,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(group.getGroupState());
		group.setGroupState(group.getGroupState().equals("启用")?"0":"1");
		boolean flag=groupInfoService.updateUserStateById(group);
		System.out.println(flag);
		if(flag) {
			response.getWriter().print("<script>location.href='list.action';</script>");
		}else {
			response.sendRedirect("../pages/group-list.jsp");
		}
	}
	//删除
	@RequestMapping("/deletegroup.action")
	public void deletegroup(Integer groupId,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("groupId:"+groupId);
		boolean flag=groupInfoService.deletegroupById(groupId);
		System.out.println("flag:"+flag);
		if(flag) {
			response.getWriter().print("<script>location.href='list.action';</script>");
		}else {
			response.sendRedirect("../pages/group-list.jsp");
		}
	}
	//修改/添加信息--验证名称 地址 code 是否可用 
	@RequestMapping("/isgroupName.action")
	@ResponseBody
	public JSONObject isgroupName(UserGroup group,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(group.getGroupName());
		boolean flag=groupInfoService.selectgroupInfo(group);
		JSONObject json =new JSONObject();
		json.put("msg", flag);
		return json;
	}
	//修改信息
	@RequestMapping("/updategroup.action")
	@ResponseBody
	public JSONObject updategroup(UserGroup group,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(group.getGroupName());
		boolean flag=groupInfoService.updategroup(group);
		JSONObject json =new JSONObject();
		json.put("msg", flag);
		return json;
	}
	//添加信息
	@RequestMapping("/addgroup.action")
	@ResponseBody
	public JSONObject addgroup(UserGroup group,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInfo user=(UserInfo) request.getSession().getAttribute("USER");
		boolean flag=groupInfoService.addgroup(group);
		JSONObject json =new JSONObject();
		json.put("msg", flag);
		return json;
	}
	//查询用户组的角色
	@RequestMapping("/selectGroupRoleId.action")
	@ResponseBody
	public void selectGroupRoleId(Integer groupId,HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserGroup groupRoles=groupInfoService.selectGroupRoleId(groupId);
		JSONArray jsonarray=JSONArray.fromObject(groupRoles);
		response.getWriter().print(jsonarray);
	}
	@RequestMapping("/updateGroupRole.action")
	public void updateGroupRole(String roleIds,GroupRole groupRole,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=groupInfoService.updateGroupRole(roleIds,groupRole);
		if(flag) {
			response.getWriter().print("<script>location.href='list.action';</script>");
		}else {
			response.sendRedirect("../pages/group-list.jsp");
		}
	}
	

	
}

