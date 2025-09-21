package com.balazsando;

import com.balazsando.model.PreferredProduct;
import com.balazsando.model.User;
import com.balazsando.service.UserDB;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class SuggestionEngine {

    private final UserDB userDB = new UserDB();

    public void processSuggestions(String userId, String product) {
        String[] valueSplit = product.split(",");
        if (valueSplit.length < 3) {
            System.out.printf("Skipping invalid product: %s%n", product);
            return;
        }

        String productType = valueSplit[0].trim();
        String productColor = valueSplit[1].trim();
        String productDesign = valueSplit[2].trim();

        log.info("User with ID: {}, product type: {}, product color: {}", userId, productType, productColor);

        User user = userDB.findByID(userId);

        user.getPreferences().add(new PreferredProduct(productColor, productType, productDesign));
        user.setSuggestions(generateSuggestions(user.getPreferences()));

        userDB.save(user);
    }

    private List<String> generateSuggestions(List<PreferredProduct> preferences) {
        return Arrays.asList(
                "TSHIRT,BLUE",
                "DESIGN,ORANGE,ROCKET",
                "TSHIRT,PURPLE,CAR"
        );
    }
}
