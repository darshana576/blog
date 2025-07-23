package org.springboot.blog.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;           // ✅ Add this field
    private String username;
    private String email;
    private String password;   // Only for registration (not shown in response)
}