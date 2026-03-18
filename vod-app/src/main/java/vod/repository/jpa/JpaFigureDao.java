package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Designer;
import vod.model.Figure;
import vod.model.Shop;
import vod.repository.FigureDao;

import java.util.List;

@Repository
@Primary
public class JpaFigureDao implements FigureDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Figure> findAll() {
        return entityManager.createQuery("select f from Figure f").getResultList();
    }

    @Override
    public Figure findById(int id) {
        return entityManager.find(Figure.class, id);
    }

    @Override
    public List<Figure> findByDesigner(Designer d)
    {
        return entityManager.createQuery("select f from Figure f where f.designer=:designer")
                .setParameter("designer", d).getResultList();
    }

    @Override
    public List<Figure> findByShop(Shop s)
    {
        return entityManager.createQuery(
                "select f from Figure f inner join f.shops shop where shop=:shop")
                .setParameter("shop", s).getResultList();
    }

    @Override
    public Figure add(Figure f) {
        entityManager.persist(f);
        return f;
    }
}
