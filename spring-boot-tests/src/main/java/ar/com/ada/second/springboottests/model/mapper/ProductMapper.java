package ar.com.ada.second.springboottests.model.mapper;

import ar.com.ada.second.springboottests.model.dto.ProductDTO;
import ar.com.ada.second.springboottests.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper extends DataMapper<ProductDTO, Product> {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
}
