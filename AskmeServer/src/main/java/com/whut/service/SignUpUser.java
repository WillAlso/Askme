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

public class SignUpUser extends HttpServlet {

    String resource = "mybatis-config.xml";
    SqlSession sqlSession = null;
    UserInfo user = null;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String t_identity = req.getParameter("identity");
        int identity = Integer.parseInt(t_identity);
        UserInfo userInfo = new UserInfo(0,email,password,0,"",identity);
        resp.setContentType("text/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        HashMap<String,Object> map = new HashMap<String, Object>();

        if (signUpUser(userInfo) == 1) {
            map.put("statuscode", 200);
        } else {
            map.put("statuscode", 400);
        }
        Gson gson = new Gson();
        String result = gson.toJson(map);
        printWriter.print(result);
        printWriter.close();
    }

    public int signUpUser(UserInfo userInfo){
        int result = 0;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            result = sqlSession.insert("LoginMapper.signUpUser",userInfo);
            sqlSession.commit();
        } catch (IOException e) {
            return 0;
        }
        return result;
    }
}
