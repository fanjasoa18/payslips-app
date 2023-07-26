package com.example.prog4.repository;

import com.example.prog4.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT * FROM employees e WHERE LOWER(e.first_name) LIKE %:searchFirstName%"
            + " OR LOWER(e.last_name) LIKE %:searchLastName%"
            + " OR e.sex = :searchSex"
            + " OR LOWER(e.function) LIKE %:searchFunction%"
            + " OR LOWER(e.hire_date) LIKE %:searchHireDate%"
            + " OR LOWER(e.departure_date) LIKE %:searchDepartureDate%",
            nativeQuery = true)
    List<Employee> searchEmployees(@Param("searchFirstName") String searchFirstName,
                                   @Param("searchLastName") String searchLastName,
                                   @Param("searchSex") String searchSex,
                                   @Param("searchFunction") String searchFunction,
                                   @Param("searchHireDate") String searchHireDate,
                                   @Param("searchDepartureDate") String searchDepartureDate);
}
