package com.byzx.test;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.byzx.mapper.AuthInfoMapper;
import com.byzx.mapper.RoleInfoMapper;
import com.byzx.mapper.UserInfoMapper;
import com.byzx.vo.AuthInfo;
import com.byzx.vo.Role;
import com.byzx.vo.UserInfo;
import com.byzx.vo.UserRole;

public class TestItem {
	public static void main(String[] args){
		ApplicationContext ac =new ClassPathXmlApplicationContext("Spring.xml");
		System.out.println("***************");
		RoleInfoMapper rim=(RoleInfoMapper) ac.getBean("roleInfoMapper");
		HashMap map= new HashMap();
		 List<Role> ros=rim.selectRolelist(map);
		 for(Role role:ros) {
			 System.out.println(role.getUserInfo().getUserCode());
		 }
		//UserInfoMapper uim=(UserInfoMapper)ac.getBean("userInfoMapper");
		//AuthInfoMapper afm=(AuthInfoMapper)ac.getBean("authInfoMapper");
		//List<AuthInfo> authInfos=afm.selectAllAuthInfo(0);
		/*for(AuthInfo authInfo:authInfos) {
			System.out.println(authInfo.getAuthName());
		}*/
		/*UserRole userInfos=uim.selectUserRole(1);
		List<Role> userRoles=(List<Role>) uim.selectUserRole(1);
		for(Role userRole:userInfos.getRole()) {
			System.out.println(userRole.getRoleName());
		}
		*/
		
		
		/*@RequestMapping("/selectColumn.action")//模糊查询通道
		public void page(PageUtil pageUtil,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getSession().setAttribute("Params", pageUtil);//将模糊字段存值
			PageUtil puil=(PageUtil) request.getSession().getAttribute("page");//获取上一页的分页数值
			puil.setColumn(pageUtil.getColumn());
			puil.setStateId(pageUtil.getStateId());
			puil.setGroupId(pageUtil.getGroupId());
			PageUtil pu=page1(map,param,request,response);
			puil.setLimitIndex(pu.getLimitIndex());
			request.getSession().setAttribute("UserList",userInfoService.selectByPage(puil));
			response.sendRedirect("../pages/user-list.jsp");
		}*/
		//分页
	}
}
