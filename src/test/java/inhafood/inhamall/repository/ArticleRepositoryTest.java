package inhafood.inhamall.repository;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.domain.Member;
import inhafood.inhamall.domain.Timestamps;
import org.assertj.core.api.Assertions;
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
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    void 글작성() {
        Article article = new Article();
        article.setTitle("Tt");
        article.setDescription("des");
        article.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        Member member = new Member();
        member.setName("son");
        member.setLoginId("sonny");
        member.setPassword("1111");

        article.setMember(member);

        Long articleId = articleRepository.save(article);

        Article findArticle = articleRepository.findById(articleId);

        // findById
        Assertions.assertThat(article.getId()).isEqualTo(findArticle.getId());
    }

    @Test
    void 제목으로_조회() {

        String sameTitle = "Tottenham";

        // 게시글 생성
        Article article1 = new Article();
        article1.setTitle(sameTitle);
        article1.setDescription("des");
        article1.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        Article article2 = new Article();
        article2.setTitle(sameTitle);
        article2.setDescription("champs");
        article2.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        // 멤버 생성
        Member member = new Member();
        member.setName("son");
        member.setLoginId("sonny");
        member.setPassword("1111");

        Member member2 = new Member();
        member2.setName("son");
        member2.setLoginId("sonny");
        member2.setPassword("1111");

        // 게시글 멤버 설정
        article1.setMember(member);
        article2.setMember(member2);

        // 게시글 저장
        Long articleId = articleRepository.save(article1);
        Long article2Id = articleRepository.save(article2);

        // 게시글 제목으로 검색
        List<Article> articleList = articleRepository.findByTitle(sameTitle);

        System.out.println("제목" + sameTitle + "을 가진 게시글은 총 " + articleList.size() + "개 입니다.");
        for (Article a : articleList) {
            Assertions.assertThat(a.getTitle()).isEqualTo(sameTitle);
        }
    }

    @Test
    void 사용자로_조회() {

        // 게시글 생성
        Article article1 = new Article();
        article1.setTitle("Tt");
        article1.setDescription("des");
        article1.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        Article article2 = new Article();
        article2.setTitle("Tt");
        article2.setDescription("des");
        article2.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        // 멤버 생성
        Member member = new Member();
        member.setName("son");
        member.setLoginId("sonny");
        member.setPassword("1111");

        // 게시글 멤버 설정
        article1.setMember(member);
        article2.setMember(member);

        // 게시글 저장
        Long article1Id = articleRepository.save(article1);
        Long article2Id = articleRepository.save(article2);

        //멤버 Id로 조회
        List<Article> articleListFindByMemberId = articleRepository.findByMemberId(member.getId());
        System.out.println(member.getName() + "가 작성한 게시글은 총 " + articleListFindByMemberId.size() + "개 입니다.");
        for (Article a : articleListFindByMemberId) {
            Assertions.assertThat(a.getMember()).isEqualTo(member);
        }
    }
}