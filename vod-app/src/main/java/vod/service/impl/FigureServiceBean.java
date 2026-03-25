package vod.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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
@RequiredArgsConstructor
public class FigureServiceBean implements FigureService {

    private static final Logger log = Logger.getLogger(FigureService.class.getName());
    //private final DataSourceTransactionManager transactionManager;

    private final DesignerDao directorDao;
    private final ShopDao shopDao;
    private final FigureDao figureDao;
    private final PlatformTransactionManager transactionManager;

    public List<Figure> getAllFigures() {
        log.info("searching all movies...");
        return figureDao.findAll();
    }

    public List<Figure> getFiguresByDesigner(Designer d) {
        log.info("serching movies by diretor " + d.getDesignerId());
        return figureDao.findByDesigner(d);
    }

    public List<Figure> getMoviesInCinema(Shop c) {
        log.info("searching movies played in cinema " + c.getId());
        return figureDao.findByShop(c);
    }

    public Figure getFigureById(int id) {
        log.info("searching movie by id " + id);
        return figureDao.findById(id);
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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Figure addFigure(Figure m) {
        log.info("about to add fiiigjure " + m);
        m = figureDao.add(m);

        if (m.getName().equals("Apocalypse Now"))
        {
            throw new RuntimeException("not yet!");
        }
        return m;
    }

    @Override
    public Designer addDesigner(Designer d) {
        log.info("about to add director " + d);
        return directorDao.add(d);
    }

}
