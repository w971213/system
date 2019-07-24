package com.byzx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.byzx.service.UserInfoService;
import com.byzx.vo.AuthInfo;
import com.byzx.vo.PageUtil;
import com.byzx.vo.Role;
import com.byzx.vo.UserGroup;
import com.byzx.vo.UserInfo;
import com.byzx.vo.UserList;
import com.byzx.vo.UserRole;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class User {
	@Autowired
	public UserInfoService userInfoService;
	
	//验证登录信息
	@RequestMapping("/isAvailableUser.action")
	@ResponseBody
	public JSONObject isAvailableUser(UserInfo userInfo,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=userInfoService.selectUserByUserCode(userInfo);
		if(flag) {
			UserInfo ui=userInfoService.selectUserInfoByUserCode(userInfo);
			request.getSession().setAttribute("USER",ui );
		}
		JSONObject json=new JSONObject();
		json.put("res", flag);
		return json;
	}
	//验证验证码
	@RequestMapping("/validCode.action")
	@ResponseBody
	public JSONObject validCode(String vCode,HttpServletRequest request, HttpServletResponse response) {
		String rand= (String) request.getSession().getAttribute("rand01");
		boolean flag=vCode.equals(rand)?true:false;
		JSONObject json=new JSONObject();
		json.put("msg", flag);
		return json;
	}
	//主页面
	@RequestMapping("/index.action")
	public void index(HashMap<String,Integer> map,UserInfo userInfo,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userInfo=(UserInfo) request.getSession().getAttribute("USER");
		map.put("userId", userInfo.getUserId());
		map.put("groupId", userInfo.getGroupId());
		map.put("authId", 0);
		List<AuthInfo> firstAuthInfos=userInfoService.selectUserInfoAuth(map);
		for(AuthInfo authInfo:firstAuthInfos ) {
			map.put("authId", authInfo.getAuthId());
			List<AuthInfo> secondAuthInfos=userInfoService.selectUserInfoAuth(map);
			authInfo.setChildList(secondAuthInfos);
		}
		request.getSession().setAttribute("firstList", firstAuthInfos);
		response.sendRedirect("../pages/index.jsp");
	}
	//用户列表展示
	@RequestMapping("/list.action")
	public void list(UserInfo userInfo,PageUtil pageUtil,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setAttribute("Params", userInfo);//将模糊值存request
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("user", userInfo);
			map.put("page", pageUtil);
			userInfo.setUserCode(userInfo.getUserCode()==null?"":userInfo.getUserCode());
			userInfo.setUserType(userInfo.getUserType()==null?"":userInfo.getUserType());
			userInfo.setUserState(userInfo.getUserState()==null?"":userInfo.getUserState());
			StringBuffer param=new StringBuffer();
			param.append("userCode="+userInfo.getUserCode());
			param.append("&userType="+userInfo.getUserType());
			param.append("&userState="+userInfo.getUserState());
			pageUtil.setParams(param); //将模糊值存入Stringbuffer中
			page(map,param,request,response); //调用分页方法，存储基本数据
	}
	//封装分页方法
	public void page(HashMap<String, Object> map, StringBuffer param, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		List<UserList> userList=userInfoService.selectAllUser(map);
		request.getSession().setAttribute("downs", userList);
		int count=userList.size(); //一共多少数据
		int num=((PageUtil) map.get("page")).getPageSize()==null?5:((PageUtil) map.get("page")).getPageSize();//每页显示多少条
		int currNum=((PageUtil) map.get("page")).getCurrNo()==null?1:((PageUtil) map.get("page")).getCurrNo();  //当前第几页
		((PageUtil) map.get("page")).setPageSize(num);
		((PageUtil) map.get("page")).setCurrNo(currNum);
		List<UserList> userInfos=userInfoService.selectByPage(map);
  		PageUtil page=new PageUtil(num,count,currNum,userInfos,"user/list.action",param); 
  		request.getSession().setAttribute("page", page);//存值 jsp获得
  		List<Role> roles=userInfoService.selectAllRole();
  		request.setAttribute("userType",roles);	
  		List<UserGroup> userGroup=userInfoService.selectAllGroup();//获取分组
		request.setAttribute("Group", userGroup);
		request.getRequestDispatcher("../pages/user-list.jsp").forward(request,response);
	}
	//禁用或者启用
	@RequestMapping("/updateUserState.action")
	public void updateUserState(UserInfo userInfo,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userInfo.setUserState(userInfo.getUserState().equals("启用")?"0":"1");
		boolean flag=userInfoService.updateUserStateById(userInfo);
		if(flag) {
			response.getWriter().print("<script>location.href='list.action';</script>");
		}else {
			response.getWriter().print("<script>alert('修改失败');location.href='../pages/user-list.jsp';</script>");
		}
	}
	//删除
	@RequestMapping("/deleteUser.action")
	public void deleteuser(UserInfo userInfo,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		boolean flag=userInfoService.deleteUserInfo(userInfo.getUserId());
		if(flag) {
			out.print("<script>location.href='list.action';</script>");
		}else {
			out.print("<script>alert('删除失败');location.href='../pages/user-list.jsp';</script>");
		}
	}
	//重置密码
	@RequestMapping("/updateUserPwd.action")
	public void updateUserPwd(UserInfo userInfo,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		boolean flag=userInfoService.updateUserPwdById(userInfo.getUserId());
		if(flag) {
			out.print("<script>location.href='list.action';</script>");
		}else {
			System.out.println("我走这条路");
			out.print("<script>alert('重置失败');location.href='../pages/user-list.jsp';</script>");
		}
			
	}
	//修改信息
	@RequestMapping("/updateUserInfo.action")
	public void updateUserInfo(UserInfo userInfo,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		boolean flag=userInfoService.updateUserInfoById(userInfo);
		if(flag) {
			out.print("<script>location.href='list.action';</script>");
		}else {
			out.print("<script>alert('修改失败');location.href='../pages/user-list.jsp';</script>");
		}
	}
	//添加用户
	@RequestMapping("/addUserInfo.action")
	public void addUserInfo(UserInfo userInfo,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		UserInfo user=(UserInfo) request.getSession().getAttribute("USER");
		userInfo.setCreateBy(user.getUserId());
		boolean flag=userInfoService.addUserInfo(userInfo);
		if(flag) {
			out.print("<script>location.href='list.action';</script>");
		}else {
			out.print("<script>alert('添加失败');location.href='../pages/user-list.jsp';</script>");
		}
	}
	//添加用户用户名验证
	@RequestMapping("/validCodeUserCode.action")
	@ResponseBody
	public JSONObject validCodeUserCode(String nameCode,HttpServletRequest request, HttpServletResponse response) {
		boolean flag=userInfoService.selectUserInfoByUserCode(nameCode);
		JSONObject json =new JSONObject();
		json.put("msg", flag);
		return json;
	}
	
	//查询角色并保存 异步
		@RequestMapping("/updateUserRole.action")
		@ResponseBody
		public void updateUserRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
			List<Role> roles=userInfoService.selectAllRole();
			JSONArray jsonarray=JSONArray.fromObject(roles);
			response.getWriter().print(jsonarray);
		}
		//查询用户的角色保存
		@RequestMapping("/updateUserRole1.action")
		@ResponseBody
		public void updateUserRole1(Integer userId,HttpServletRequest request, HttpServletResponse response) throws IOException {
			UserRole userRoles=userInfoService.selectUserRole(userId); 
			JSONArray jsonarray=JSONArray.fromObject(userRoles);
			response.getWriter().print(jsonarray);
		}
		//将修改后的用户角色分配带过来进行设置
		@RequestMapping("/updateUserRole2.action")
		public void updateUserRole2(Integer userId,String roleIds,HttpServletRequest request, HttpServletResponse response) throws IOException {
			PrintWriter out=response.getWriter();
			boolean flag=userInfoService.addUserRole(userId,roleIds);
			if(flag) {
				out.print("<script>location.href='list.action';</script>");
				return;
			}else {
				out.print("<script>alert('更改失败！');location.href='list.action';</script>");
				return;
		   }
		}
		//查找用户信息By userId
		@RequestMapping("/selectUserInfoById")
		@ResponseBody
		public JSONObject selectUserInfoById(Integer userId,HttpServletRequest request, HttpServletResponse response) {
			UserInfo userInfo=userInfoService.selectUserInfoById(userId);
			System.out.println(userInfo.getNickName());
			JSONObject json=new JSONObject();
			json.put("UserInfo", userInfo);
			return json;	
		}
		//查找用户权限
		@RequestMapping("/selectUserAuth.action")
		public void selectUserAuth(HashMap<String,Integer> map,Integer userId,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			UserInfo userInfo=userInfoService.selectUserInfoById(userId);
			map.put("userId", userInfo.getUserId());
			map.put("groupId", userInfo.getGroupId());
			List<AuthInfo> userAuthInfos=userInfoService.selectUserInfoAllAuth(map);
			map.put("authState", 1);
			List<AuthInfo> authInfos=userInfoService.selectAllAuthInfo(map);
			JSONArray jsonarray= new JSONArray();
			for(AuthInfo authInfo:authInfos) {
				JSONObject json =new JSONObject();
				json.put("id", authInfo.getAuthId());
				json.put("pId", authInfo.getParentId());
				json.put("name", authInfo.getAuthName());
				for(AuthInfo userAuthInfo:userAuthInfos) {
					if(userAuthInfo.getAuthId()==authInfo.getAuthId()) {
						json.put("checked", "true");
					}
				}
				jsonarray.add(json);
			}
			request.setAttribute("UserInfo", userInfo);
			request.setAttribute("data", jsonarray);
			request.getRequestDispatcher("../pages/updateAuth.jsp").forward(request,response);
		}
		//更改权限
		@RequestMapping("/updateAuth.action")
		public void updateAuth(UserInfo userInfo,String ids,HttpServletRequest request, HttpServletResponse response) throws IOException {
			boolean flag=userInfoService.updateAuth(userInfo,ids);
			if(flag) {
				response.getWriter().print("<script>location.href='list.action';</script>");
			}else {
				response.getWriter().print("<script>location.href='list.action';</script>");
			}
		}
		//展示所有权限
		@RequestMapping("/showAllAuth.action")
		public void showAllAuth(HashMap<String,Integer> map,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			map.put("authState", 0);
			List<AuthInfo> authInfos=userInfoService.selectAllAuthInfo(map);
			JSONArray jsonarray= new JSONArray();
			for(AuthInfo authInfo:authInfos) {
				JSONObject json =new JSONObject();
				json.put("id", authInfo.getAuthId());
				json.put("pId", authInfo.getParentId());
				json.put("name", authInfo.getAuthName());
				json.put("state", authInfo.getAuthState());
				json.put("desc", authInfo.getAuthDesc());
				jsonarray.add(json);
			}
			request.setAttribute("AuthInfo", jsonarray);
			request.getRequestDispatcher("../pages/authTree.jsp").forward(request,response);
		}
		//删除用户个人权限
		@RequestMapping("/deleteUserAuth.action")
		public void deleteUserAuthById(Integer userId,String ids,HttpServletRequest request, HttpServletResponse response) throws IOException {
			boolean flag=userInfoService.deleteUserAuthById(userId);
			if(flag) {
				response.getWriter().print("<script>location.href='list.action';</script>");
			}else {
				response.getWriter().print("<script>location.href='list.action';</script>");
			}
		}
	
	
	
	
	
	
	
	
	
	
}
