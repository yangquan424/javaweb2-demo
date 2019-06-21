package com.cheer.mybatis.model;

import java.io.Serializable;
import java.util.StringJoiner;

public class Exam implements Serializable {
    private int id;
    private String question;
    private String A;
    private String B;
    private String C;
    private String D;
    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Exam.class.getSimpleName() + "[", "]")
                .add("question='" + question + "'")
                .add("A='" + A + "'")
                .add("B='" + B + "'")
                .add("C='" + C + "'")
                .add("D='" + D + "'")
                .add("answer='" + answer + "'")
                .toString();
    }
}
