package com.prodReadyFeatures.clients;

import com.prodReadyFeatures.dto.EmployeeDto;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDto> getAllEmployees();
}
