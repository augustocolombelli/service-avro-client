package com.client.domain;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Product {

    UUID uuid;
    String name;
    String description;

}
