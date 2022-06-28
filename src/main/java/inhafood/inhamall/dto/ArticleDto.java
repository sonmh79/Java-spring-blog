package inhafood.inhamall.dto;

import inhafood.inhamall.domain.Article;
import inhafood.inhamall.domain.Member;
import inhafood.inhamall.domain.Timestamps;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private String author;
    private Integer visitCount;
    private Long memberId;

    public ArticleDto(Long id, String title, String description, LocalDateTime createdDate, String author,Integer visitCount, Long memberId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.author = author;
        this.visitCount = visitCount;
        this.memberId = memberId;
    }

    public static ArticleDto from(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getDescription(), article.getTimestamps().getCreatedDate(), article.getMember().getName(),article.getVisitCount(),article.getMember().getId());
    }

}
