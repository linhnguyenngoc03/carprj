//  @author Anh Linh
package Classes.Com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class BrandList {

    ArrayList<Brand> brandList;
    Validation val;
    Inputter input;

    public BrandList() {
        this.brandList = new ArrayList<>();
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
                        String brandID = String.valueOf(item[0].trim());
                        String brandName = String.valueOf(item[1].trim());
                        String[] item2 = (item[2].trim()).split(": ");
                        String soundBrand = String.valueOf(item2[0].trim());
                        double price = Double.parseDouble(String.valueOf(item2[1].trim()));
                        Brand newBrand = new Brand(brandID, brandName, soundBrand, price);
                        brandList.add(newBrand);
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
        }
        return true;
    }

    public boolean saveToFile(String filename) {
        Menu menu = new Menu();
        int choice = -1;
        ArrayList<String> ops = new ArrayList<>();
        ops.add("Yes");
        ops.add("No");
        System.out.println("Please check data before saving.");
        brandList.forEach(System.out::println);
        System.out.println("Save data to file " + filename + "? Y/N");
        choice = menu.int_getChoice(ops);
        if (choice == 2) {
            System.out.println("The data is not saved to file " + filename);
            return false;
        } else {
            try {
                File fileName = new File(filename);
                FileWriter fw = new FileWriter(fileName);
                try (Writer output = new BufferedWriter(fw)) {
                    int numEntries = brandList.size();
                    for (int i = 0; i < numEntries; i++) {
                        output.write(brandList.get(i).toString() + "\n");
                    }
                }
                System.out.println("Sucessfully save data to file " + filename);
            } catch (Exception e) {
                System.err.println("Cannot save data to the file " + filename);
            }
            return true;
        }
    }

    public int searchID(String bID) {
        for (int i = 0; i < brandList.size(); i++) {
            if (brandList.get(i).getBrandID().compareTo(bID) == 0) {
                return i;
            }
        }
        return -1;
    }

    public void printBrand(int pos) {
        System.out.println(brandList.get(pos));
    }

    public Brand getUserChoice() {
        Menu menu = new Menu();
        return (Brand) menu.ref_getChoice(brandList);
    }

    public void addBrand() {
        String brandID = null;
        String brandName = null;
        String soundBrand = null;
        double price = 0;
        int check;
        do {
            check = 1;
            brandID = input.inputString("ID of brand").trim();
            for (Brand x : brandList) {
                if (val.checkDuplicated(x.getBrandID(), brandID, "brand ID")) {
                    check = 0;
                    break;
                }
            }
        } while (check == 0);
        brandName = input.inputString("name of brand").trim();
        soundBrand = input.inputString("sound of brand").trim();
        price = input.inputPositiveDouble("price of brand");
        Brand newBrand = new Brand(brandID, brandName, soundBrand, price);
        System.out.println("Add: " + newBrand);
        brandList.add(newBrand);
        System.out.println("Add new brand successfully!");
    }

    public void updateBrand() {
        String brandID;
        int pos;
        brandID = input.inputString("ID of brand").trim();
        pos = searchID(brandID.trim());
        if (pos < 0) {
            System.err.println("Not found!");
        } else {
            System.out.println("--------\nThe brand you want to update:");
            System.out.println(brandList.get(pos).toString() + "\n--------");
            System.out.println("Please enter new information for the brand");
            String brandName;
            String soundBrand;
            double price;
            brandName = input.inputString("update name of brand").trim();
            soundBrand = input.inputString("update sound of brand").trim();
            price = input.inputPositiveDouble("update price of brand");
            System.out.println("Update: " + brandList.get(pos));
            brandList.get(pos).setBrandName(brandName);
            brandList.get(pos).setSoundBrand(soundBrand);
            brandList.get(pos).setPrice(price);
            System.out.println("To: " + brandList.get(pos));
            System.out.println("Update successfully!");
        }
    }

    public void listBrands() {
        if (brandList.isEmpty()) {
            System.err.println("The list is empty");
        } else {
            System.out.println("List of brands:");
            brandList.forEach(System.out::println);
        }
    }
}
