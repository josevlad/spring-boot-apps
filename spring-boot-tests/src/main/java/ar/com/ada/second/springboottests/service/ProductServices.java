package ar.com.ada.second.springboottests.service;

import ar.com.ada.second.springboottests.component.BusinessLogicExceptionComponent;
import ar.com.ada.second.springboottests.model.dto.ProductDTO;
import ar.com.ada.second.springboottests.model.entity.Product;
import ar.com.ada.second.springboottests.model.mapper.AvoidingMappingContext;
import ar.com.ada.second.springboottests.model.mapper.ProductMapper;
import ar.com.ada.second.springboottests.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices implements Services<ProductDTO, Product> {

    private ProductMapper productMapper = ProductMapper.MAPPER;

    @Autowired
    private AvoidingMappingContext context;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Override
    public ProductDTO createNew(ProductDTO dto) {
        // 1) se recibe en el metodo los datos a guardar (dto) y esos datos
        // se convierten en un objeto de tipo entity
        Product productToSave = productMapper.toEntity(dto, context);

        // 2) invocar el metodo save de la instancia productRepository
        Product productSaved = productRepository.save(productToSave);

        // 3) convertir el entity en dto (con un id de la base de datos)
        ProductDTO productSavedDTO = productMapper.toDTO(productSaved, context);

        // 4) retorno el dto con id
        return productSavedDTO;
    }

    @Override
    public List<ProductDTO> getAll() {
        // 1) desde el productRepository invoco al metodo findAll para obtener
        // la lits de todos los productos
        List<Product> products = productRepository.findAll();

        // 2) convertir esa lista (entity) a una lista de tipo DTO
        List<ProductDTO> productListDTO = productMapper.toDTO(products, context);

        // 3) retornar esa lista de DTO
        return productListDTO;
    }

    @Override
    public ProductDTO getById(Long id) {
        // 1) se invoca el metodo findById de la instancia productRepository
        // pasandole por parametro el id, en caso que no exista, lanza una exception
        Product productById = productRepository
                .findById(id)
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Produc", id));

        // 2) se convierte esa entity en dto
        ProductDTO productByIdDTO = productMapper.toDTO(productById, context);

        // 3) se retorna el dto
        return productByIdDTO;
    }

    @Override
    public ProductDTO update(ProductDTO dto, Long id) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void mergeData(Product entity, ProductDTO dto) {

    }
}
