package com.client.mapper;


import com.client.domain.Product;

import java.util.UUID;

public class ProductMapper {

    public Product toDomain(com.avro.Product avroProduct) {
        return Product.builder()
                .uuid(UUID.fromString(String.valueOf(avroProduct.getUuid())))
                .description(avroProduct.getDescription())
                .name(String.valueOf(avroProduct.getName()))
                .lastName(String.valueOf(avroProduct.getLastName()))
                .build();
    }

    public com.avro.Product toAvro(Product product) {
        return com.avro.Product.newBuilder()
                .setUuid(product.getUuid().toString())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setLastName(product.getLastName())
                .build();
    }

}
