package com.vote.controller;

import com.vote.dao.DaoVote;
import com.vote.model.Elector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "initVote", urlPatterns = "/init_vote")
public class initVote extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoVote daoVote = new DaoVote();
        ArrayList<Elector> electorList;
        try {
            electorList = daoVote.findAllElector();
            request.setAttribute("electorList",electorList);
            request.getRequestDispatcher("init_vote.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
