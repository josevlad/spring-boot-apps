package ar.com.ada.second.tdvr.model.entity;

import ar.com.ada.second.tdvr.model.mapper.converter.YearAttributeConverter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "Album")
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "smallint")
    @Convert(converter = YearAttributeConverter.class)
    private Year released;

    @ManyToOne
    @JoinColumn(name = "Artist_id", nullable = false, foreignKey = @ForeignKey(name = "fk_Album_Artist"))
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<Track> tracks;
}
