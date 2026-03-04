package vod.repository.mem;

import org.springframework.stereotype.Component;
import vod.repository.FigureDao;
import vod.model.Shop;
import vod.model.Designer;
import vod.model.Figure;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemFigureDao implements FigureDao {
    @Override
    public List<Figure> findAll() {
        return SampleData.figures;
    }

    @Override
    public Figure findById(int id) {
        return SampleData.figures.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Figure> findByDesigner(Designer d) {
       return SampleData.figures.stream().filter(m -> m.getDesigner() == d).collect(Collectors.toList());
    }

    @Override
    public List<Figure> findByShop(Shop c) {
        return SampleData.figures.stream().filter(m -> m.getShops().contains(c)).collect(Collectors.toList());
    }

    @Override
    public Figure add(Figure m) {
        int max = SampleData.figures.stream().max((m1, m2) -> m1.getId() - m2.getId()).get().getId();
        m.setId(++max);
        SampleData.figures.add(m);
        return m;
    }
}
