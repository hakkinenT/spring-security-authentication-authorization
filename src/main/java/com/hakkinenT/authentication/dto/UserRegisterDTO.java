package com.hakkinenT.authentication.dto;

import com.hakkinenT.authentication.domain.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;

    public Role getRole(){
        if(role.equals("ADMIN")){
            return Role.ADMIN;
        }else{
            return Role.USER;
        }
    }
    
}
