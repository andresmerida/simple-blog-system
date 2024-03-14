package org.adk.sbs.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArticleRequest(
        @NotNull
        @NotBlank(message = "Please provide a title")
        @Max(value = 256, message = "Title must be less than or equal to 256")
        String title,
        @NotNull
        @NotBlank(message = "Please provide a content post")
        String content,
        @NotNull
        @NotBlank(message = "Please provide an author")
        String author) {
}
