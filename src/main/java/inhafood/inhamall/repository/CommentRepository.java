package inhafood.inhamall.repository;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.domain.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Comment comment) {
        em.persist(comment);
        return comment.getId();
    }

    public List<Comment> findByArticle(Long articleId) {
        return em.createQuery("select c from Comment c where c.article.id = :articleId", Comment.class)
                .setParameter("articleId", articleId)
                .getResultList();
    }

    public List<Comment> findByMember(Long memberId) {
        return em.createQuery("select c from Comment c where c.member.id = :memberId", Comment.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
