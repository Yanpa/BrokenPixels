package phones;

public class BrokenPhones {
    String serialNumber;

    /**
     * Constructor that gets String and saves the information in the class
     * @param serialNumber
     */
    public BrokenPhones(String serialNumber){
        this.serialNumber = serialNumber;
    }

    /**
     *
     * @return serial number saved in the constructor
     */
    public String getSerialNumber() {
        return serialNumber;
    }
}
