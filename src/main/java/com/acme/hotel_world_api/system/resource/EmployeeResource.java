package com.acme.hotel_world_api.system.resource;

public class EmployeeResource {
    
    private Long id;

    private String name;

    private Long dni;

    private Long phoneNumber;

    private String email;

    public Long getId(){
        return id;
    }

    public EmployeeResource setId(Long id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public EmployeeResource setName(String name){
        this.name = name;
        return this;
    }
    
    public Long getDni(){
        return dni;
    }

    public EmployeeResource setDni(Long dni){
        this.dni=dni;
        return this;
    }

    public Long getPhoneNumber(){
        return phoneNumber;
    }

    public EmployeeResource setPhoneNumber(Long phoneNumber){
        this.phoneNumber=phoneNumber;
        return this;
    }

    public String getEmail(){
        return email;
    }

    public EmployeeResource setEmail(String email){
        this.email = email;
        return this;
    }
}
