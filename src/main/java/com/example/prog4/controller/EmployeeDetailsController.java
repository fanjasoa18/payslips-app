package com.example.prog4.controller;

import com.example.prog4.model.Employee;
import com.example.prog4.model.Entreprise;
import com.example.prog4.repository.EmployeeRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class EmployeeDetailsController {
    private Entreprise entreprise;
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees/{id}/details")
    public String getEmployeeDetails(@PathVariable("id") int id, Model model) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            Employee employee = optional.get();
            model.addAttribute("employee", employee);
            model.addAttribute("entreprise", entreprise);
            return "employeeDetails";
        }
        return "errorPage";
    }
}
