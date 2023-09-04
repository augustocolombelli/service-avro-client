package com.client.mapper;

import com.client.domain.Product;
import org.apache.avro.AvroMissingFieldException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThat(aProduct.getUuid().toString()).hasToString(actualAvroProduct.getUuid().toString());
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
        assertThat(aAvroProduct.getUuid().toString()).hasToString(actualProduct.getUuid().toString());
        assertThat(aAvroProduct.getName()).isEqualTo(actualProduct.getName());
        assertThat(aAvroProduct.getDescription()).isEqualTo(actualProduct.getDescription());
    }


    @Test
    void aAvroProductWithoutName_throwsAvroMissingFieldException(){
        // given
        com.avro.Product.Builder avroProductBuilder = com.avro.Product.newBuilder()
                .setUuid(UUID.randomUUID())
                .setDescription("a description");

        // when then
        assertThrows(AvroMissingFieldException.class, avroProductBuilder::build);
    }

    @Test
    void aAvroProductWithoutUuid_throwsAvroMissingFieldException(){
        // given
        com.avro.Product.Builder avroProductBuilder = com.avro.Product.newBuilder()
                .setName("a name")
                .setDescription("a description");

        // when then
        assertThrows(AvroMissingFieldException.class, avroProductBuilder::build);
    }

}
