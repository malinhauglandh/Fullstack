package org.ntnu.rest.service;

import org.ntnu.rest.entity.Calculation;
import org.ntnu.rest.entity.User;
import org.ntnu.rest.model.Equation;
import org.ntnu.rest.repository.CalculationRepository;
import org.ntnu.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationService {

    @Autowired
    private final CalculationRepository calculationRepository;

    @Autowired
    private final UserRepository userRepository;

    public CalculationService(CalculationRepository calculationRepository, UserRepository userRepository) {
        this.calculationRepository = calculationRepository;
        this.userRepository = userRepository;
    }

    public void save(Equation equation, User user) {
        Calculation calculation = new Calculation();
        calculation.setCalculation(equation.getEquation());
        calculation.setUser(user);
        this.calculationRepository.save(calculation);
    }

    public List<String> getCalculations(String username, int pageNumber){
        User user = userRepository.findById(username).get();
        Page<Calculation> calculations = calculationRepository.findByUserOrderByIdDesc(user, PageRequest.of(pageNumber, 10));
        return calculations
                .getContent()
                .stream()
                .map(Calculation::getCalculation)
                .toList();
    }
}