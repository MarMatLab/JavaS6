package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vod.model.Shop;
import vod.service.ShopService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ShopRest {

    private final ShopService shopService;

    @GetMapping("/shops")
    List<Shop> getShops() {
        log.info("we getting shopzzz now");
        List<Shop> shops = shopService.getAllShops();
        log.info("{} shopz found", shops.size());

        return shops;
    }
}
