package com.client.mapper;

import com.client.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    public void setUp(){
        productMapper = new ProductMapper();
    }

    @Test
    void aProduct_mapToAvroProduct(){
        // given
        Product aProduct = Product.builder()
                .uuid(UUID.randomUUID())
                .name("a name")
                .description("a description")
                .build();

        // when
        com.avro.Product actualAvroProduct = productMapper.toAvro(aProduct);

        // then
        assertThat(aProduct.getUuid().toString()).isEqualTo(actualAvroProduct.getUuid().toString());
        assertThat(aProduct.getName()).isEqualTo(actualAvroProduct.getName().toString());
        assertThat(aProduct.getDescription()).isEqualTo(actualAvroProduct.getDescription());
    }

    @Test
    void aAvroProduct_mapToProduct(){
        // given
        com.avro.Product aAvroProduct = com.avro.Product.newBuilder()
                .setUuid(UUID.randomUUID())
                .setName("a name")
                .setDescription("a description")
                .build();

        // when
        Product actualProduct = productMapper.toDomain(aAvroProduct);

        // then
        assertThat(aAvroProduct.getUuid().toString()).isEqualTo(actualProduct.getUuid().toString());
        assertThat(aAvroProduct.getName()).isEqualTo(actualProduct.getName());
        assertThat(aAvroProduct.getDescription()).isEqualTo(actualProduct.getDescription());
    }

}
