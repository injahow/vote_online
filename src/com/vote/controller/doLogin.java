package com.vote.controller;

import com.vote.dao.DaoVote;
import com.vote.model.Voter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "doLogin", urlPatterns ="/login" )
public class doLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // out
        response.setHeader("Content-Type","text/plain; charset=utf-8");

        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");
        account = account==null?"":account;
        pwd = pwd==null?"":pwd;

        DaoVote daoVote = new DaoVote();
        try {
            Voter voter = daoVote.check(account, pwd);
            int id = voter.getId();
            if(id!=0){
                request.getSession().setAttribute("account", voter);
                response.getWriter().print("success");
            }else{
                response.getWriter().print("用户名或密码错误！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


}
