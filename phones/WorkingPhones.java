package phones;

public class WorkingPhones {
    String serialNumber;

    /**
     * Constructor that gets String and saves the information in the class
     * @param serialNumber
     */
    public WorkingPhones(String serialNumber){
        this.serialNumber = serialNumber;
    }

    /**
     *
     * @return serial number saved in the constructor
     */
    public String getSerialNumber(){
        return serialNumber;
    }
}
