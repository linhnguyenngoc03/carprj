//  @author Anh Linh
package Classes.Com;

public class Car {

    protected String carID;
    protected Brand brand;
    protected String color;
    protected String frameID;
    protected String engineID;

    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    public int comparedTo(Car c) {
        int d = this.getBrand().getBrandName().compareTo(c.getBrand().getBrandName());
        if (d != 0) {
            return d;
        }
        return this.getCarID().compareTo(c.getCarID());
    }

    @Override
    public String toString() {
        return getCarID() + ", " + getBrand().getBrandID() + ", "
                + getColor() + ", " + getFrameID() + ", " + getEngineID();
    }

    public String screenString() {
        return getBrand() + ",\n"
                + getCarID() + ", " + getColor() + ", "
                + getFrameID() + ", " + getEngineID();
    }

}
