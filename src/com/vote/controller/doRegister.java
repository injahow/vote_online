package com.vote.controller;

import com.vote.dao.DaoVote;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "doRegister", urlPatterns ="/register" )
public class doRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Type","text/plain; charset=utf-8");

        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");
        account = account==null?"":account;
        pwd = pwd==null?"":pwd;

        DaoVote daoVote = new DaoVote();
        try {
            boolean hasVote = daoVote.hasAccount(account);
            if(!hasVote){
                daoVote.addVoter(account, pwd);
                response.getWriter().print("success");
            }else{
                response.getWriter().print("账号重复！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
