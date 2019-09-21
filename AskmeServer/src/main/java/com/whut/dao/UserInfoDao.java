package com.whut.dao;

import com.whut.bean.UserInfo;

import java.util.List;

public interface UserInfoDao {
    UserInfo findUserById(int userid);
    List<UserInfo> findAllUser();
}
