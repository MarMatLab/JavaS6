package vod.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vod.model.Shop;
import vod.model.Figure;
import vod.repository.ShopDao;
import vod.repository.FigureDao;
import vod.service.ShopService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ShopServiceBean implements ShopService {

    private static final Logger log = Logger.getLogger(ShopService.class.getName());

    private ShopDao shopDao;
    private FigureDao figureDao;

    public ShopServiceBean(ShopDao shopDao, FigureDao figureDao) {
        log.info("creating shop service bean");
        this.shopDao = shopDao;
        this.figureDao = figureDao;
    }

    @Override
    public Shop getShopById(int id) {
        log.info("searching shop by id " + id);
        return shopDao.findById(id);
    }

    @Override
    public List<Figure> getFiguresInShop(Shop c) {
        log.info("searching figures sold in shop " + c.getId());
        return figureDao.findByShop(c);
    }

    @Override
    public List<Shop> getAllShops() {
        log.info("searching all shops");
        return shopDao.findAll();
    }

    @Override
    public List<Shop> getShopsByFigure(Figure m) {
        log.info("searching shops by figure " + m.getId());
        return shopDao.findByFigure(m);
    }

    @Override
    public Shop addShop(Shop shop) {
        log.info("adding shop " + shop);
        return shopDao.save(shop);
    }

}
