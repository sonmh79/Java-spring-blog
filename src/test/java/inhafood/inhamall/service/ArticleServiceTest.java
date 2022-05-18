package inhafood.inhamall.service;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.domain.Member;
import inhafood.inhamall.domain.Timestamps;
import inhafood.inhamall.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleRepository articleRepository;

    @Test
    void 글저장() {

        // 게시글 생성
        Article article = new Article();
        article.setTitle("title");
        article.setDescription("des");
        article.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        // 멤버 생성
        Member member = new Member();
        member.setName("son");
        member.setLoginId("sonny");
        member.setPassword("1111");

        // 게시글 멤버 저장
        article.setMember(member);

        Long articleId = articleService.save(article);

        System.out.println(articleId + "번째 글 저장 완료");
    }

    @Test
    void 글방문() {

        // 게시글 생성
        Article article = new Article();
        article.setTitle("title");
        article.setDescription("des");
        article.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));
        article.setVisitCount(0);

        // 멤버 생성
        Member member = new Member();
        member.setName("son");
        member.setLoginId("sonny");
        member.setPassword("1111");

        // 게시글 멤버 저장
        article.setMember(member);

        // 게시글 저장
        Long articleId = articleService.save(article);


        int visitTime = 100;
        for (int i = 0; i < visitTime; i++) {
            articleService.visit(articleId);
        }

        articleRepository.clearEm();
        Article findArticle = articleService.findOne(articleId);

        org.assertj.core.api.Assertions.assertThat(findArticle.getVisitCount()).isEqualTo(visitTime);
    }
}