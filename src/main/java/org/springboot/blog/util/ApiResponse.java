package org.springboot.blog.util;

import lombok.*;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
}
