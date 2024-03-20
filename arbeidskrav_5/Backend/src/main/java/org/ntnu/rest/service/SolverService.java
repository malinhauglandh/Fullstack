package org.ntnu.rest.service;

import org.ntnu.rest.entity.Calculation;
import org.ntnu.rest.model.Equation;
import org.ntnu.rest.repository.CalculationRepository;
import org.ntnu.rest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolverService {

    private final CalculationRepository calculationRepository;
    private final UserRepository userRepository;

    @Autowired
    public SolverService(CalculationRepository calculationRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.calculationRepository = calculationRepository;
    }
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
        Calculation c = new Calculation();
        c.setCalculation(equation.getEquation() + "=" + equation.getAnswer());
        c.setUser(userRepository.findById(equation.getUser()).get());
        calculationRepository.save(c);
        logger.info("Result of equation: " + equation.getAnswer());
    }
}

