package logic.reader;

import logic.manufacture.Manufacturer;
import logic.manufacture.Requisites;

import java.util.*;
import java.util.stream.Collectors;

public class ManufactureReader extends Reader {
    List<Manufacturer> manufacturerList = new ArrayList<>();
    ById byId = new ById();
    ByCountry byCountry = new ByCountry();
    Scanner in = new Scanner(System.in);
    @Override
    public void add() {
        System.out.println("Creating manufacturer");
        long id;
        String name, country, address, abbreviation;
        id = inputLong("Enter id: ");
        System.out.println("Enter name: ");
        in.nextLine();
        name = in.nextLine();
        System.out.println("Enter country: ");
        country = in.nextLine();
        System.out.println("Enter address: ");
        address = in.nextLine();
        System.out.println("Enter abbreviation: ");
        abbreviation = in.nextLine();
        Manufacturer manufacturer = new Manufacturer(id,name,country,new Requisites(address,abbreviation));
        replaceOrAdd(manufacturer);
    }
    private void replaceOrAdd(Manufacturer manufacturer) {
        if(!isExist(manufacturer)){
            addToConsignmentNote(manufacturer);
        } else {
            System.err.println("Manufacturer with that id was exist");
            System.out.println("Do you want replace that manufacturer? Y/N");
            String result = in.next();
            if ("Y".equalsIgnoreCase(result)){
               addToConsignmentNote(manufacturer);
            }
        }
    }
    private void addToConsignmentNote(Manufacturer manufacturer) {
        consignmentNote.addManufacturer(manufacturer);
        consignmentNote.sendConsignmentNote();
        consignmentNote.printManufacturers();
        renewList();
    }


    @Override
    public void remove() {
        if(!manufacturerList.isEmpty()) {
            long id = byId.chooseId("remove");
            if (byId.containsId(id)) {
                consignmentNote.removeManufacturer(id);
                consignmentNote.sendConsignmentNote();
                consignmentNote.printManufacturers();
            } else System.err.println("Can`t find that manufacturer");
        }else System.err.println("Can`t search catalog is empty");
    }

    public long chooseManufacturerById(){
        renewList();
        if(!manufacturerList.isEmpty()) {
            long id = byId.chooseId("show");
            if (byId.containsId(id)) {
                return id;
            } else System.err.println("Wrong id!");
        } else System.err.println("Can`t search catalog is empty");
        return 0;
    }
    public String chooseManufacturerByCountry(){
        renewList();
        if(!manufacturerList.isEmpty()) {
            String country = byCountry.chooseCountry("show");
            if (byCountry.containsCountry(country)) {
                return country;
            } else System.err.println("Wrong country");
        } else System.err.println("Can`t search catalog is empty");
        return null;
    }

    public double chooseManufacturerByPrice() {
        renewList();
        if (!manufacturerList.isEmpty()) {
            System.out.println("Enter the price");
            boolean flag = true;
            double price = 0f;
            while (flag) {
                try {
                    price = in.nextDouble();
                    flag = false;
                    break;
                } catch (Exception e) {
                    System.err.print("Wrong input! Retype: ");
                    in.nextDouble();
                }
            }
            return price;
        } else System.err.println("Can`t search catalog is empty");
        return 0;
    }

    public void searchManufacturerBySouvenirAndDate(){
        renewList();
        if(!manufacturerList.isEmpty()) {
            System.out.println("Enter the name of souvenir ");
            in.nextLine();
            String name = in.nextLine();
            int year = inputInt("Enter the year in which the souvenir was made");
            consignmentNote.getShop().printManufacturerWithSouvenirAndYear(name, year);
        } else System.err.println("Can`t search catalog is empty");
    }

    public void renewList(){
        manufacturerList = consignmentNote.getManufacturerList();
    }

    private boolean isExist(Manufacturer manufacturer){
        return manufacturerList.stream().findFirst().filter(manufacture -> manufacture.equals(manufacturer)).isPresent();
    }

    private long inputLong(String msg){
        System.out.println(msg);
        boolean flag = true;
        long digit = 0;
        while(flag){
            try{
                digit = in.nextLong();
                flag = false;
                break;
            } catch(Exception e){
                System.err.print("Wrong input! Retype: ");
                in.nextLong();
            }
        }
        return digit;
    }
    private int inputInt(String msg){
        System.out.println(msg);
        boolean flag = true;
        int digit = 0;
        while(flag){
            try{
                digit = in.nextInt();
                flag = false;
                break;
            } catch(Exception e){
                System.err.print("Wrong input! Retype: ");
                in.nextInt();
            }
        }
        return digit;
    }

    protected class ById{
        public boolean containsId(long id) {
            return manufacturerList.stream().anyMatch(manufacturer -> manufacturer.getId() == id);
        }
        public long chooseId(String msg){
            renewList();
            if (!manufacturerList.isEmpty()) {
                System.out.println("Select the ID of the manufacturer you want to " + msg);
                consignmentNote.printManufacturers();
                consignmentNote.sendConsignmentNote();
                return inputLong("id");
            } else {
                System.err.println("No one manufacturers, can`t " + msg);
            }
            return 0;
        }
    }
    protected class ByCountry{
        public boolean containsCountry(String country) {
            return manufacturerList.stream().anyMatch(manufacturer -> manufacturer.getCountry().equals(country));
        }
        public String chooseCountry(String msg){
            renewList();
            if (!manufacturerList.isEmpty()) {
                System.out.println("Select the country of the manufacturer you want to " + msg);
                consignmentNote.sendConsignmentNote();
                System.out.println("Enter country: ");
                in.nextLine();
                return in.nextLine();
            } else {
                System.err.println("No one manufacturers, can`t " + msg);
            }
            return null;
        }
    }
}
