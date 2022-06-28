package inhafood.inhamall.service;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.domain.Comment;
import inhafood.inhamall.dto.ArticleDto;
import inhafood.inhamall.repository.ArticleRepository;
import inhafood.inhamall.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CommentRepository commentRepository;

    public Long save(Article article) {
        articleRepository.save(article);
        return article.getId();
    }

    public List<ArticleDto> findAllArticleDtos() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(a -> ArticleDto.from(a)).collect(Collectors.toList());

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

    public void deleteForever(Long articleId) {
        Article findArticle = articleRepository.findById(articleId);
        //findArticle.setMember(null);

//        // 댓글 남기기
//        List<Comment> comments = commentRepository.findByArticle(findArticle.getId());
//        for (Comment c : comments) {
//            c.setArticle(null);
//        }
        // 댓글 지우기
        List<Comment> comments = commentRepository.findByArticle(findArticle.getId());
        for (Comment c : comments) {
            //c.destroy();
            commentRepository.delete(c);
        }
        articleRepository.deleteForever(findArticle);
    }

    public void visit(Long id) {
        articleRepository.visitArticle(id);
    }
}
