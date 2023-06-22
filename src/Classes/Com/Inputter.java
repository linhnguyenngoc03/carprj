//  @author Anh Linh
package Classes.Com;

import java.util.Scanner;

public class Inputter {

    Scanner sc = new Scanner(System.in);
    Validation val = new Validation();

    public String inputString(String name) {
        String str = null;
        do {
            System.out.print("Enter " + name + " : ");
            str = sc.nextLine();
        } while (val.isEmptyString(str,name));
        return str;
    }

    public double inputPositiveDouble(String name) {
        double number = -1;
        do {
            try {
                System.out.print("Enter " + name + " : ");
                number = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter again...");
            }
        } while (!val.isPositiveNumber(number));
        return number;
    }

    public int inputInteger(String name) {
        int number = -1;
        int check = 0;
        do {
            try {
                System.out.print("Enter " + name + " : ");
                number = Integer.parseInt(sc.nextLine());
                check = 1;
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter again...");
            }
        } while (check == 0);
        return number;
    }

}
