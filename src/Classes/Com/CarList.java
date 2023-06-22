//  @author Anh Linh
package Classes.Com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

public class CarList {

    ArrayList<Car> carList;
    BrandList bList;
    Validation val;
    Inputter input;

    public CarList(BrandList bList) {
        this.carList = new ArrayList<>();
        this.bList = bList;
        this.input = new Inputter();
        this.val = new Validation();
    }

    public boolean loadFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            System.err.println("Error: Cannot read the file " + filename);
            return false;
        } else {
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(filename);
                int data = fin.read();
                StringBuilder line = new StringBuilder();
                while (data != -1) {
                    if ((char) data == '\n') {
                        String temp = null;
                        temp = line.toString();
                        String[] item = temp.split(", ");
                        String carID = String.valueOf(item[0].trim());
                        Brand brand = null;
                        for (Brand x : bList.brandList) {
                            if (x.getBrandID().compareTo(String.valueOf(item[1].
                                    trim())) == 0) {
                                brand = x;
                                break;
                            }
                        }
                        String color = String.valueOf(item[2].trim());
                        String frameID = String.valueOf(item[3].trim());
                        String engineID = String.valueOf(item[4].trim());
                        Car newCar = new Car(carID, brand, color, frameID, 
                                engineID);
                        carList.add(newCar);
                        line.delete(0, line.length());
                        data = fin.read();
                        continue;
                    }
                    line.append((char) data);
                    data = fin.read();
                }
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fin != null) {
                        fin.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    public boolean saveToFile(String filename) {
        Menu menu = new Menu();
        int choice = -1;
        ArrayList<String> ops = new ArrayList<>();
        ops.add("Yes");
        ops.add("No");
        System.out.println("Check data before saving.");
        carList.forEach(System.out::println);
        System.out.println("Do you want to save data to file " + filename + "?");
        choice = menu.int_getChoice(ops);
        if (choice == 2) {
            System.out.println("The data is not saved to file " + filename);
            return false;
        } else {
            try {
                File fileName = new File(filename);
                FileWriter fw = new FileWriter(fileName);
                try (Writer output = new BufferedWriter(fw)) {
                    int numEntries = carList.size();
                    for (int i = 0; i < numEntries; i++) {
                        output.write(carList.get(i).toString() + "\n");
                    }
                }
                System.out.println("Save data to file " + filename + " successfully");
            } catch (Exception e) {
                System.err.println("Cannot save data to the file " + filename);
            }
        }
        return true;
    }

    public int searchID(String carID) {
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getCarID().compareTo(carID) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getFrameID().compareToIgnoreCase(fID) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getEngineID().compareToIgnoreCase(eID) == 0) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        String carID = null;
        Brand brand = null;
        String color = null;
        String frameID = null;
        String engineID = null;
        int check;
        do {
            check = 1;
            carID = input.inputString("ID of car").trim();
            for (Car x : carList) {
                if (val.checkDuplicated(x.getCarID(), carID, "car ID")) {
                    check = 0;
                    break;
                }
            }
        } while (check == 0);
        System.out.println("Please enter the brand ID of the car");
        brand = (Brand) bList.getUserChoice();
        color = input.inputString("color of car").trim();
        do {
            check = 1;
            frameID = input.inputString("frame ID of car (F0000)");
            for (Car x : carList) {
                if (val.checkDuplicated(x.getFrameID(), frameID, "frame ID")) {
                    check = 0;
                    break;
                }
            }
        } while (check == 0 || !val.checkFrameIdFormat(frameID));
        do {
            check = 1;
            engineID = input.inputString("engine ID of car (E0000)");
            for (Car x : carList) {
                if (val.checkDuplicated(x.getEngineID(), engineID, "engine ID")) {
                    check = 0;
                    break;
                }
            }
        } while (check == 0 || !val.checkEngineIdFormat(engineID));
        Car newCar = new Car(carID, brand, color, frameID, engineID);
        carList.add(newCar);
        System.out.println("Add: " + newCar);
        System.out.println("Add new car successfully...");
    }

    public void printBasedBrandName() {
        String aPartOfBrandName;
        aPartOfBrandName = input.inputString("the part of brand name").trim();
        int count = 0;
        count = carList.stream().filter((x) -> 
        (x.getBrand().getBrandName().contains(aPartOfBrandName))).map((Car x) -> {
            System.out.println(x.screenString());
            return x;
        }).map((Car _item) -> 1).reduce(count, Integer::sum);
        if (count == 0) {
            System.err.println("No car is detected!");
        }
    }

    public boolean removeCar() {
        String carID;
        int pos;
        carID = input.inputString("ID of car want to remove").trim();
        pos = searchID(carID);
        if (pos < 0) {
            System.err.println("Not found!");
            return false;
        } else {
            carList.remove(pos);
            System.out.println("Remove successfully!");
        }
        return true;
    }

    public boolean updateCar() {
        String carID;
        int pos;
        carID = input.inputString("ID of car want to update").trim();
        pos = searchID(carID);
        if (pos < 0) {
            System.err.println("Not found!");
            return false;
        } else {
            System.out.println("--------\nThe car you want to update:");
            System.out.println(carList.get(pos).screenString() + "\n--------");
            System.out.println("Please enter new information for the car");
            Brand brand = null;
            String color = null;
            String frameID = null;
            String engineID = null;

            System.out.println("Please enter the brand ID of the car");
            brand = (Brand) bList.getUserChoice();
            color = input.inputString("color of car").trim();

            int check;
            do {
                check = 1;
                frameID = input.inputString("frame ID of car (F0000)");
                if (carList.get(pos).getFrameID().compareTo(frameID) != 0) {
                    for (Car x : carList) {
                        if (val.checkDuplicated(x.getFrameID(), frameID, "frame ID")) {
                            check = 0;
                            break;
                        }
                    }
                }
            } while (check == 0 || !val.checkFrameIdFormat(frameID));

            do {
                check = 1;
                engineID = input.inputString("engine ID of car (E0000)");
                if (carList.get(pos).getEngineID().compareTo(engineID) != 0) {
                    for (Car x : carList) {
                        if (val.checkDuplicated(x.getEngineID(), engineID, "engine ID")) {
                            check = 0;
                            break;
                        }
                    }
                }
            } while (check == 0 || !val.checkEngineIdFormat(engineID));

            System.out.println("Update: " + carList.get(pos));
            carList.get(pos).setBrand(brand);
            carList.get(pos).setColor(color);
            carList.get(pos).setFrameID(frameID);
            carList.get(pos).setEngineID(engineID);
            System.out.println("To: " + carList.get(pos));
            System.out.println("Update successfully!");
        }
        return true;
    }

    public void listCars() {
        if (carList.isEmpty()) {
            System.err.println("The list is empty");
        } else {
            Collections.sort(carList, (Car o1, Car o2) -> o1.comparedTo(o2));
            System.out.println("List of cars shown below");
            carList.forEach((Car x) -> {
                System.out.println("--------\n" + x.screenString());
            });
        }
    }
}
