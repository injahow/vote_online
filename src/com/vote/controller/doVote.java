package com.vote.controller;

import com.vote.dao.DaoVote;

import com.vote.model.Voter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "doVote", urlPatterns = "/vote")
public class doVote extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // out
        response.setHeader("Content-Type","text/plain; charset=utf-8");

        //1.检查投票次数
        String electorId = request.getParameter("electorId");
        String anonymous = request.getParameter("anonymous");//0-1
        String[] electorIds = request.getParameterValues("electorIds[]");
        electorId=electorId==null?"":electorId;
        anonymous=anonymous==null?"":anonymous;

        int isAnonymous = anonymous.equals("1") ? 1:0;

        DaoVote daoVote = new DaoVote();
        try {
            Voter account = (Voter)request.getSession().getAttribute("account");
            if(account==null){
                // 提示登陆
                response.getWriter().print("请登陆后投票！");
                return;
            }

            int voterId = account.getId();
            int num = electorIds==null?1:electorIds.length;

            int can_vote = daoVote.canVote(voterId, num);
            // sql：记录投票
            if(can_vote > -1) {
                if(electorIds==null) {
                    if(electorId==""){
                        response.getWriter().print("请选择被投票的人！");
                        return;
                    }else{
                        daoVote.log(voterId, Integer.parseInt(electorId), isAnonymous);
                    }
                }else{
                    for (int i = 0; i < electorIds.length; i++) {
                        daoVote.log(voterId, Integer.parseInt(electorIds[i]), isAnonymous);
                    }
                }
                response.getWriter().print("success");
            } else {
                // 提示没有投票数
                response.getWriter().print("剩余投票数为"+(can_vote+num)+"，无法投票！");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
