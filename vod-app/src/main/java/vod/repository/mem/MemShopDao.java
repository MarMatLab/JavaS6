package vod.repository.mem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import vod.repository.ShopDao;
import vod.model.Shop;
import vod.model.Figure;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class MemShopDao implements ShopDao {

    @Override
    public List<Shop> findAll() {
        return SampleData.shops;
    }

    @Override
    public Shop findById(int id) {
        return SampleData.shops.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Shop> findByFigure(Figure m) {
        return SampleData.shops.stream().filter(c -> c.getFigure().contains(m)).collect(Collectors.toList());
    }
}
