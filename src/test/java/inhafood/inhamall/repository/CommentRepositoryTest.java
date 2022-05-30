package inhafood.inhamall.repository;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.domain.Comment;
import inhafood.inhamall.domain.Member;
import inhafood.inhamall.domain.Timestamps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    MemberRepository memberRepository;

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

        memberRepository.save(this.member1);
        memberRepository.save(this.member2);

        this.article1.setMember(this.member1);
        Long articleId = articleRepository.save(this.article1);
    }

    @Test
    void 댓글_저장() {
        Comment comment = new Comment();
        comment.setDescription("댓글 내용입니다.");
        comment.setTimestamps(new Timestamps(LocalDateTime.now(),LocalDateTime.now(),null));
        comment.setMember(member1);
        comment.setArticle(article1);

        Long commentId = commentRepository.save(comment);
        Assertions.assertThat(commentId).isEqualTo(comment.getId());
    }

    @Test
    void 댓글_조회_with_Member() {
        Comment comment1 = new Comment();
        comment1.setDescription("댓글 내용입니다.");
        comment1.setTimestamps(new Timestamps(LocalDateTime.now(),LocalDateTime.now(),null));
        comment1.setMember(member1);
        comment1.setArticle(article1);

        Comment comment2 = new Comment();
        comment2.setDescription("댓글 내용입니다.");
        comment2.setTimestamps(new Timestamps(LocalDateTime.now(),LocalDateTime.now(),null));
        comment2.setMember(member1);
        comment2.setArticle(article1);

        Long comment1Id = commentRepository.save(comment1);
        Long comment2Id = commentRepository.save(comment2);

        List<Comment> findComments = commentRepository.findByMember(member1.getId());

        for (Comment comment :findComments) {
            Assertions.assertThat(comment.getMember().getId()).isEqualTo(member1.getId());
        }
    }

    @Test
    void 댓글_조회_with_Article() {
        Comment comment1 = new Comment();
        comment1.setDescription("member1의 댓글 내용입니다.");
        comment1.setTimestamps(new Timestamps(LocalDateTime.now(),LocalDateTime.now(),null));
        comment1.setMember(member1);
        comment1.setArticle(article1);

        Comment comment2 = new Comment();
        comment2.setDescription("member2의 댓글 내용입니다.");
        comment2.setTimestamps(new Timestamps(LocalDateTime.now(),LocalDateTime.now(),null));
        comment2.setMember(member2);
        comment2.setArticle(article1);

        Long comment1Id = commentRepository.save(comment1);
        Long comment2Id = commentRepository.save(comment2);

        List<Comment> findComments = commentRepository.findByArticle(article1.getId());

        for (Comment comment :findComments) {
            Assertions.assertThat(comment.getArticle().getId()).isEqualTo(article1.getId());
        }
    }

    @Test
    void 댓글_조회_with_id() {
        Comment comment1 = new Comment();
        comment1.setDescription("member1의 댓글 내용입니다.");
        comment1.setTimestamps(new Timestamps(LocalDateTime.now(),LocalDateTime.now(),null));
        comment1.setMember(member1);
        comment1.setArticle(article1);

        Long savedId = commentRepository.save(comment1);

        Comment findComment = commentRepository.findById(savedId);
        Assertions.assertThat(findComment.getId()).isEqualTo(savedId);
    }

    @Test
    void 댓글_삭제() {
        Comment comment1 = new Comment();
        comment1.setDescription("member1의 댓글 내용입니다.");
        comment1.setTimestamps(new Timestamps(LocalDateTime.now(),LocalDateTime.now(),null));
        comment1.setMember(member1);
        comment1.setArticle(article1);

        Long savedId = commentRepository.save(comment1);

        commentRepository.delete(savedId);

        Assertions.assertThat(commentRepository.findById(savedId)).isEqualTo(null);

    }
}