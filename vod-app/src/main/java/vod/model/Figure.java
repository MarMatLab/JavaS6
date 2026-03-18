package vod.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Figure {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String material;

    @ManyToOne
    @JoinColumn(name = "designer_id")
    private Designer designer;
    private float price;

    @ManyToMany
    @JoinTable(
            name="figure_shop",
            joinColumns = @JoinColumn(name="figure_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id")
    )

    private List<Shop> shops = new ArrayList<>();


    public Figure(int id, String title, String poster, Designer director, float rating) {
        this.id = id;
        this.name = title;
        this.material = poster;
        this.designer = director;
        this.price = rating;
    }

    public Figure() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public void addShop(Shop c) {
        this.shops.add(c);
    }


   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (Float.compare(movie.rating, rating) != 0) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        return poster != null ? poster.equals(movie.poster) : movie.poster == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }*/

    @Override
    public String toString() {
        return "Figure{" +
                "name='" + name + '\'' +
                ", designer=" + designer +
                ", price=" + price +
                '}';
    }
}
