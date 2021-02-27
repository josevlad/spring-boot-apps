package ar.com.ada.second.tdvr.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TrackDTO {
    private Long id;
    private String title;
    private String trackDuration;
    private AlbumDTO album;
}
