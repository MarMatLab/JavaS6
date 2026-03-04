package vod.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vod.repository.ShopDao;
import vod.repository.FigureDao;
import vod.repository.mem.MemShopDao;
import vod.repository.mem.MemFigureDao;
import vod.model.Shop;
import vod.service.impl.ShopServiceBean;

import java.util.List;

public class AmazonServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find SHOPZ!");

        ApplicationContext context = new
                AnnotationConfigApplicationContext("vod");
        ShopService service = context.getBean(ShopService.class);
        ShopService service2 = context.getBean(ShopService.class);

        List<Shop> shops = service.getAllShops();
        System.out.println(shops.size() + " shops found:");
        shops.forEach(System.out::println);

        String foo = context.getBean(String.class);
        System.out.println("foo string: " + foo);
    }
}
