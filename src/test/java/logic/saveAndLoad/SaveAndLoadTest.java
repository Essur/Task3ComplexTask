package logic.saveAndLoad;

import logic.manufacture.Manufacturer;
import logic.manufacture.Requisites;
import logic.manufacture.Souvenir;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SaveAndLoadTest {
    private Scanner in = new Scanner(System.in);
    private List<Manufacturer> manufacturerList;
    private Manufacturer manufacturer;

    @BeforeEach
    void setUp() {
        manufacturerList = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        manufacturerList.clear();
    }

    @Test
    void saveData(){
        manufacturer = new Manufacturer(123L,"Name","Country", new Requisites("Address","Abbreviation"));
        manufacturer.makeSouvenir(new Souvenir("Souvenir name", LocalDate.of(2021, Month.AUGUST,16),213.4));
        manufacturerList.add(manufacturer);
        if (!manufacturerList.isEmpty()){
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tests.dat"))) {
                oos.writeObject(manufacturerList);
                System.out.println("Successfully saved");
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("IO exception: " + e.getMessage());
            }
        } else System.err.println("Don`t have info to save");
    }

    @Test
    void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tests.dat"))) {
            List<Manufacturer> list = (List<Manufacturer>) ois.readObject();
            manufacturerList.addAll(list);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1,manufacturerList.size());
    }
}