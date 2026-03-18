package vod.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vod.repository.ShopDao;
import vod.repository.DesignerDao;
import vod.repository.FigureDao;
import vod.model.Shop;
import vod.model.Designer;
import vod.model.Figure;
import vod.service.FigureService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class FigureServiceBean implements FigureService {

    private static final Logger log = Logger.getLogger(FigureService.class.getName());

    private DesignerDao directorDao;
    private ShopDao shopDao;
    private FigureDao movieDao;

    public FigureServiceBean(DesignerDao directorDao, @Qualifier("jpaShopDao") ShopDao shopDao, FigureDao movieDao) {
        this.directorDao = directorDao;
        this.shopDao = shopDao;
        this.movieDao = movieDao;
    }

    public List<Figure> getAllFigures() {
        log.info("searching all movies...");
        return movieDao.findAll();
    }

    public List<Figure> getFiguresByDesigner(Designer d) {
        log.info("serching movies by diretor " + d.getDesignerId());
        return movieDao.findByDesigner(d);
    }

    public List<Figure> getMoviesInCinema(Shop c) {
        log.info("searching movies played in cinema " + c.getId());
        return movieDao.findByShop(c);
    }

    public Figure getFigureById(int id) {
        log.info("searching movie by id " + id);
        return movieDao.findById(id);
    }

    public List<Shop> getAllCinemas() {
        log.info("searching all cinemas");
        return shopDao.findAll();
    }

    public List<Shop> getCinemasByMovie(Figure m) {
        log.info("searching cinemas by movie " + m.getId());
        return shopDao.findByFigure(m);
    }

    public Shop getCinemaById(int id) {
        log.info("searching cinema by id " + id);
        return shopDao.findById(id);
    }

    public List<Designer> getAllDesigners() {
        log.info("searching all directors");
        return directorDao.findAll();
    }

    public Designer getDesignerById(int id) {
        log.info("searching director by id " + id);
        return directorDao.findById(id);
    }

    @Override
    public Figure addFigure(Figure m) {
        log.info("about to add movie " + m);
        return movieDao.add(m);
    }

    @Override
    public Designer addDesigner(Designer d) {
        log.info("about to add director " + d);
        return directorDao.add(d);
    }

}
