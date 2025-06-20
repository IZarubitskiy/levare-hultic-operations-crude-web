package com.example.levarehulticops.users.dto;

import com.example.levarehulticops.users.entity.AccessLevel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO for creating a new User.
 * All fields are required.
 */
public record UserCreateRequest(
        /** Full name of the employee */
        @NotBlank(message = "Name must not be blank")
        String name,

        /** Business position or role description */
        @NotBlank(message = "Position must not be blank")
        String position,

        /** Username for authentication (unique) */
        @NotBlank(message = "Username must not be blank")
        String username,

        /** Plain-text password for authentication */
        @NotBlank(message = "Password must not be blank")
        String password,

        /** System access level */
        @NotNull(message = "Role must not be null")
        AccessLevel role
) {
}
