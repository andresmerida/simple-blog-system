package org.adk.sbs.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.adk.sbs.dto.ArticleDTO;
import org.adk.sbs.dto.ArticleRequest;
import org.adk.sbs.service.ArticleService;
import org.adk.sbs.web.rest.exceptions.ArticleNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> listAllArticles() {
        return ResponseEntity
                .ok()
                .body(articleService.getAllArticles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable final Long id) {
        return ResponseEntity
                .ok()
                .body(articleService.getArticleById(id).orElseThrow(() -> new ArticleNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid final ArticleRequest articleRequest) throws URISyntaxException {
        Long newId = articleService.create(articleRequest);
        return ResponseEntity
                .created(new URI("/articles/" + newId))
                .body(newId);
    }
}
