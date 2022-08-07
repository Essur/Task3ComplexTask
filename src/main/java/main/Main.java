package main;

import logic.reader.ManufactureReader;
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
                case "5" -> clearLists();
                case "6" -> shop.printAllManufacturers();
                case "7" -> shop.printCatalog();
                case "8" -> shop.printManufacturerInfo(manufacturerReader.chooseManufacturerById());
                case "9" -> searchByCountry();
                case "10" -> shop.printLessPrice(manufacturerReader.chooseManufacturerByPrice());
                case "11" -> manufacturerReader.searchManufacturerBySouvenirAndDate();
                case "12" -> souvenirReader.searchSouvenirsByYear();
                case "13" -> saveToFile();
                case "14" -> loadFromFile();
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
                    5 - Remove all manufacturers and their souvenirs
                    6 - Show all manufacturers
                    7 - Show all manufacturers and their souvenirs
                    8 - Show information about souvenirs of the selected manufacturer
                    9 - Show information about souvenirs by country
                    10 - Show information about manufacturers whose prices less than specified
                    11 - Search the manufacturer by the name of the souvenir and the year of made
                    12 - Search souvenirs by year of release
                    13 - Save data from catalog in file
                    14 - Load data in catalog from file
                    Enter exit for finish program
                    """
        );
        return in.nextLine();
    }
    private void loadFromFile(){
        saveAndLoad.clearLoaderData();
        saveAndLoad.loadData();
        shop.clearCatalog();
        shop.setCatalog(saveAndLoad.getManufacturerList());
        shop.sendToConsignmentNote(consignmentNote);
    }
    private void saveToFile(){
        saveAndLoad.setManufacturerList(shop.getCatalog());
        saveAndLoad.saveData();
    }
    private void clearLists(){
        shop.clearCatalog();
        consignmentNote.clearList();
    }

    private void searchByCountry(){
        String country = manufacturerReader.chooseManufacturerByCountry();
        if (country != null) {
            shop.printManufacturerInfo(country);
        }
    }
}