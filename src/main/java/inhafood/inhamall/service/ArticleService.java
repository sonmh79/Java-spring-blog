package inhafood.inhamall.service;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public Long save(Article article) {
        articleRepository.save(article);
        return article.getId();
    }
}
