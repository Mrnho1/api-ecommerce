package com.ecommerce.dto;

import com.ecommerce.model.Role;
import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private Role role;
}
