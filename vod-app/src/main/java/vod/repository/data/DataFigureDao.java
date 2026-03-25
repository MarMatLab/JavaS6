package vod.repository.data;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vod.model.Designer;
import vod.model.Figure;
import vod.model.Shop;
import vod.repository.FigureDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataFigureDao implements FigureDao {

    private final FigureRepository repository;

    @Override
    public List<Figure> findAll()
    {
        return repository.findAll();
    }

    @Override
    public Figure findById(int id)
    {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Figure> findByDesigner(Designer d)
    {
        return repository.findAllByDesigner(d);
    }

    @Override
    public List<Figure> findByShop(Shop s)
    {
        return repository.findAllByShopsContaining(s);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Figure add(Figure f)
    {
        return repository.save(f);
    }
}
