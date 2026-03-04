package vod.repository.mem;

import vod.model.Shop;
import vod.model.Designer;
import vod.model.Figure;

import java.util.ArrayList;
import java.util.List;

class SampleData {

    static List<Shop> shops = new ArrayList<>();

    static List<Designer> designers = new ArrayList<>();

    static List<Figure> figures = new ArrayList<>();

    static {

        Designer takara = new Designer(1, "Takara", "Tomy");
        Designer nigdy = new Designer(2, "Nigdy", "WŻyciu");
        Designer hasbro = new Designer(3, "Hasbro", "Ssie");
        Designer mattel = new Designer(4, "Mattel", "Tragedia");

        Figure transformer = new Figure(1, "Transformer", "https://ocdn.eu/pulscms-transforms/1/D0gk9kuTURBXy8zYzFhMDRhZS1jOGRiLTQxN2YtOTcwYy1iNjRjZDBkMjc4MDYuanBlZ5GTBc0DFM0BvIGhMAU", takara, (float) 124.1);
        Figure wesele = new Figure(2, "Wesele", "https://fwcdn.pl/fpo/40/98/124098/7521214.6.jpg", takara, (float) 4.3);

        Figure legends = new Figure(3, "Legends", "https://i.iplsc.com/-/00094J03E94SMPSS-C122.jpg", nigdy, (float) 133.9);
        Figure marvel = new Figure(4, "Marvel", "https://bi.im-g.pl/im/5b/9b/12/z19510363V,-Pitbull--Nowe-porzadki---rez--Patryk-Vega--plakat.jpg", nigdy, (float) 133.1);

        Figure katyn = new Figure(5, "Katyn", "http://www.gokmichalowo.pl/imprezy2007/katyn/plakat_maly.jpg", hasbro, (float) 4.7);
        Figure tatarak = new Figure(6, "Tatarak", "http://gapla.fn.org.pl/public/cache/P21829-483x700.jpg", hasbro, (float) 4.4);

        Figure essential = new Figure(7, "Essential killing", "https://m.media-amazon.com/images/M/MV5BNTE5NjAxMTEzNl5BMl5BanBnXkFtZTcwMjYzMDQ0Ng@@._V1_UX182_CR0,0,182,268_AL_.jpg", mattel, (float) 4.9);
        Figure ferdydurke = new Figure(8, "Ferdydurke", "http://gapla.fn.org.pl/public/cache/P19423-483x700.jpg", mattel, (float) 4.3);

        bind(transformer, takara);
        bind(wesele, takara);

        bind(legends, nigdy);
        bind(marvel, nigdy);

        bind(katyn, hasbro);
        bind(tatarak, hasbro);

        bind(essential, mattel);
        bind(ferdydurke, mattel);

        Shop kinoteka = new Shop(1, "Kinoteka", "https://www.kinoteka.pl/img/logo.png");
        Shop podBaranami = new Shop(2, "Kino pod Baranami", "http://www.festiwalfilmuniemego.pl/wp-content/uploads/2015/11/Kino-pod-Baranami.png");
        Shop noweHoryzonty = new Shop(3, "Kino Nowe Horyzonty", "https://i2.wp.com/garretreza.pl/wp-content/uploads/2018/07/nh.jpg");
        Shop zak = new Shop(4, "Kino Zak", "https://static2.s-trojmiasto.pl/zdj/c/n/19/2276/250x0/2276445.jpg");

        bind(kinoteka, wesele);
        bind(noweHoryzonty, wesele);
        bind(noweHoryzonty, transformer);
        bind(noweHoryzonty, legends);

        bind(kinoteka, tatarak);
        bind(zak, tatarak);
        bind(zak, essential);
        bind(podBaranami, essential);
        bind(podBaranami, legends);

        figures.add(transformer);
        figures.add(wesele);
        figures.add(legends);
        figures.add(marvel);
        figures.add(katyn);
        figures.add(tatarak);
        figures.add(essential);
        figures.add(ferdydurke);

        designers.add(takara);
        designers.add(nigdy);
        designers.add(hasbro);
        designers.add(mattel);

        shops.add(kinoteka);
        shops.add(podBaranami);
        shops.add(noweHoryzonty);
        shops.add(zak);

    }

    private static void bind(Shop c, Figure m) {
        c.addFigure(m);
        m.addShop(c);
    }

    private static void bind(Figure m, Designer d) {
        d.addFigure(m);
        m.setDesigner(d);
    }

}
