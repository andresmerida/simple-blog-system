package org.adk.sbs.service.impl;

import lombok.RequiredArgsConstructor;
import org.adk.sbs.domain.entities.Article;
import org.adk.sbs.dto.ArticleDTO;
import org.adk.sbs.dto.ArticleRequest;
import org.adk.sbs.repository.ArticleRepository;
import org.adk.sbs.service.ArticleService;
import org.adk.sbs.service.mapper.ArticleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    @Override
    public Long create(ArticleRequest articleRequest) {
        Article article = articleRepository.save(ArticleMapper.toEntity(articleRequest));
        return article.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ArticleDTO> getArticleById(Long id) {
        return articleRepository.findById(id).map(ArticleMapper::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(ArticleMapper::toDto)
                .toList();
    }
}
