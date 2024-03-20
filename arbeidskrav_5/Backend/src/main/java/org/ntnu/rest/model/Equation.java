package org.ntnu.rest.model;

import org.ntnu.rest.entity.User;

public class Equation {
    private String num1, num2;
    private char operator;
    private double answer;

    private String user;
    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public Equation(){}

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getEquation(){
        return getNum1() + getOperator() + getNum2() + "=" + getAnswer();
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
