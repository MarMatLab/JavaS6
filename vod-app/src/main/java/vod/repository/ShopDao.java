package vod.repository;

import vod.model.Shop;
import vod.model.Figure;

import java.util.List;

public interface ShopDao {

    List<Shop> findAll();

    Shop findById(int id);

    List<Shop> findByFigure(Figure m);

    Shop save(Shop shop);
}
