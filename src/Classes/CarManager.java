//  @author Anh Linh
package Classes;

import Classes.Com.BrandList;
import Classes.Com.CarList;
import Classes.Com.Inputter;
import Classes.Com.Menu;
import java.util.ArrayList;

public class CarManager {

    public static void main(String[] args) {
        ArrayList<String> ops = new ArrayList<>();
        ops.add("List all brands");
        ops.add("Add a new brand");
        ops.add("Search a brand based on its ID");
        ops.add("Update a brand");
        ops.add("Save brands to the file, named brands.txt");
        ops.add("List all cars in ascending order of brand names");
        ops.add("List cars based on a part of an input brand name");
        ops.add("Add a car");
        ops.add("Remove a car based on its ID");
        ops.add("Update a car based on its ID");
        ops.add("Save cars to file, named cars.txt");
        ops.add("Exit");
        BrandList brandList = new BrandList();
        brandList.loadFromFile("brands.txt");
        CarList carList = new CarList(brandList);
        carList.loadFromFile("cars.txt");
        int option = -1;
        Inputter input = new Inputter();
        Menu menu = new Menu();
        System.out.println("Welcome to the program!");
        do {
            System.out.println("-----------------------------------");
            option = menu.int_getChoice(ops);
            System.out.println("-----------------------------------");
            switch (option) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    String searchID = null;
                    searchID = input.inputString("brand ID you want to search");
                    if (brandList.searchID(searchID) == -1) {
                        System.err.println("Not found!");
                    } else {
                        brandList.printBrand(brandList.searchID(searchID));
                    }
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile("brands.txt");
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    carList.removeCar();
                    break;
                case 10:
                    carList.updateCar();
                    break;
                case 11:
                    carList.saveToFile("cars.txt");
                    break;
                case 12:
                    System.out.println("Thank you for using!");
                    break;
            }
        } while (option != 12);

    }

}
