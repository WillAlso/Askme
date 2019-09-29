package com.whut.dao;

import com.whut.bean.UserInfo;

public interface LoginDao {
    UserInfo loginUser(String username);
}
