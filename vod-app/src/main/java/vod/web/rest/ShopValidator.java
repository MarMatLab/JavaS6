package vod.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.model.Shop;
import vod.service.ShopService;

@Component
@RequiredArgsConstructor
public class ShopValidator implements Validator {

    private final ShopService shopService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Shop.class);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        Shop validatedShop = (Shop) target;

        boolean duplicated = shopService.getAllShops().stream()
                .anyMatch(shop -> shop.getName().equalsIgnoreCase(validatedShop.getName()));

        if (duplicated)
        {
            errors.rejectValue("name", "shop.name.duplicated");
        }
    }
}
