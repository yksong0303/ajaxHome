package com.ajax.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ajax.test.service.UserService;
import com.ajax.test.service.impl.UserServiceImpl;
import com.google.gson.Gson;


@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		private UserService us = new UserServiceImpl();
       private static Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getRequestURI().lastIndexOf("/")+1;
		String cmd = request.getRequestURI().substring(idx);
		PrintWriter pw = response.getWriter();
		if("checkid".equals(cmd)) {
			String uiId= request.getParameter("ui_id");
			Map<String, String> rMap= us.checkId(uiId);
			pw.println(gson.toJson(rMap));
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getRequestURI().lastIndexOf("/")+1;
		String cmd = request.getRequestURI().substring(idx);
		if("login".equals(cmd)) {
		BufferedReader br = request.getReader();//중요
		String str;
		StringBuffer sb = new StringBuffer();//sql직접 써서 하는것보다 속도차이남
		while((str=br.readLine())!=null) {
			sb.append(str);
		}
		Map<String,String> pMap=gson.fromJson(sb.toString(),Map.class);
		Map<String,String> rMap=us.doLogin(pMap);
		if("ok".equals(rMap.get("result"))) {
			HttpSession session = request.getSession();
			session.setAttribute("id",pMap.get("id"));
		}
		response.getWriter().append(gson.toJson(rMap));
		}else if("logout".equals(cmd)) {
			HttpSession session = request.getSession();
			session.invalidate();
			Map<String,String> rMap = new HashMap<String, String>();
			rMap.put("msg","로그아웃됨");
			response.getWriter().append((gson.toJson(rMap)));
		}else if ("join".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			if(uiId==null||uiId.trim().length()<4) {
				throw new ServletException("올바르지 않은 아이디 값");
			}
			String uiPwd = request.getParameter("ui_password").toString();
			String uiName = request.getParameter("ui_name").toString();
			String uiAge = request.getParameter("ui_age").toString();
			String uiBirth = request.getParameter("ui_birth").toString();
			uiBirth =uiBirth.replace("-","");
			String uiPhone = request.getParameter("ui_phone").toString();
			String uiEmail = request.getParameter("ui_email").toString();
			String uiNickname = request.getParameter("ui_nickname").toString();
			Map<String, Object> user = new HashMap<>();
			user.put("ui_id",uiId);
			user.put("ui_password",uiPwd);
			user.put("ui_age",uiAge);
			user.put("ui_name",uiName);
			user.put("ui_birth",uiBirth);
			user.put("ui_phone",uiPhone);
			user.put("ui_email",uiEmail);
			user.put("ui_nickname",uiNickname);
			Map<String,Object> rMap = us.joinUserInfo(user);
			request.setAttribute("rMap",rMap);
		
			
		}
	}


}
