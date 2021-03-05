package ar.com.ada.second.tdvr.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TrackDTO implements Serializable {

    private Long id;

    @NotBlank(message = "is required")
    private String title;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)$", message = "wrong format, should be HH:MM:SS")
    private String trackDuration;

    @JsonIgnoreProperties({ "tracks" })
    private AlbumDTO album;

    public Boolean hasNullOrEmptyAttributes() {
        return title == null || title.trim().isEmpty()
                || trackDuration == null || trackDuration.trim().isEmpty();
    }
}
