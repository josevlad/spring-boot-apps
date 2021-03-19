package ar.com.ada.second.springboottests.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private BigInteger price;

    private Date createAt;

    private Date updateAt;

    public ProductDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductDTO setPrice(BigInteger price) {
        this.price = price;
        return this;
    }
}
