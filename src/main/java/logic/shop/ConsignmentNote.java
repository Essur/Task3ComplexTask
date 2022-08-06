package logic.shop;

import logic.manufacture.Manufacturer;
import logic.manufacture.Souvenir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Class ConsignmentNote work with CRUD (create;remove;update;delete) operations of program
 * And send done map of Manufacturers and Souvenirs in the Shop class*/
public class ConsignmentNote {
    private Shop shop = new Shop();
    private List<Manufacturer> manufacturerList;


    public ConsignmentNote() {
        manufacturerList = new ArrayList<>();
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Manufacturer> getManufacturerList() {
        return manufacturerList;
    }


    public void setManufacturerList(List<Manufacturer> manufacturerList) {
        this.manufacturerList = manufacturerList;
    }

    public void addManufacturer(Manufacturer manufacturer){
        manufacturerList.add(manufacturer);
    }
   public void addSouvenir(long id, Souvenir souvenir){
       for (Manufacturer manufacturer : manufacturerList) {
           if(manufacturer.getId() == id){
               manufacturer.makeSouvenir(souvenir);
           }
       }
    }
    public void removeManufacturer(long id){
        manufacturerList.removeIf(manufacturer -> manufacturer.getId() == id);
    }
    public void removeSouvenir(long id, String name){
         manufacturerList.stream().filter(manufacturer -> manufacturer.getId() == id)
                 .forEach(manufacturer -> manufacturer.getSouvenirList().removeIf(souvenir -> name.equals(souvenir.getName())));
    }
    public void sendConsignmentNote(){
        shop.setConsignmentNote(this);
        shop.setCatalog(this.manufacturerList);
    }

    public void printSouvenirList(){
        manufacturerList.stream().map(manufacturer ->  "Manufacturer " + manufacturer.getId() + ": "+ '\n' + manufacturer + '\n' + "Souvenirs: " + manufacturer.getSouvenirList()).forEach(System.out::println);
    }
    public void printManufacturers(){
        System.out.println("Manufacturers: ");
        manufacturerList.forEach(manufacturer -> System.out.println("Manufacturer "+ manufacturer.getId() + ": " + '\n' + manufacturer + '\n'));
    }
}
