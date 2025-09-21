package com.balazsando;

import com.balazsando.enums.Color;
import com.balazsando.enums.DesignType;
import com.balazsando.enums.ProductType;
import com.balazsando.enums.UserId;
import com.balazsando.model.Event;
import com.balazsando.model.Product;
import com.balazsando.model.User;
import com.github.javafaker.Faker;

public class EventGenerator {
    private Faker faker = new Faker();

    public Event generateEvent() {
        return Event.builder()
                .user(generateRandomUser())
                .product(generateRandomObject())
                .build();
    }

    private User generateRandomUser() {
        return User.builder()
                .userId(faker.options().option(UserId.class))
                .userName(faker.name().lastName())
                .dateOfBirth(faker.date().birthday())
                .build();
    }

    private Product generateRandomObject() {
        return Product.builder()
                .color(faker.options().option(Color.class))
                .type(faker.options().option(ProductType.class))
                .designType(faker.options().option(DesignType.class))
                .build();
    }
}
