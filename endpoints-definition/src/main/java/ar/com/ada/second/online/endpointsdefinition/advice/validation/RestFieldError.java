package ar.com.ada.second.online.endpointsdefinition.advice.validation;

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
