package com.ecommerce.backend.dto;

import com.ecommerce.backend.enums.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
