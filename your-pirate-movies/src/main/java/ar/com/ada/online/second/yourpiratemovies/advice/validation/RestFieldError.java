package ar.com.ada.online.second.yourpiratemovies.advice.validation;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestFieldError {

    private String field;
    private String message;

}
