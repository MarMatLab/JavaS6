package vod.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Figure;
import vod.model.Shop;
import vod.repository.ShopDao;

import java.util.List;

@Repository
//@Primary
public class JpaShopDao implements ShopDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> findAll()
    {
        return entityManager.createQuery("select s from Shop s").getResultList();
    }

    @Override
    public Shop findById(int id)
    {
        return entityManager.find(Shop.class, id);
    }

    @Override
    public List<Shop> findByFigure(Figure f)
    {
        return entityManager.createQuery("select s from Shop s inner join s.figure figure where figure=:figure")
                .setParameter("figure", f).getResultList();
    }

    @Override
    public Shop save(Shop shop)
    {
        entityManager.persist(shop);
        return shop;
    }
}
