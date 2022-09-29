package com.acme.hotel_world_api.system.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.hotel_world_api.system.domain.model.Employee;
import com.acme.hotel_world_api.system.domain.service.EmployeeService;
import com.acme.hotel_world_api.system.resource.EmployeeResource;
import com.acme.hotel_world_api.system.resource.SaveEmployeeResource;


@RestController
@RequestMapping("/api")
public class EmployeesController {
    
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/hotels/{hotelId}/employees")
    public Page<EmployeeResource> getAlLEmployeeByHotelId(@PathVariable Long hotelId, Pageable pageable){
        Page<Employee> employeePage = employeeService.getAllEmployeeByHotelId(hotelId, pageable);
        List<EmployeeResource> resources = employeePage.getContent().stream().map(
            this::convertToResource
        ).collect(Collectors.toList());
        
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/hotels/{hotelId}/employees/{employeeId}")
    public EmployeeResource getEmployeeByIdAndHotelId(@PathVariable Long hotelId, @PathVariable Long employeeId){
        return convertToResource(employeeService.getEmployeeByIdAndHotelId(hotelId, employeeId));
    }

    //
    @PostMapping("/hotels/{hotelId}/employees")
    public EmployeeResource createEmployee(@PathVariable Long hotelId, @Valid @RequestBody SaveEmployeeResource resource){
        return convertToResource(employeeService.createEmployee(hotelId, convertToEntity(resource)));
    }

    @PutMapping("/hotels/{hotelId}/employees/{employeeId}")
    public EmployeeResource updateEmployee(@PathVariable Long hotelId, @PathVariable Long employeeId, @Valid @RequestBody SaveEmployeeResource resource){
        return convertToResource(employeeService.updateEmployee(hotelId, employeeId, convertToEntity(resource)));
    }

    @DeleteMapping("/hotels/{hotelId}/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long hotelId, @PathVariable Long employeeId){
        return employeeService.deleteEmployee(hotelId, employeeId);
    }

    //

    private Employee convertToEntity(SaveEmployeeResource resource){
        return mapper.map(resource, Employee.class);
    }

    private EmployeeResource convertToResource(Employee entity){
        return mapper.map(entity, EmployeeResource.class);
    }
}
