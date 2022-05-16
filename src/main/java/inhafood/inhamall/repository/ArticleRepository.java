package inhafood.inhamall.repository;

import inhafood.inhamall.domain.Article;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ArticleRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Article article) {
        em.persist(article);
        return article.getId();
    }
}
