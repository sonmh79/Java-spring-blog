package inhafood.inhamall.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.persister.walking.spi.CollectionDefinition;

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
    private Integer visitCount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;
}
