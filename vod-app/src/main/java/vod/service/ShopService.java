package vod.service;

import vod.model.Shop;
import vod.model.Figure;

import java.util.List;

public interface ShopService {

    Shop getShopById(int id);

    List<Shop> getAllShops();

    List<Shop> getShopsByFigure(Figure m);

    List<Figure> getFiguresInShop(Shop c);

    Shop addShop(Shop shop);
}
