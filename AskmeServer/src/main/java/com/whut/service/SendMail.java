package com.whut.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SendMail extends HttpServlet {

    public boolean sendmessage(String email, String code){
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAI4FiNMTbdbNQwrFNwbpb1", "mgNZmtlsnLhZIjV7BdYxA7ugCqZDaX");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        request.setAccountName("askme@www.fenggangguo.xyz");
        request.setFromAlias("AskMe运营方");
        request.setAddressType(1);
        request.setTagName("askme");
        request.setReplyToAddress(true);
        request.setToAddress(email);
        String content = "【AskMe】尊敬的用户：您的校验码：" + code
                + "，工作人员不会索取，请勿泄露。";
        request.setSubject("AskMe验证码");
        request.setHtmlBody(content);
        try {
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ClientException e) {
            return false;
        }
        return true;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        PrintWriter pw = resp.getWriter();
        SendMail sendMail = new SendMail();
        String code = String.valueOf(new Random().nextInt(10000));
        Map<String,Object> map = new HashMap<String, Object>();
        Gson gson = new Gson();
        int statuscode = 200;
        if (!sendMail.sendmessage(email,code)){
            statuscode = 400;
        }
        map.put("statuscode",statuscode);
        map.put("email",email);
        map.put("code",code);
        String msg = gson.toJson(map);
        pw.print(msg);
        pw.close();
    }

}
