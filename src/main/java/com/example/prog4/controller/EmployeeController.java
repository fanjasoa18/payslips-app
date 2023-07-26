package com.example.prog4.controller;

import com.example.prog4.model.Employee;
import com.example.prog4.model.Entreprise;
import com.example.prog4.model.PhoneNumber;
import com.example.prog4.repository.EmployeeRepository;
import com.example.prog4.service.EmployeeService;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    private final Entreprise entreprise;

    @GetMapping("/employees")
    public String listEmployees(@RequestParam(name="searchFirstName", required = false) String searchFirstName,
                                @RequestParam(name = "searchLastName", required = false) String searchLastName,
                                @RequestParam(name = "searchSex", required = false) String searchSex,
                                @RequestParam(name = "searchFunction", required = false) String searchFunction,
                                @RequestParam(name = "searchHireDate", required = false) String searchHireDate,
                                @RequestParam(name = "searchDepartureDate", required = false) String searchDepartureDate,
                                @RequestParam(name = "sortType", required = false) String sortType,
                                Model model) {
        List<Employee> employees = employeeService.searchEmployees(searchFirstName, searchLastName, searchSex,
                 searchFunction, searchHireDate, searchDepartureDate, sortType);

        model.addAttribute("employees", employees);
        model.addAttribute("entreprise", entreprise);
        return "employees";
    }

    @GetMapping("/exportToCSV")
    public void exportToCSV(@RequestParam(name = "sort", required = false) String sortType,
                            @RequestParam(name = "search", required = false) String searchQuery,
                            HttpServletResponse response) throws IOException {
        List<Employee> employees = employeeService.searchEmployees(searchQuery,null, null,
                null, null, null, sortType);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");

        CSVWriter csvWriter = new CSVWriter(response.getWriter());
        csvWriter.writeNext(new String[]{"ID", "First Name", "Last Name", "Sex", "Function"});

        for (Employee employee : employees) {
            String[] data= {
                   String.valueOf(employee.getId()),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSex().toString(),
                    employee.getFunction(),
                    employee.getHireDate().toString(),
                    employee.getDepartureDate().toString()
            };
            csvWriter.writeNext(data);
        }
        csvWriter.close();

    }

    @GetMapping("/addEmployee")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("entreprise", entreprise);
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("employee") Employee employee,
                              @RequestParam("phoneNumbers") String phoneNumbers,
                              @RequestParam("imageFile")MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();
                String base = Base64.getEncoder().encodeToString(imageBytes);
                employee.setImage(base);
            } catch (IOException e) {
            }
        }

        List<PhoneNumber> phoneNumberList = employeeService.parsePhoneNumbers(phoneNumbers);
        //employee.setPhoneNumbers(phoneNumberList);
        employeeService.saveEmployee(employee);

        return "redirect:/employees";
    }

}
