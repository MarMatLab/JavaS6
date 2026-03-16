package vod.web.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Figure;
import vod.model.Shop;
import vod.service.FigureService;
import vod.service.ShopService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ShopController {

    private final ShopService shopService;
    private final FigureService figureService;

    @GetMapping("/shops") //shops?figureId=3
    String getShops(Model model, @RequestParam(value = "figureId", required = false) Integer figureId) {
        log.info("about to display list having figure {}", figureId);

        if (figureId != null) {
            Figure figure = figureService.getFigureById(figureId);
            List<Shop> shops = shopService.getShopsByFigure(figure);
            model.addAttribute("shops", shops);
            model.addAttribute("title", "Shops having: " + figure.getName());
        }
        else {
            List<Shop> shops = shopService.getAllShops();
            model.addAttribute("shops", shops);
            model.addAttribute("title", "Shops");
        }
        return "shopsView";
    }
}
