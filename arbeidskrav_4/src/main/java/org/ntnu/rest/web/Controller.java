package org.ntnu.rest.web;

import org.ntnu.rest.model.Equation;
import org.ntnu.rest.service.SolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class Controller {

    @Autowired
    private SolverService solver;

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
}
