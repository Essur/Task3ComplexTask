package logic.manufacture;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manufacturer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234543521L;
    private long id;
    private String name;
    private String country;
    private Requisites requisites;
    private List<Souvenir> souvenirList = new ArrayList<>();

    public Manufacturer(long id, String name, String country, Requisites requisites) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.requisites = requisites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRequisites(Requisites requisites) {
        this.requisites = requisites;
    }

    public Requisites getRequisites() {
        return requisites;
    }

    public List<Souvenir> getSouvenirList() {
        return souvenirList;
    }

    public void makeSouvenir(Souvenir souvenir){
        souvenirList.add(souvenir);
    }

    @Override
    public String toString() {
        return "{name='" + name + '\'' +
                ", country='" + country + '\'' +
                ' ' + requisites +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return id == that.id || Objects.equals(name, that.name) || Objects.equals(requisites, that.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, requisites, souvenirList);
    }
}
