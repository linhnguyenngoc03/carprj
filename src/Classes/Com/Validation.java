//  @author Anh Linh
package Classes.Com;

import java.util.regex.Pattern;

public class Validation {

    public boolean isEmptyString(String inputString, String name) {
        if (inputString.isEmpty() || inputString.trim().isEmpty()) {
            System.err.println("Invalid input: " + name + " cannot be blank. "
                    + "Please enter again...");
            return true;
        } else {
            return false;
        }
    }

    public boolean isPositiveNumber(double inputNumber) {
        if (inputNumber > 0) {
            return true;
        } else {
            System.err.println("The input must be a positive real number.");
            return false;
        }
    }

    public boolean checkFrameIdFormat(String input) {
        Pattern p = Pattern.compile("^[F]+[0-9]{5}+$");
        if (!p.matcher(input).find()) {
            System.err.println("Please enter the correct format F0000");
            return false;
        }
        return true;
    }

    public boolean checkEngineIdFormat(String input) {
        Pattern p = Pattern.compile("^[E]+[0-9]{5}+$");
        if (!p.matcher(input).find()) {
            System.err.println("Please enter the correct format E0000");
            return false;
        }
        return true;
    }

    public boolean checkRangeNumber(double input, int minRange, int maxRange) {
        if (input >= minRange && input <= maxRange) {
            return true;
        } else {
            System.err.println("The input must be from " + 
                    String.valueOf(minRange) + " to " + String.valueOf(maxRange)
                    + ". Please enter again...");
            return false;
        }
    }

    public boolean checkDuplicated(String str, String compareStr, String name) {
        if (str.compareToIgnoreCase(compareStr) == 0) {
            System.err.println("The " + name + " cannot be duplicated");
            return true;
        }
        return false;
    }

}
