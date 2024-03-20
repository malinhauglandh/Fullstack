package org.ntnu.rest.controller;

import org.ntnu.rest.model.Equation;
import org.ntnu.rest.service.CalculationService;
import org.ntnu.rest.service.SolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CalculationController {

    @Autowired
    private SolverService solver;
    @Autowired
    private CalculationService calculationService;

    @GetMapping("/")
    public String testConnection(){
        Equation e = new Equation();
        e.setNum1("2");
        e.setOperator('+');
        e.setNum2("2");
        this.solver.solve(e);
        System.out.println(e.getEquation() + "=" + e.getAnswer());
        return "Success";
    }

    @PostMapping("/solve")
    public Equation solve( @RequestBody Equation equation){
        this.solver.solve(equation);
        return equation;
    }
    @GetMapping("calculations/{username}/{pageNumber}")
    public List<String> getCalculations(@PathVariable String username, @PathVariable int pageNumber){
        return calculationService.getCalculations(username, pageNumber);
    }
}
