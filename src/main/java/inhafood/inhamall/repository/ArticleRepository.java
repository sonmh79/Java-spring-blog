package inhafood.inhamall.repository;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ArticleRepository {

    @PersistenceContext
    EntityManager em;

    public void clearEm() {
        em.clear();
    }

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

    // 전체 글 조회
    public List<Article> findAll() {
        return em.createQuery("select a from Article a where a.timestamps.deletedDate is null order by a.timestamps.createdDate desc")
                .getResultList();
    }

    // 삭제된 글 조회
    public List<Article> findAllDeleted() {
        return em.createQuery("select a from Article a where a.timestamps.deletedDate is not null order by a.timestamps.createdDate desc")
                .getResultList();
    }

    public Long updateArticle(Long articleId, String title, String description) {
        return Long.valueOf(em.createQuery("update Article a set a.title = :title, a.description = :description, a.timestamps.updatedDate = :updatedDate where a.id = :articleId")
                .setParameter("articleId", articleId)
                .setParameter("title", title)
                .setParameter("description", description)
                .setParameter("updatedDate", LocalDateTime.now())
                .executeUpdate());
    }

    public void delete(Long id) {
        em.createQuery("update Article a set a.timestamps.deletedDate = :deletedDate where a.id = :articleId")
                .setParameter("articleId", id)
                .setParameter("deletedDate", LocalDateTime.now())
                .executeUpdate();
    }

    public void deleteForever(Article article) {
        em.remove(article);
    }

    public void visitArticle(Long id) {
        em.createQuery("update Article a set a.visitCount = a.visitCount + 1 where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
