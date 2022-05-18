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

import java.time.LocalDateTime;
import java.util.List;


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

    @Test
    void 전체글_조회() {

        Article article1 = new Article();
        article1.setTitle("1");
        article1.setDescription("ㄹㄴㅇㄹ");
        article1.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        Article article2 = new Article();
        article2.setTitle("2");
        article2.setDescription("des");
        article2.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        Article article3 = new Article();
        article3.setTitle("3");
        article3.setDescription("des");
        article3.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        // 멤버 생성
        Member member = new Member();
        member.setName("son");
        member.setLoginId("sonny");
        member.setPassword("1111");

        // 게시글 멤버 설정
        article1.setMember(member);
        article2.setMember(member);
        article3.setMember(member);

        // 게시글 저장
        Long article1Id = articleRepository.save(article1);
        Long article2Id = articleRepository.save(article2);
        Long article3Id = articleRepository.save(article3);

        //전체 게시글 조회
        List<Article> articleList = articleRepository.findAll();
        System.out.println("모든 게시글은 총" + articleList.size() + "개 입니다.");
        for (Article a : articleList) {
            System.out.println(a.getId());
        }
    }

    @Test
    void 게시글_수정() {
        Article article = new Article();
        article.setTitle("Tt");
        article.setDescription("des");
        article.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        Member member = new Member();
        member.setName("son");
        member.setLoginId("sonny");
        member.setPassword("1111");

        article.setMember(member);

        Long savedId = articleRepository.save(article);

        String newTitle = "새로운 제목입니다.";
        String newDescription = "새로운 내용입니다.";

        Long cnt = articleRepository.updateArticle(savedId, newTitle, newDescription);
        System.out.println(cnt + "개의 글이 수정되었습니다.");

        articleRepository.clearEm(); // 영속성 컨텍스트 초기화

        Article updatedArticle = articleRepository.findById(savedId);
        Assertions.assertThat(updatedArticle.getTitle()).isEqualTo(newTitle);
        Assertions.assertThat(updatedArticle.getDescription()).isEqualTo(newDescription);

    }

    @Test
    void 글_삭제() {

        Article article = new Article();
        article.setTitle("Tt");
        article.setDescription("des");
        article.setTimestamps(new Timestamps(LocalDateTime.now(), LocalDateTime.now(), null));

        Member member = new Member();
        member.setName("son");
        member.setLoginId("sonny");
        member.setPassword("1111");

        article.setMember(member);

        Long savedId = articleRepository.save(article);
        articleRepository.delete(savedId);

        articleRepository.clearEm();

        //전체 게시글 조회
        List<Article> articleList = articleRepository.findAll();
        List<Article> articleDeletedList = articleRepository.findAllDeleted();
        System.out.println("모든 게시글은 총"+ articleList.size() + "개 입니다.");
        System.out.println("삭제된 게시글은 총" + articleDeletedList.size() + "개 입니다.");
        for (Article a : articleList) {
            System.out.println(a.getId());
            System.out.println(a.getTimestamps().getDeletedDate());
        }
    }
}