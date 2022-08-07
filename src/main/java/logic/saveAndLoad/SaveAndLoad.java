package logic.saveAndLoad;


import logic.manufacture.Manufacturer;
import logic.shop.Shop;

import java.io.*;
import java.util.*;

/**<p>Class which save and load data in/from file</p>
 *Class is made with pattern memento*/
public class SaveAndLoad implements Serializable{
    private Shop shop;
    private List<Manufacturer> manufacturerList;
    private final Scanner in = new Scanner(System.in);

    public SaveAndLoad() {
        shop = new Shop();
        manufacturerList = new ArrayList<>();
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Shop getShop() {
        return shop;
    }

    public List<Manufacturer> getManufacturerList() {
        return manufacturerList;
    }

    public void setManufacturerList(List<Manufacturer> manufacturerList) {
        this.manufacturerList = manufacturerList;
    }

    public void clearLoaderData(){
        manufacturerList.clear();
    }
    public void saveData() {
        if (!manufacturerList.isEmpty()) {
            System.out.print("Enter the file name to save to: ");
            String name = in.nextLine();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name + ".dat"))) {
                oos.writeObject(manufacturerList);
                System.out.println("Successfully saved");
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("IO exception: " + e.getMessage());
            }
        } else System.err.println("Don`t have info to save");
    }

    public void loadData() {
        manufacturerList.clear();
        System.out.print("Enter the file name from where to read the information: ");
        String name = in.nextLine();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name + ".dat"))) {
            List<Manufacturer> list = (List<Manufacturer>) ois.readObject();
            manufacturerList.addAll(list);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Successfully loaded");
    }
}
