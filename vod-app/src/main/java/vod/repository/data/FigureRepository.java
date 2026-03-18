package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import vod.model.Figure;

public interface FigureRepository extends JpaRepository<Figure, Integer> {
}
