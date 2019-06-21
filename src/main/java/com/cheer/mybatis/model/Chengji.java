package com.cheer.mybatis.model;

import java.io.Serializable;
import java.util.StringJoiner;

public class Chengji implements Serializable {
    private int id;
    //private String username;
    //private String Password;
    private int correct;
    private int error;
    private int miss;
    private int scores;
    private String pass;

    public Chengji(int id,int correct, int error, int miss, int scores, String pass) {
        this.id = id;
        this.correct = correct;
        this.error = error;
        this.miss = miss;
        this.scores = scores;
        this.pass = pass;
    }

    public Chengji() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   /* public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }*/


    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Chengji.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("correct=" + correct)
                .add("error=" + error)
                .add("miss=" + miss)
                .add("scores=" + scores)
                .add("pass='" + pass + "'")
                .toString();
    }
}
