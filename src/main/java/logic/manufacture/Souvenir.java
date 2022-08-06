package logic.manufacture;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Souvenir implements Serializable {
    private String name;
    private LocalDate dateOfRelease;
    private double price;

    public Souvenir(String name, LocalDate dateOfRelease, double price) {
        this.name = name;
        this.dateOfRelease = dateOfRelease;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\n{" +
                "Souvenir name='" + name + '\'' +
                ", dateOfRelease=" + dateOfRelease.format(DateTimeFormatter.ofPattern("dd MM yyyy")) +
                ", price=" + price +
                '}';
    }
}
