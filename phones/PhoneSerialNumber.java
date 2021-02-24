package phones;

import java.awt.*;
import java.util.Random;

public class PhoneSerialNumber {
    Random rand = new Random();
    private String theSerialNumber;

    public PhoneSerialNumber(){

    }

    /**
     * Method that returns random char[] that will be used as name for every created phone
     * @return char[] name
     */
    private char[] serialNumberCreator(){
        String creator = "qwer_tyuio123456pasdfghj90-klzxcvbnm78";
        char[] creation = new char[10];
        for(int i = 0; i < 10; i++){
            int randomNumber = rand.nextInt(creator.length());
            creation[i] = creator.charAt(randomNumber);
        }
        return creation;
    }

    /**
     *
     * @return created char[] convertet to String so it'd be used properly in the program
     */
    public String theSerialNumber(){
         return theSerialNumber = new String(serialNumberCreator());
    }

}
