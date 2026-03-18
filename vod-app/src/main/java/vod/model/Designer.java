package vod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int designerId;

    @Column(name="firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @OneToMany(mappedBy = "designer")
    @JsonIgnore
    private List<Figure> figures = new ArrayList<>();

    public Designer(int designerId, String firstName, String lastName) {
        this.designerId = designerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Designer() {
    }

    public int getDesignerId() {
        return designerId;
    }

    public int getId() {
        return this.designerId;
    }

    public void setDesignerId(int designerId) {
        this.designerId = designerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public void addFigure(Figure m) {
        this.figures.add(m);
    }

    @Override
    public String toString() {
        return "Designer{" +
                "id=" + designerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
