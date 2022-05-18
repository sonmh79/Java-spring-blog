package inhafood.inhamall.service;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.List;

@Service
@Transactional
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public Long save(Article article) {
        articleRepository.save(article);
        return article.getId();
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findAllDeleted() {
        return articleRepository.findAllDeleted();
    }

    public Article findOne(Long id) {
        return articleRepository.findById(id);
    }

    public Long updateArticle(Long articleId, String title, String description) {
        return articleRepository.updateArticle(articleId, title, description);
    }

    public void delete(Long id) {
        articleRepository.delete(id);
    }

    public void deleteForever(Long id) {
        articleRepository.deleteForever(id);
    }

    public void visit(Long id) {
        articleRepository.visitArticle(id);
    }
}
