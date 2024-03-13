package org.adk.sbs.service;

import org.adk.sbs.dto.ArticleDTO;
import org.adk.sbs.dto.ArticleRequest;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Long create(ArticleRequest articleRequest);
    Optional<ArticleDTO> getArticleById(Long id);
    List<ArticleDTO> getAllArticles();
}
