package org.springboot.blog.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private Long userId;
    private Long categoryId;
}