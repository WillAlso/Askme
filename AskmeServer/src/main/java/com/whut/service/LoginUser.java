package com.whut.service;

import com.google.gson.Gson;
import com.whut.bean.UserInfo;
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
import java.util.HashMap;

public class LoginUser extends HttpServlet {

    String resource = "mybatis-config.xml";
    SqlSession sqlSession = null;
    UserInfo user = null;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        resp.setContentType("text/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        UserInfo user = loginUser(username);
        HashMap<String,Object> map = new HashMap<String, Object>();
        if (user == null){
            map.put("statuscode",400);      //用户不存在
        } else if (!user.getPassword().equals(password)) {
            map.put("statuscode",401);      //密码错误
        }else {
            map.put("statuscode",200);
        }
        Gson gson = new Gson();
        String result = gson.toJson(map);
        System.out.println(result);
        printWriter.print(result);
        printWriter.close();
    }

    public UserInfo loginUser(String username){
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            user = sqlSession.selectOne("LoginMapper.loginUser",username);
        } catch (IOException e) {
            user = null;
        }
        return user;
    }
}
