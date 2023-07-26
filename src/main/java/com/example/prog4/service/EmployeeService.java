package com.example.prog4.service;

import com.example.prog4.model.Employee;
import com.example.prog4.model.PhoneNumber;
import com.example.prog4.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    public List<Employee> getAllList() {
        return repository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<PhoneNumber> parsePhoneNumbers(String phoneNumbers) {
        List<PhoneNumber> phoneNumbersList = new ArrayList<>();
        String[] phoneNumberArray = phoneNumbers.split(",");

        for (String phoneNumber : phoneNumberArray) {
            PhoneNumber phoneNumberObj = new PhoneNumber();
            phoneNumberObj.setPhoneNumbers(phoneNumber.trim());
            phoneNumbersList.add(phoneNumberObj);
        }

        return phoneNumbersList;
    }

    public List<Employee> searchEmployees(String searchFirstName, String searchLastName, String searchSex,
                                          String searchFunction, String searchHireDate, String searchDepartureDate, String sortType) {
        List<Employee> employees = repository.findAll();

        if (searchFirstName != null && !searchFirstName.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> employee.getFirstName().toLowerCase().contains(searchFirstName.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (searchLastName != null && !searchLastName.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> employee.getLastName().toLowerCase().contains(searchLastName.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (searchSex != null && !searchSex.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> employee.getSex().toString().toLowerCase().contains(searchSex.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (searchFunction != null && !searchFunction.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> employee.getFunction().toLowerCase().contains(searchFunction.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (searchHireDate != null && !searchHireDate.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> employee.getHireDate().toString().toLowerCase().contains(searchHireDate.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (searchDepartureDate != null && !searchDepartureDate.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> employee.getDepartureDate().toString().toLowerCase().contains(searchDepartureDate.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (sortType != null && !sortType.isEmpty()) {
            switch (sortType) {
                case "first_name_asc":
                    Collections.sort(employees, Comparator.comparing(Employee::getFirstName));
                    break;
                case "first_name_desc":
                    Collections.sort(employees, Comparator.comparing(Employee::getFirstName).reversed());
                    break;
                case "last_name_asc":
                    Collections.sort(employees, Comparator.comparing(Employee::getLastName));
                    break;
                case "last_name_desc":
                    Collections.sort(employees, Comparator.comparing(Employee::getLastName).reversed());
                    break;
                case "sex_asc":
                    Collections.sort(employees, Comparator.comparing(Employee::getSex));
                    break;
                case "sex_desc":
                    Collections.sort(employees, Comparator.comparing(Employee::getSex).reversed());
                    break;
                case "function_asc":
                    Collections.sort(employees, Comparator.comparing(Employee::getFunction));
                    break;
                case "function_desc":
                    Collections.sort(employees, Comparator.comparing(Employee::getFunction).reversed());
                    break;
                case "hire_date_asc":
                    Collections.sort(employees, Comparator.comparing(Employee::getHireDate));
                    break;
                case "hire_date_desc":
                    Collections.sort(employees, Comparator.comparing(Employee::getHireDate).reversed());
                    break;
                case "departure_date_asc":
                    Collections.sort(employees, Comparator.comparing(Employee::getDepartureDate));
                    break;
                case "departure_date_desc":
                    Collections.sort(employees, Comparator.comparing(Employee::getDepartureDate).reversed());
                    break;
                default:
                    // Gérer le tri par défaut si le type de tri n'est pas reconnu
                    break;
            }
        }

        return employees;
    }

}
