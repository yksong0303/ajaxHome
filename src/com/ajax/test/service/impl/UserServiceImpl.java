package com.ajax.test.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.ajax.test.service.UserService;

public class UserServiceImpl implements UserService {

	public Map<String, String> doLogin(Map<String, String> user) {
		Map<String,String> rMap= new HashMap<String, String>();
		rMap.put("result", "fail");
		rMap.put("msg","아이디를 확인해주세요");
		if("test".contentEquals(user.get("id"))) {
			rMap.put("msg","비밀번호를 확인해주세요");
			if("test".contentEquals(user.get("pwd"))) {
				rMap.put("result","ok");
				rMap.put("msg","로그인 완료");
			}
		}
		return rMap;
	}

}


