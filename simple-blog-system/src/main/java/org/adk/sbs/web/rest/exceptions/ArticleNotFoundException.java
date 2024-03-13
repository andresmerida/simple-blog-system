package org.adk.sbs.web.rest.exceptions;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException(Long id) {
        super("Article with id: " + id + ", NOT FOUND.");
    }
}
