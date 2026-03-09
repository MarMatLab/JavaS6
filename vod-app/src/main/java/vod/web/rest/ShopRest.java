package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vod.model.Figure;
import vod.model.Shop;
import vod.service.FigureService;
import vod.service.ShopService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class ShopRest {

    private final ShopService shopService;
    private final FigureService figureService;

    @GetMapping("/shops")
    List<Shop> getShops(@RequestParam(value = "phrase", required = false) String phrase,
                        @RequestHeader(value = "custom-header", required = false) String customHeader)
    {
        log.info("we getting shopzzz now");
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
    ResponseEntity<Shop> addShop(@RequestBody Shop shop) {
        log.info("we adding a new shop {}", shop);
        shop = shopService.addShop(shop);
        log.info("shop added: {}", shop);
        return ResponseEntity.status(HttpStatus.CREATED).body(shop);
    }
}
