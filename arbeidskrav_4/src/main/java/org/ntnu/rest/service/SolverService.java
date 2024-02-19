package org.ntnu.rest.service;

import org.ntnu.rest.model.Equation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SolverService {
    Logger logger = LoggerFactory.getLogger(SolverService.class);

    public void solve(Equation equation){
        logger.info("Received calculation: " + equation.getEquation());
        switch (equation.getOperator()) {
            case '+' -> equation.setAnswer(Double.parseDouble(equation.getNum1()) + Double.parseDouble(equation.getNum2()));
            case '-' -> equation.setAnswer(Double.parseDouble(equation.getNum1()) - Double.parseDouble(equation.getNum2()));
            case '*' -> equation.setAnswer(Double.parseDouble(equation.getNum1()) * Double.parseDouble(equation.getNum2()));
            case '/' -> {
                if (equation.getNum2().equals("0")) throw new ArithmeticException("Division by zero");
                equation.setAnswer(Double.parseDouble(equation.getNum1()) / Double.parseDouble(equation.getNum2()));
            }
            default -> throw new IllegalArgumentException("Unsupported operator" + equation.getOperator());
        }
        logger.info("Result of equation: " + equation.getAnswer());
    }
}

