package logic.shop;

import logic.manufacture.Manufacturer;
import logic.manufacture.Souvenir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Class Shop work with catalog of Souvenirs and their Manufacturer*/
public class Shop {
    private ConsignmentNote consignmentNote;
    private List<Manufacturer> catalog;
    private static final String splitter = "\n---------------------------------------------\n";

    public Shop() {
        catalog = new ArrayList<>();
    }

    public void setConsignmentNote(ConsignmentNote consignmentNote) {
        this.consignmentNote = consignmentNote;
    }

    public ConsignmentNote getConsignmentNote() {
        return consignmentNote;
    }

    public List<Manufacturer> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<Manufacturer> catalog) {
        this.catalog = catalog;
    }
    public void sendToConsignmentNote(ConsignmentNote consignmentNote){
        this.consignmentNote = consignmentNote;
        consignmentNote.setManufacturerList(catalog);
    }
    public void clearCatalog(){
        this.catalog.clear();
    }

    /**Method which print the manufacturer by the given id*/
    public void printManufacturerInfo(long id){
        catalog.forEach(manufacturer -> {
            if(manufacturer.getId() == id)
                System.out.println(splitter + "Manufacturer " + manufacturer.getId() + ": " + '\n' + "Souvenirs: " + manufacturer.getSouvenirList() + splitter);
        });
    }

    /**Method which print the manufacturers from entered country*/
    public void printManufacturerInfo(String country){
        catalog.forEach(manufacturer -> {
            if(country.equals(manufacturer.getCountry()))
                System.out.println(splitter + "Manufacturer " + manufacturer.getId() + ": " + '\n' + "Souvenirs: " + manufacturer.getSouvenirList() + splitter);
        });
    }

    /**Method print all manufacturers and their souvenirs if the price of the souvenirs made is lower than entered*/
    public void printLessPrice(double price){
        List<Manufacturer> list = catalog.stream().filter(manufacturer -> manufacturer.getSouvenirList().stream().allMatch(souvenir -> souvenir.getPrice() < price)).toList();
        if (!list.isEmpty()){
            list.forEach(manufacturer -> System.out.println(splitter + "Manufacturer " + manufacturer.getId() + ": " + '\n' +
                    "Souvenirs: " +  '\n' + manufacturer.getSouvenirList() + splitter));
        } else System.err.println("No one souvenir below the set price");
    }

    /**Method which print manufacturers and their souvenirs with selected name of souvenir and year of production*/
    public void printManufacturerWithSouvenirAndYear(String name, int year){
        List<Manufacturer> manufacturers = catalog.stream().filter(manufacturer -> manufacturer.getSouvenirList().stream()
                .anyMatch(souvenir -> name.equals(souvenir.getName()) && souvenir.getDateOfRelease().getYear() == year)).toList();
        if(!manufacturers.isEmpty()){
            manufacturers.forEach(manufacturer -> System.out.println(splitter + "Manufacturer " + manufacturer.getId() + splitter));
        } else System.err.println("No one matches found");
    }

    /**Search souvenirs by year of release*/
    public void printSouvenirsReleasedInYear(int year){
        List<Souvenir> souvenirs = catalog.stream().flatMap(manufacturer -> manufacturer.getSouvenirList().stream()
                .filter(souvenir -> souvenir.getDateOfRelease().getYear() == year)).toList();
        souvenirs.forEach(souvenir -> System.out.print(splitter + souvenir + "\n"));
        System.out.println(splitter);
    }

    /**Method print all manufacturers*/
    public void printAllManufacturers(){
        if(!catalog.isEmpty()){
            System.out.println("Manufacturers");
            catalog.forEach(System.out::println);
        } else System.err.println("Catalog is empty");
    }

    /**Method print all manufacturers with their souvenirs*/
    public void printCatalog(){
        if (!catalog.isEmpty()) {
            catalog.forEach(manufacturer -> System.out.println(splitter + "Manufacturer: " + manufacturer + '\n' + "Souvenirs: " + manufacturer.getSouvenirList() + splitter));
        } else System.err.println("Catalog is empty");
    }

    @Override
    public String toString() {
        return "Shop{" +
                "catalog=" + catalog +
                '}';
    }
}
