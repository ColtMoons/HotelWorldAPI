package com.acme.hotel_world_api.system.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveEmployeeResource {

    @NotNull
    @NotBlank
    @Lob
    private String name;

    @NotNull
    private Long dni;

    @NotNull
    private Long phoneNumber;

    @NotNull
    @NotBlank
    private String email;
    

    public String getName(){
        return name;
    }

    public SaveEmployeeResource setName(String name){
        this.name = name;
        return this;
    }
    
    public Long getDni(){
        return dni;
    }

    public SaveEmployeeResource setDni(Long dni){
        this.dni=dni;
        return this;
    }

    public Long getPhoneNumber(){
        return phoneNumber;
    }

    public SaveEmployeeResource setPhoneNumber(Long phoneNumber){
        this.phoneNumber=phoneNumber;
        return this;
    }

    public String getEmail(){
        return email;
    }

    public SaveEmployeeResource setEmail(String email){
        this.email = email;
        return this;
    }
}
