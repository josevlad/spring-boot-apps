package ar.com.ada.second.springboottests.service;

import ar.com.ada.second.springboottests.component.BusinessLogicExceptionComponent;
import ar.com.ada.second.springboottests.exception.BusinessLogicException;
import ar.com.ada.second.springboottests.exception.EntityError;
import ar.com.ada.second.springboottests.model.dto.ProductDTO;
import ar.com.ada.second.springboottests.model.entity.Product;
import ar.com.ada.second.springboottests.model.mapper.AvoidingMappingContext;
import ar.com.ada.second.springboottests.model.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServicesTest {

    @Mock
    private AvoidingMappingContext context;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @InjectMocks
    private ProductServices productServices;

    @Test
    public void whenGetAllThenReturnProductList() {
        // GIVEN
        List<Product> productsMock = Arrays.asList(
                new Product()
                        .setId(1L)
                        .setName("CocaCola")
                        .setDescription("Bebida Gaseosa")
                        .setPrice(new BigInteger("80")),
                new Product()
                        .setId(2L)
                        .setName("Pepsi")
                        .setDescription("Otra Bebida Gaseosa")
                        .setPrice(new BigInteger("90"))
        );
        when(productRepository.findAll()).thenReturn(productsMock);

        // WHEN
        List<ProductDTO> productListDTO = productServices.getAll();

        // THEN
        assertThat(productListDTO.size()).isEqualTo(2);
        assertThat(productListDTO.get(0).getName()).isEqualTo("CocaCola");
        assertThat(productListDTO.get(1).getName()).isEqualTo("Pepsi");
    }

    @Test
    public void whenCreateNewReturnProductDtoWhitID() {
        // GIVEN
        ProductDTO productToSave = new ProductDTO();
        when(productRepository.save(any(Product.class)))
                .thenReturn(new Product().setId(5L));

        // WHEN
        ProductDTO newProductSaved = productServices.createNew(productToSave);

        // THEN
        assertThat(newProductSaved.getId()).isEqualTo(5);
    }

    @Test
    public void whenGetByIdReturnProductDtoWhitID() {
        // GIVEN
        Long id = 8L;
        when(productRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new Product().setId(8L)));

        // WHEN
        ProductDTO byId = productServices.getById(id);

        // THEN
        assertThat(byId.getId()).isEqualTo(8L);
    }

    @Test
    public void whenGetByIdReturnOptionalNull() {
        // GIVEN
        Long id = 8L;
        when(productRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        BusinessLogicException logicException = new BusinessLogicException(
                "Entity does not exist",
                HttpStatus.BAD_REQUEST,
                new EntityError()
        );

        when(logicExceptionComponent.getExceptionEntityNotFound(any(String.class), any(Long.class)))
                .thenReturn(logicException);

        // WHEN
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> productServices.getById(id)
        );

        // THEN
        assertThat(runtimeException.getMessage())
                .isEqualTo("Entity does not exist");
    }

    // update
    // remove
}