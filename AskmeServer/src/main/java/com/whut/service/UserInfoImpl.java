package com.whut.service;

import com.google.gson.Gson;
import com.whut.bean.UserInfo;
import com.whut.dao.UserInfoDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserInfoImpl extends HttpServlet implements UserInfoDao {

    String resource = "mybatis-config.xml";
    SqlSession sqlSession = null;
    UserInfo user = null;

    public UserInfo findUserById(int userid) {
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            user = sqlSession.selectOne("UserMapper.findUserById",userid);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<UserInfo> findAllUser() {
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            userInfos = sqlSession.selectList("UserMapper.findAllUser");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfos;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        List<UserInfo> userInfos = (new UserInfoImpl()).findAllUser();
        Gson gson = new Gson();
        String result = gson.toJson(userInfos);
        printWriter.print(result);
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
