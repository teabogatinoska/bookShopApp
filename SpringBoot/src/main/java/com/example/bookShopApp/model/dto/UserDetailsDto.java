package com.example.bookShopApp.model.dto;

import com.example.bookShopApp.enumerations.Role;
import com.example.bookShopApp.model.User;
import lombok.Data;

@Data
public class UserDetailsDto {
    private String username;
    private Role role;

    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getRole();
        return details;
    }
}

