package ar.com.ada.second.tdvr.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ArtistDTO implements Serializable {

    private Long id;

    @NotBlank(message = "is required")
    private String name;

    public Boolean hasNullOrEmptyAttributes() {
        return name == null || name.trim().isEmpty();
    }
}
