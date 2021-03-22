package ar.com.ada.second.springboottests.controller;

import ar.com.ada.second.springboottests.model.dto.ProductDTO;
import ar.com.ada.second.springboottests.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping({ "", "/" })
    public ResponseEntity createNewProductMethod(@Valid @RequestBody ProductDTO dto) throws URISyntaxException {
        ProductDTO productCreated = productServices.createNew(dto);
        return ResponseEntity
                .created(new URI("/products/" + productCreated.getId()))
                .body(productCreated);
    }
}
