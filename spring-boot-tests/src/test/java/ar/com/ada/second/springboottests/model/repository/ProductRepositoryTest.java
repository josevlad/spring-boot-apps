package ar.com.ada.second.springboottests.model.repository;

import ar.com.ada.second.springboottests.model.entity.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    //SAVE
    @Test @Order(0)
    public void whenSaveThenReturnAnProductWithId() {
        // GIVEN
        Product product = new Product()
                .setName("CocaCola")
                .setDescription("Bebida Gaseosa")
                .setPrice(new BigInteger("80"));

        // WHEN
        productRepository.save(product);

        // THEN
        assertNotNull(product.getId());
        assertNotNull(product.getCreateAt());
        assertNotNull(product.getUpdateAt());

    }

    //FIND BY (Select)
    @Test @Order(1)
    public void whenFindByNameThenReturnProduct() {
        //GIVEN
        String nameProduct = "CocaCola";

        //WHEN
        Product productByName = productRepository
                .findByName(nameProduct)
                .orElse(null);

        //THEN
        assertNotNull(productByName);
        assertEquals(nameProduct, productByName.getName());
    }

    //FIND ALL
    @Test @Order(2)
    public void whenFindAllThenReturnProductList() {
        // GIVEN
        //NO HAY NADA EN ESTA SECCION O
        // SE AGREGA UN REGISTRO MAS A LA BASDE DE DATOS EN MEMORIA
        Product product = new Product()
                .setName("Pepsi")
                .setDescription("Otra Bebida Gaseosa")
                .setPrice(new BigInteger("90"));
        productRepository.save(product);

        // WHEN
        List<Product> all = productRepository.findAll();

        // THEN
        assertEquals(all instanceof Collection, true);
        assertThat(all).hasSize(3);
    }

    //UPDATE
    //DELETE

}