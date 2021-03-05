package ar.com.ada.second.tdvr.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Track")
public class Track implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "track_duration", nullable = false)
    private String trackDuration;

    @ManyToOne
    @JoinColumn(name = "Album_id", nullable = false, foreignKey = @ForeignKey(name = "fk_Track_Album"))
    private Album album;
}
