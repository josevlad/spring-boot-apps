package ar.com.ada.online.second.yourpiratemovies.model.dto.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Getter @JsonPropertyOrder({ "type", "toke", "createAt" })
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponseBody implements Serializable {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSSXXX")
    private final Date createAt = new Date();

    private String type;

    private String token;

    public JwtAuthResponseBody setToken(String token) {
        this.token = token;
        return this;
    }

    public JwtAuthResponseBody setType(String type) {
        this.type = type;
        return this;
    }

}
