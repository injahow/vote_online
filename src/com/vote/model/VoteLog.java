package com.vote.model;

import java.sql.Timestamp;

public class VoteLog {
    private int voterId;
    private int voterName;
    private int electorId;
    private int electorName;
    private int anonymous;
    private Timestamp CTime;

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public int getElectorId() {
        return electorId;
    }

    public void setElectorId(int electorId) {
        this.electorId = electorId;
    }

    public int isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(int anonymous) {
        this.anonymous = anonymous;
    }

    public Timestamp getCTime() {
        return CTime;
    }

    public void setCTime(Timestamp CTime) {
        this.CTime = CTime;
    }

    public int getVoterName() {
        return voterName;
    }

    public void setVoterName(int voterName) {
        this.voterName = voterName;
    }

    public int getElectorName() {
        return electorName;
    }

    public void setElectorName(int electorName) {
        this.electorName = electorName;
    }
}
