package com.acme.hotel_world_api.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.hotel_world_api.shared.exception.ResourceNotFoundException;
import com.acme.hotel_world_api.system.domain.model.Employee;
import com.acme.hotel_world_api.system.domain.repository.EmployeeRepository;
import com.acme.hotel_world_api.system.domain.repository.HotelRepository;
import com.acme.hotel_world_api.system.domain.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Page<Employee> getAllEmployeeByHotelId(Long hotelId, Pageable pageable) {
        return employeeRepository.findByHotelId(hotelId, pageable);
    }

    @Override
    public Employee getEmployeeByIdAndHotelId(Long hotelId, Long employeeId) {
        return employeeRepository.findByIdAndHotelId(employeeId, hotelId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id" + employeeId + "and HotelId" + hotelId));
    }

    @Override
    public Employee createEmployee(Long hotelId, Employee employee) {
        return hotelRepository.findById(hotelId).map(hotel ->{
            employee.setHotel(hotel);
            return employeeRepository.save(employee);
        }).orElseThrow(()->new ResourceNotFoundException("Hotel", "ID", hotelId));
    }

    @Override
    public Employee updateEmployee(Long hotelId, Long employeeId, Employee employeeDetails) {
        if(!hotelRepository.existsById(hotelId)){
            throw new ResourceNotFoundException("Hotel", "Id", hotelId);
        }
        return employeeRepository.findById(employeeId).map(employee ->{
            employee.setName(employeeDetails.getName())
            .setPhoneNumber(employeeDetails.getPhoneNumber())
            .setDni(employeeDetails.getDni())
            .setEmail(employeeDetails.getEmail());
            return employeeRepository.save(employee);
        }).orElseThrow(()->new ResourceNotFoundException("Employee", "ID", employeeId));
    }

    @Override
    public ResponseEntity<?> deleteEmployee(Long hotelId, Long employeeId) {
        if(!hotelRepository.existsById(hotelId)){
            throw new ResourceNotFoundException("Hotel", "Id", hotelId);
        }
        return employeeRepository.findById(employeeId).map(employee -> {
            employeeRepository.delete(employee);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Employee", "ID", employeeId));
    }
    
}
