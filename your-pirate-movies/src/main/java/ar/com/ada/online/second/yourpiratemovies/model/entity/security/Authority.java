package ar.com.ada.online.second.yourpiratemovies.model.entity.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "Authority")
public class Authority implements Serializable {

    private static final String STRING = "STRING";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AuthorityName name;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users;

    public Authority setId(Long id) {
        this.id = id;
        return this;
    }

    public Authority setName(AuthorityName name) {
        this.name = name;
        return this;
    }
}
