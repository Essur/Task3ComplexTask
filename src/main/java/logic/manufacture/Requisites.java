package logic.manufacture;

import java.io.Serial;
import java.io.Serializable;

public class Requisites implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234543521L;
    private String address;
    private String abbreviation;

    public Requisites(String address, String abbreviation) {
        this.address = address;
        this.abbreviation = abbreviation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return "Requisites{" +
                "address='" + address + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                '}';
    }
}
