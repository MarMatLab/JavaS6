package vod.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vod.model.Figure;
import vod.model.Shop;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    List<Shop> findAllByNameContaining(String name);

    @Query("select s from Shop s inner join s.figure figure where figure=:figure")
    List<Shop> findAllByFigure(@Param("figure") Figure figure);
}
