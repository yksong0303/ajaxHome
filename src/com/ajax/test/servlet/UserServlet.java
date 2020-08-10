package com.ajax.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		}
	}


}
