package com.vote.dao;

import com.vote.model.Elector;
import com.vote.model.VoteLog;
import com.vote.model.Voter;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DaoVote extends dbUtil {

    /**
     * 查询所有 voter (never used)
     * @return
     * @throws Exception
     */
    public ArrayList<Voter> findAllVoters() throws Exception {
        Connection conn=dbUtil.getConnection();
        String sql = "select * from voter";
        PreparedStatement stmt= conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Voter> voterList = new ArrayList();
        while(rs.next()) {
            Voter voter = new Voter();
            voter.setId(rs.getInt("id"));
            voter.setName(rs.getString("name"));
            voter.setSex(rs.getString("sex"));
            voter.setVoteNum(rs.getInt("voteNum"));
            voterList.add(voter);
        }
        dbUtil.closeAll(rs, stmt, conn);
        return voterList;
    }

    /**
     * 检查账号是否重复
     * @param account
     * @return
     * @throws Exception
     */
    public boolean hasAccount(String account) throws Exception {
        Connection conn = dbUtil.getConnection();
        String sql = "select * from voter where account=?";
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1, account);

        ResultSet rs = stmt.executeQuery();
        boolean result=false;
        while(rs.next()) {
            result = true;
            break;
        }
        dbUtil.closeAll(rs,stmt,conn);
        return result;
    }

    /**
     * 添加 voter 账号
     * @param a
     * @param p
     * @return
     * @throws Exception
     */
    public int addVoter(String a, String p) throws Exception {
        Connection conn=dbUtil.getConnection();
        String sql = "insert into voter (account,pwd,voteNum) values(?,?,5)";
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1, a);
        stmt.setString(2, p);
        int resultSet = stmt.executeUpdate();

        dbUtil.closeAll(null, stmt, conn);
        return resultSet;
    }

    /**
     * 查询 voter 可投票次数
     * @param voteId
     * @param num
     * @return
     * @throws Exception
     */
    public int canVote(int voteId, int num) throws Exception {
        Connection conn = dbUtil.getConnection();

        String sql = "select voteNum from voter where id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, voteId);
        ResultSet rs = stmt.executeQuery();

        int voteNum=0;
        while (rs.next()){
            voteNum = rs.getInt("voteNum");
            break;
        }

        dbUtil.closeAll(rs,stmt,conn);
        return voteNum - num;
    }

    /**
     * 插入投票记录
     * @param voterId
     * @param electorId
     * @param isAnonymous
     * @return
     * @throws Exception
     */
    public int log(int voterId, int electorId, int isAnonymous) throws Exception {
        String sql = "insert into votelog(voterId, electorId, anonymous, CTime) values(?,?,?,?)";
        Connection conn = dbUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, voterId);
        stmt.setInt(2, electorId);
        stmt.setInt(3, isAnonymous);

        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date da_date = new Date();
        String nowDaDate = sdf.format(da_date);
        Timestamp daTs = Timestamp.valueOf(nowDaDate);

        stmt.setTimestamp(4, daTs);

        int flag = stmt.executeUpdate();

        int f =0;
        if(flag > 0) {

            sql = "update voter set voteNum=voteNum-1 where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, voterId);
            stmt.executeUpdate();

            sql = "update elector set electedNum=electedNum+1 where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, electorId);
            stmt.executeUpdate();

        } else {
            f = -1;
        }

        dbUtil.closeAll(null, stmt, conn);
        return f;
    }

    /**
     * 查询所有 elector
     * @return
     * @throws Exception
     */
    public ArrayList<Elector> findAllElector() throws Exception {
        Connection conn=dbUtil.getConnection();
        String sql = "select * from elector";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Elector> electorList = new ArrayList();
        while(rs.next()) {
            Elector elector = new Elector();
            elector.setId(rs.getInt("id"));
            elector.setName(rs.getString("name"));
            elector.setSex(rs.getString("sex"));
            elector.setElectedNum(rs.getInt("electedNum"));
            electorList.add(elector);
        }
        dbUtil.closeAll(rs, stmt, conn);
        return electorList;
    }

    /**
     * 查询所有记录 (never used)
     * @return
     * @throws Exception
     */
    public ArrayList<VoteLog> findAllLog() throws Exception {
        Connection conn = dbUtil.getConnection();
        String sql = "select * from votelog";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        ArrayList<VoteLog> voteLogList = new ArrayList();
        while(rs.next()) {

            VoteLog voteLog = new VoteLog();
            int anonymous = rs.getInt("anonymous");
            if(anonymous==0){
                //todo
            }
            voteLog.setVoterId(rs.getInt("voterId"));
            voteLog.setElectorId(rs.getInt("electorId"));
            voteLog.setAnonymous(anonymous);
            voteLog.setCTime(rs.getTimestamp("CTime"));

            voteLogList.add(voteLog);
        }
        dbUtil.closeAll(rs, stmt, conn);
        return voteLogList;
    }

    /**
     * 检查登陆
     * @param account
     * @param pwd
     * @return
     * @throws Exception
     */
    public Voter check(String account, String pwd) throws Exception {
        Connection conn = dbUtil.getConnection();
        String sql = "select * from voter where account=? and pwd=?";
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1, account);
        stmt.setString(2, pwd);

        ResultSet rs = stmt.executeQuery();
        Voter voter = new Voter();

        while(rs.next()) {
            voter.setId(rs.getInt("id"));
            voter.setAccount(rs.getString("account"));
            voter.setName(rs.getString("name"));
            voter.setSex(rs.getString("sex"));
            break;
        }
        dbUtil.closeAll(rs,stmt,conn);
        return voter;
    }
}
