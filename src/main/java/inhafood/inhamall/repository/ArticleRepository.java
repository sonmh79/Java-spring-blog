package inhafood.inhamall.repository;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ArticleRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Article article) {
        em.persist(article);
        return article.getId();
    }

    public Article findById(Long id) {
        return em.find(Article.class, id);
    }

    public List<Article> findByTitle(String title) {
        return em.createQuery("select a from Article a where a.title = :title", Article.class)
                .setParameter("title", title)
                .getResultList();
    }

    public List<Article> findByMemberId(Long memberId) {
        return em.createQuery("select a from Article a where a.member.id = :memberId", Article.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
