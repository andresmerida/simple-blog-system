package org.adk.sbs.web.rest.exceptions;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(Long id) {
        super("Article with id: " + id + ", Not Found.");
    }
}
