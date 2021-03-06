package com.rowingMachineMVP.user.service.impl;

import java.util.UUID;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rowingMachineMVP.user.mapper.UserInfoMapper;
import com.rowingMachineMVP.user.service.UserService;
import com.rowingMachineMVP.user.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public int insertUserInfo(UserVO param) {
		String userId = param.getUserId();
		if (StringUtils.isEmpty(userId)) {
			String uuid = UUID.randomUUID().toString();
			param.setUserId(uuid);
			param.setUserNm("GUEST");
		} else {
			String[] userNm = userId.split("@");
			param.setUserNm(userNm[0]);
		}
		return userInfoMapper.insertUserInfo(param);
	}

	@Override
	public UserVO getUserInfo(String userId) {
		return userInfoMapper.getUserInfo(userId);
	}

}
