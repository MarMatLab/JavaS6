package vod.web.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vod.model.Designer;
import vod.model.Figure;
import vod.model.Shop;
import vod.service.FigureService;
import vod.service.ShopService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FigureController {

    private final ShopService shopService;
    private final FigureService figureService;

    @GetMapping("/figures") // /figures?shopId=3
    String getFigures(Model model, @RequestParam(value = "shopId", required = false) Integer shopId,
                      @RequestParam(value = "designerId", required = false) Integer designerId)
    {
        log.info("about to display figures in shop {}", shopId);

        if(shopId != null)
        {
            Shop shop = shopService.getShopById(shopId);
            List<Figure> figures = shopService.getFiguresInShop(shop);
            model.addAttribute("figures", figures);
            model.addAttribute("title", "Figures in shop " + shop.getName());
        }
        else if (designerId != null)
        {
            Designer designer = figureService.getDesignerById(designerId);
            List<Figure> figures = figureService.getFiguresByDesigner(designer);
            model.addAttribute("figures", figures);
            model.addAttribute("title", "Figures by " + designer.getLastName());
        }
        else {
            List<Figure> figures = figureService.getAllFigures();
            model.addAttribute("figures", figures);
            model.addAttribute("title", "Figures");
        }

        return "figuresView";
    }
}
