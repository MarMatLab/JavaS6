package vod.repository;

import vod.model.Shop;
import vod.model.Designer;
import vod.model.Figure;

import java.util.List;

public interface FigureDao {

    List<Figure> findAll();

    Figure findById(int id);

    List<Figure> findByDesigner(Designer d);

    List<Figure> findByShop(Shop c);

    Figure add(Figure m);

}
