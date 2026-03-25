package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Designer;
import vod.model.Figure;
import vod.model.Shop;

import java.util.List;

public interface FigureRepository extends JpaRepository<Figure, Integer> {
    List<Figure> findAllByDesigner(Designer d);

    List<Figure> findAllByShopsContaining(Shop s);
}
