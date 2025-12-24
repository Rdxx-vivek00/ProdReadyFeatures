package com.prodReadyFeatures.clients.impl;

import com.prodReadyFeatures.advice.ApiResponse;
import com.prodReadyFeatures.clients.EmployeeClient;
import com.prodReadyFeatures.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;
    @Override
    public List<EmployeeDto> getAllEmployees() {
        try {
            ApiResponse<List<EmployeeDto>> employeeDtoList = restClient.get()
                    .uri("api/getAll")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeDtoList.getData();
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
