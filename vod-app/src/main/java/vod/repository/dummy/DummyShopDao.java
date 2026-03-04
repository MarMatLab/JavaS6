package vod.repository.dummy;

import org.springframework.stereotype.Component;
import vod.model.Figure;
import vod.model.Shop;
import vod.repository.ShopDao;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DummyShopDao implements ShopDao
{
    @Override
    public List<Shop> findAll()
    {
        return List.of();
    }

    @Override
    public Shop findById(int id)
    {
        return null;
    }

    @Override
    public List<Shop> findByFigure(Figure m) {
        return List.of();
    }
}
