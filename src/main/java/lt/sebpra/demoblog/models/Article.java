package lt.sebpra.demoblog.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "article")
@Setter
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable=false)
    private User user;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="article_stats_id", referencedColumnName = "id", nullable=false)
    private ArticleStats articleStats;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Comment> comments;

    @Column(name = "image_name")
    private String imageName;

    @CreationTimestamp
    @Column(name = "created")
    private Timestamp created;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "content", length = 5000)
    private String content;

}