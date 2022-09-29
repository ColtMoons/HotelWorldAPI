package com.acme.hotel_world_api.system.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.acme.hotel_world_api.system.domain.model.Employee;

public interface EmployeeService {
    Page<Employee> getAllEmployeeByHotelId(Long hotelId, Pageable pageable);
    Employee getEmployeeByIdAndHotelId(Long hotelId, Long employeeId);
    Employee createEmployee(Long hotelId, Employee employee);
    Employee updateEmployee(Long hotelId, Long employeeId, Employee employeeDetails);
    ResponseEntity<?> deleteEmployee(Long hotelId, Long employeeId);
}
