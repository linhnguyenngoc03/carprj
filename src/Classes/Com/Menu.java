//  @author Anh Linh
package Classes.Com;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    Validation val = new Validation();
    Inputter input = new Inputter();

    public int int_getChoice(ArrayList<String> options) {
        int response = -1;
        for (int i = 0; i < options.size(); i++) {
            System.out.println(String.valueOf(i + 1) + ". " + options.get(i));
        }
        do {
            System.out.println("Please choose an option from 1 to " + 
                    String.valueOf(options.size()));
            response = input.inputInteger("your option");
        } while (!val.checkRangeNumber(response, 1, options.size()));
        return response;
    }

    public Brand ref_getChoice(ArrayList<Brand> options) {
        int response = -1;
        for (int i = 0; i < options.size(); i++) {
            System.out.println("<" + String.valueOf(i + 1) + "> " 
                    + options.get(i));
        }
        do {
            System.out.println("Please choose an option from 1 to " 
                    + String.valueOf(options.size()));
            response = input.inputInteger("option");
        } while (!val.checkRangeNumber(response, 1, options.size()));
        return options.get(response - 1);
    }
}
