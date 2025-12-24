package com.prodReadyFeatures.clients.impl;

import com.prodReadyFeatures.advice.ApiResponse;
import com.prodReadyFeatures.clients.EmployeeClient;
import com.prodReadyFeatures.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

     Logger log=LoggerFactory.getLogger(EmployeeClientImpl.class);
    @Override
    public List<EmployeeDto> getAllEmployees() {

        log.trace("trying to fetch all the employees");

        try {
            ApiResponse<List<EmployeeDto>> employeeDtoList = restClient.get()
                    .uri("api/getAll")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

                log.info("successfully retrieved the employees in getAllEmployees");
                log.trace("retrievd all employeess list: {}",employeeDtoList.getData());
            return employeeDtoList.getData();
        }catch (Exception e)
        {
            log.error("Exception occurred in getAllEmployees",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        log.trace("trying to fetch the employees with employee ID");
        try{
        ApiResponse<EmployeeDto> employeeResponse=restClient.get()
                .uri("api/{employeeId}",employeeId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
            log.info("successfully retrieved the employee in getEmployeeById");
            log.trace("retrievd the  employee with employeeId : {}",employeeId);
        return employeeResponse.getData();}
        catch (Exception e)
        {
            log.error("Exception occurred in getEmployeeById",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        log.trace("trying to post the employee");
        try {
            ResponseEntity<ApiResponse<EmployeeDto>> employeeDTOApiResponse = restClient.post()
                    .uri("api/addEmployee")
                    .body(employeeDto)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.info("successfully posted the employee");

            return employeeDTOApiResponse.getBody().getData();
        }catch (Exception e)
        {
            log.error("exception occurred in createNewEmployee ",e);
            throw new RuntimeException(e);
        }
    }
}
