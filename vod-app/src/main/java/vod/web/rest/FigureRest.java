package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vod.model.Figure;
import vod.model.Shop;
import vod.service.FigureService;
import vod.service.ShopService;
import vod.web.rest.dto.FigureDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class FigureRest {

    private final FigureService figureService;
    private final ShopService shopService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final FigureValidator figureValidator;

    @GetMapping("/figures")
    List<Figure> getFigures()
    {
        log.info("'bout to retrieve ze figure limst");
        List<Figure> figures = figureService.getAllFigures();
        log.info("{} figures foumd", figures.size());

        return figures;
    }

    @GetMapping("/movies/{id}")
    ResponseEntity<Figure> getFigure(@PathVariable("id") int id)
    {
        log.info("'bout to retrieve ze movie {}", id);
        Figure figure = figureService.getFigureById(id);
        log.info("figure foumd: {}", figure);

        if (figure != null)
        {
            return ResponseEntity.ok(figure);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/shops/{shopId}/figures")
    ResponseEntity<List<Figure>> getFiguresAvailableAtShop(@PathVariable("shopId") int shopId)
    {
        log.info("'bout to retrieve ze figures at stock in shop {}", shopId);
        Shop shop = shopService.getShopById(shopId);

        if (shop==null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            List<Figure> figures = shopService.getFiguresInShop(shop);
            log.info("There's {} figures at stock in shop {}", figures.size(), shop.getName());

            return ResponseEntity.ok(figures);
        }
    }

    @PostMapping("/figures")
    ResponseEntity<?> addFigure(@Validated @RequestBody FigureDTO figureDTO, Errors errors)
    {
        log.info("'bout to add ze figure {}", figureDTO);

        if (errors.hasErrors())
        {
            return ResponseEntity.badRequest().build();
        }

        Figure figure = new Figure();
        figure.setName(figureDTO.getName());
        figure.setMaterial(figureDTO.getMaterial());
        figure.setPrice(figureDTO.getPrice());
        figure.setDesigner(figureService.getDesignerById(figureDTO.getDesignerId()));

        figure = figureService.addFigure(figure);
        log.info("new figure added: {}", figure);
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequestUri()
                                .path("/" + figure.getId())
                                .build()
                                .toUri())
                .body(figure);
    }
}
