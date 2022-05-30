package inhafood.inhamall.service;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.domain.Comment;
import inhafood.inhamall.domain.Member;
import inhafood.inhamall.domain.Timestamps;
import inhafood.inhamall.repository.ArticleRepository;
import inhafood.inhamall.repository.CommentRepository;
import inhafood.inhamall.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ArticleService articleService;

    Member member1;
    Member member2;
    Article article1;

    @BeforeEach
    void 멤버_글_생성() {

        this.member1 = new Member();
        this.member1.setName("son");
        this.member1.setLoginId("sonny");
        this.member1.setPassword("1111");

        this.member2 = new Member();
        this.member2.setName("James");
        this.member2.setLoginId("james123");
        this.member2.setPassword("123");

        this.article1 = new Article();
        this.article1.setTitle("Title1");
        this.article1.setDescription("Des1");
        this.article1.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        memberService.join(this.member1);
        memberService.join(this.member2);

        this.article1.setMember(this.member1);
        Long articleId = articleService.save(this.article1);
    }

    @Test
    void 댓글_저장() {
        Comment comment = new Comment();
        comment.setDescription("댓글 내용입니다.");
        comment.setTimestamps(new Timestamps(LocalDateTime.now(),LocalDateTime.now(),null));
        comment.setMember(member1);
        comment.setArticle(article1);

        Long commentId = commentService.save(comment);
        Assertions.assertThat(commentId).isEqualTo(comment.getId());
    }

    @Test
    void 댓글_수정() {
        String updatedDescription = "수정된 내용입니다.";

        Comment comment = new Comment();
        comment.setDescription("댓글 내용입니다.");
        comment.setTimestamps(new Timestamps(LocalDateTime.now(),LocalDateTime.now(),null));
        comment.setMember(member1);
        comment.setArticle(article1);

        System.out.println("댓글을 저장합니다.");
        Long commentId = commentService.save(comment);
        System.out.println("댓글을 수정합니다.");
        commentService.update(commentId,updatedDescription);

        System.out.println("댓글을 비교합니다.");
        List<Comment> findComments = commentRepository.findByMember(member1.getId());
        for (Comment c : findComments) {
            Assertions.assertThat(c.getDescription()).isEqualTo(updatedDescription);
        }

    }

    @Test
    void 댓글_삭제() {
        Comment comment = new Comment();
        comment.setDescription("댓글 내용입니다.");
        comment.setTimestamps(new Timestamps(LocalDateTime.now(),LocalDateTime.now(),null));
        comment.setMember(member1);
        comment.setArticle(article1);

        System.out.println("댓글을 저장합니다.");
        Long commentId = commentService.save(comment);

        System.out.println("댓글을 삭제합니다.");
        commentService.delete(commentId);
        Assertions.assertThat(commentRepository.findById(commentId)).isEqualTo(null);
    }
}