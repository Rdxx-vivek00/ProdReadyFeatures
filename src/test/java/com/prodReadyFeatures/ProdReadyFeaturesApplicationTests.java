package com.prodReadyFeatures;

import com.prodReadyFeatures.clients.EmployeeClient;
import com.prodReadyFeatures.dto.EmployeeDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ProdReadyFeaturesApplicationTests {


    @Autowired
    private EmployeeClient employeeClient;

    @Order(3)
    @Test
    void getAllEmployees()
    {
        List<EmployeeDto> employeeDtoList=employeeClient.getAllEmployees();
        System.out.println(employeeDtoList);
    }

    @Order(2)
    @Test
    void getEmployeeById()
    {
      EmployeeDto employeeDto=employeeClient.getEmployeeById(1L);
        System.out.println(employeeDto);
    }

    @Order(1)
    @Test
    void createNewEmployee()
    {
      EmployeeDto employeeDto=  new EmployeeDto(null, "Anuj", "anuj@gmail.com", 2,LocalDate.of(2020, 12, 1), true);
        EmployeeDto savedEmployeeDTO = employeeClient.createNewEmployee(employeeDto);
    }
}
