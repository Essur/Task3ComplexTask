package main;

import logic.reader.ManufactureReader;
import logic.reader.Reader;
import logic.reader.SouvenirReader;
import logic.saveAndLoad.SaveAndLoad;
import logic.shop.ConsignmentNote;
import logic.shop.Shop;

import java.util.Scanner;

public class Main {
    ManufactureReader manufacturerReader = new ManufactureReader();
    ConsignmentNote consignmentNote = new ConsignmentNote();
    SouvenirReader souvenirReader = new SouvenirReader();
    SaveAndLoad saveAndLoad = new SaveAndLoad();
    Shop shop = new Shop();
    Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        menu();
    }
    private void menu(){
        manufacturerReader.setConsignmentNote(consignmentNote);
        souvenirReader.setConsignmentNote(consignmentNote);
        shop = consignmentNote.getShop();
        loop: while(true) {
            switch (chooseAction()) {
                case "1" -> manufacturerReader.add();
                case "2" -> souvenirReader.add();
                case "3" -> manufacturerReader.remove();
                case "4" -> souvenirReader.remove();
                case "5" -> shop.printAllManufacturers();
                case "6" -> shop.printCatalog();
                case "7" -> shop.printManufacturerInfo(manufacturerReader.chooseManufacturerById());
                case "8" -> {
                    String country = manufacturerReader.chooseManufacturerByCountry();
                    if (country != null) {
                        shop.printManufacturerInfo(country);
                    }
                }
                case "9" -> shop.printLessPrice(manufacturerReader.chooseManufacturerByPrice());
                case "10" -> manufacturerReader.searchManufacturerBySouvenirAndDate();
                case "11" -> souvenirReader.searchSouvenirsByYear();
                case "12" -> saveToFile();
                case "13" -> loadFromFile();
                case "Exit", "exit" -> {
                    System.out.println("Exiting, thx for using!");
                    break loop;
                }
                default -> System.err.println("Current action does not exists!");
            }
        }
    }
    private String chooseAction(){
        System.out.println("Choose action:");
        System.out.print("""
                    1 - Create manufacturer
                    2 - Create souvenir
                    3 - Remove manufacturer
                    4 - Remove souvenir
                    5 - Show all manufacturers
                    6 - Show all manufacturers and their souvenirs
                    7 - Show information about souvenirs of the selected manufacturer
                    8 - Show information about souvenirs by country
                    9 - Show information about manufacturers whose prices less than specified
                    10 - Search souvenirs by name and release year
                    11 - Search souvenirs by year of release
                    12 - Save data from catalog in file
                    13 - Load data in catalog from file
                    Enter exit for finish program
                    """
        );
        return in.nextLine();
    }
    public void loadFromFile(){
        saveAndLoad.clearLoaderData();
        saveAndLoad.loadData();
        shop.clearCatalog();
        shop.setCatalog(saveAndLoad.getManufacturerList());
        shop.sendToConsignmentNote(consignmentNote);
        manufacturerReader.setConsignmentNote(shop.getConsignmentNote());
        souvenirReader.setConsignmentNote(shop.getConsignmentNote());
    }
    public void saveToFile(){
        saveAndLoad.setManufacturerList(shop.getCatalog());
        saveAndLoad.saveData();
    }
}