package org.adk.sbs.service.mapper;

import org.adk.sbs.domain.entities.Article;
import org.adk.sbs.dto.ArticleDTO;
import org.adk.sbs.dto.ArticleRequest;

public interface ArticleMapper {
    static Article toEntity(ArticleRequest articleRequest) {
        return new Article(
                articleRequest.title(),
                articleRequest.content(),
                articleRequest.author()
        );
    }

    static ArticleDTO toDto(Article article) {
        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getAuthor()
        );
    }
}
