package com.balazsando.model;

import com.balazsando.enums.Color;
import com.balazsando.enums.DesignType;
import com.balazsando.enums.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Color color;
    private ProductType type;
    private DesignType designType;
}
