package logic.reader;

import logic.manufacture.Manufacturer;
import logic.manufacture.Souvenir;

import java.time.LocalDate;
import java.util.*;

public class SouvenirReader extends Reader {
    List<Manufacturer> manufacturerList = new ArrayList<>();
    Scanner in = new Scanner(System.in);

    @Override
    public void add() {
        renewList();
        if (!manufacturerList.isEmpty()) {
            System.out.println("Creating souvenir");
            consignmentNote.printManufacturers();
            System.out.println("Choose manufacturer which make a souvenir (by id)");
            long id = in.nextLong();
            String name;
            int year, month, day;
            double price;
            System.out.println("Enter name");
            in.nextLine();
            name = in.nextLine();
            year = input("Enter year");
            month = input("Enter month (Example: January - 1)");
            day = input("Enter day");
            System.out.println("Enter price: ");
            price = in.nextDouble();
            consignmentNote.addSouvenir(id, new Souvenir(name, LocalDate.of(year, month, day), price));
            consignmentNote.printSouvenirList();
        } else System.err.println("No one manufacturers exist!");
    }
    private void renewList(){
        manufacturerList = consignmentNote.getManufacturerList();
    }

    @Override
    public void remove() {
        long id;
        String name;
        renewList();
        if (!manufacturerList.isEmpty()) {
            consignmentNote.printSouvenirList();
            System.out.println("Select the ID of the manufacturer you want to remove");
            id = in.nextLong();
            if(containsId(id)){
                System.out.println("Select the name of souvenir which need to remove");
                name = in.next();
                consignmentNote.removeSouvenir(id, name);
                consignmentNote.sendConsignmentNote();
                consignmentNote.printSouvenirList();
            } else System.err.println("Can`t find that manufacturer");
        } else System.err.println("No one manufacturers exist");
    }
    public void searchSouvenirsByYear(){
        renewList();
        if(!manufacturerList.isEmpty()) {
            int year = input("Enter year");
            consignmentNote.getShop().printSouvenirsReleasedInYear(year);
        } else System.err.println("Can`t search catalog is empty");
    }

    private boolean containsId(long id) {
        return manufacturerList.stream().anyMatch(manufacturer -> manufacturer.getId() == id);
    }

    private int input(String msg){
        System.out.println(msg);
        boolean flag = true;
            int digit = 0;
            while (flag) {
                try {
                    digit = in.nextInt();
                    flag = false;
                    break;
                } catch (Exception e) {
                    System.out.print("Wrong input! Retype: ");
                    in.next();
                }
            }
            return digit;
    }
}
