package com.prodReadyFeatures;

import com.prodReadyFeatures.clients.EmployeeClient;
import com.prodReadyFeatures.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProdReadyFeaturesApplicationTests {


    @Autowired
    private EmployeeClient employeeClient;

    @Test
    void getAllEmployees()
    {
        List<EmployeeDto> employeeDtoList=employeeClient.getAllEmployees();
        System.out.println(employeeDtoList);
    }
}
