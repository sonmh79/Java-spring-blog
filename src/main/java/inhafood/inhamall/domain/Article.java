package inhafood.inhamall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Article {

    @Id @GeneratedValue
    @Column(name = "article_id")
    private Long id;
    private String title;
    private String description;
    @Embedded
    private Timestamps timestamps;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
