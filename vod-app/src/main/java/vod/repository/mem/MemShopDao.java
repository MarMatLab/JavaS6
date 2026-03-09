package vod.repository.mem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vod.repository.ShopDao;
import vod.model.Shop;
import vod.model.Figure;

import java.util.List;
import java.util.stream.Collectors;

@Repository("shopDao")
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

    @Override
    public Shop save(Shop shop) {
        int maxId = SampleData.shops.stream().sorted((c1,c2)->c2.getId()-c1.getId())
                .findFirst()
                .map(c->c.getId())
                .orElse(0);
        shop.setId(maxId+1);
        SampleData.shops.add(shop);
        return shop;
    }
}
