package vod.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import vod.model.Figure;
import vod.model.Shop;
import vod.service.FigureService;
import vod.service.ShopService;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class ShopRest {

    private final ShopService shopService;
    private final FigureService figureService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final ShopValidator validator;

/*    @InitBinder
    void initBinder(WebDataBinder binder)
    {
        binder.addValidators(validator);
    }*/

    @GetMapping("/shops")
    List<Shop> getShops(@RequestParam(value = "phrase", required = false) String phrase,
                        @RequestHeader(value = "custom-header", required = false) String customHeader,
                        @CookieValue(value = "some-cookie", required = false) String someCookie)
    {
        log.info("we getting shopzzz now");
        log.info("phrase param: {}", phrase);
        log.info("custom-header param: {}", customHeader);
        log.info("some cookie vaaaaaalue: {}", someCookie);

        if (phrase != null && phrase.equals("foo"))
        {
            throw new IllegalArgumentException("Foo!");
        }
        List<Shop> shops = shopService.getAllShops();
        log.info("{} shopz found", shops.size());

        return shops;
    }

    @GetMapping("/shops/{id}")
    ResponseEntity<Shop> getShop(@PathVariable("int") int id) {
        log.info("we getting a shopppp now {}", id);
        Shop shop = shopService.getShopById(id);
        log.info("shop found: {}", shop);

        if (shop != null)
        {
            return ResponseEntity.status(200).body(shop);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/figures/{figureId}/shops")
    ResponseEntity<List<Shop>> getShopsByFigure(@PathVariable("figureId") int figureId) {
        log.info("we 'bout to get shops with figure in stock {}", figureId);
        Figure figure = figureService.getFigureById(figureId);

        if (figure == null)
        {
            return ResponseEntity.notFound().build();
        }
        else {
            List<Shop> shops = shopService.getShopsByFigure(figure);
            log.info("there's {} shops with figure in stock", shops.size(), figure.getName());
            return ResponseEntity.ok(shops);
        }
    }

    @PostMapping("/shops")
    ResponseEntity<?> addShop(@Validated @RequestBody Shop shop, Errors errors, HttpServletRequest request) {
        log.info("we adding a new shop {}", shop);

        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMessage = errors.getAllErrors().stream()
                    .map(oe->messageSource.getMessage(oe.getCode(), new Object[0], locale))
                    .reduce("errors:\n", (accu, oe)->accu + oe + "\n");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        shop = shopService.addShop(shop);
        log.info("shop added: {}", shop);
        return ResponseEntity.status(HttpStatus.CREATED).body(shop);
    }
}
