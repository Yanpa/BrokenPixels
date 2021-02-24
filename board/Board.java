package board;

import phones.*;
import pixels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Board extends JFrame implements MouseListener {
    final int sideSizeOfTheArray = 64;
    public Pixel[][] display;
    private Pixel firstPositionClicked, secondPositionClicked, thirdPositionClicked;
    private int[][] brokenParts = new int[sideSizeOfTheArray][sideSizeOfTheArray];
    private int numberOfClicks = 0, clickCounter = 0, numberOfBrokenPixels = 0, numberOfIterations = 5;
    private final int BROKEN_PIXEL = 0, TO_BE_BROKEN_PIXEL = 1;
    PhoneSerialNumber phoneSerialNumber = new PhoneSerialNumber();
    String serialNumber;
    Stack<WorkingPhones> workingPhonesStack = new Stack<WorkingPhones>(5);
    Stack<BrokenPhones> brokenPhonesStack = new Stack<BrokenPhones>(5);

    Random rand = new Random();

    /**
     * Creates a Constructor that sets the size of the game board and the serial number
     */
    public Board(){
        this.display = new Pixel[sideSizeOfTheArray][sideSizeOfTheArray];
        placingThePixelsOnDisplay();
        serialNumber = phoneSerialNumber.theSerialNumber();

        this.setSize(680, 750);
        this.setVisible(true);
        this.setName("BrokenPixels");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);

        if(numberOfIterations == 0){
            System.out.println("Broken phones list: ");
            for(int i = 0; i < brokenPhonesStack.stackLength(); i++){
                System.out.println(brokenPhonesStack.get(i).getSerialNumber());
            }
            System.out.println("Working phones list: ");
            for(int i = 0; i < workingPhonesStack.stackLength(); i++){
                System.out.println(workingPhonesStack.get(i).getSerialNumber());
            }
        }
    }

    /**
     * Paints the board
     * @param g
     */
    @Override
    public void paint(Graphics g){
        for(int row = 0; row < sideSizeOfTheArray; row++){
            for(int col = 0; col < sideSizeOfTheArray; col++) {
                renderPixel(g, row, col);
            }
        }
        g.setFont(g.getFont().deriveFont(15f));
        g.drawString(serialNumber, 310, 50);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoordinate = (e.getX() - 20) / 10;
        int yCoordinate = (e.getY() - 80) / 10;

        if((xCoordinate >= 0 && xCoordinate <=63) && (yCoordinate >= 0 && yCoordinate <=63)){
            clickCounter++;
            numberOfClicks++;
        }
        System.out.println("You clicked \nX: " + xCoordinate + "\n" + "Y: " + yCoordinate);
        System.out.println(numberOfClicks);

        if(numberOfClicks == 1)
            firstPositionClicked = display[xCoordinate][yCoordinate];
        if(numberOfClicks == 2)
            secondPositionClicked = display[xCoordinate][yCoordinate];
        if(numberOfClicks == 3)
            thirdPositionClicked = display[xCoordinate][yCoordinate];

        blackPixelsAndHowToFindThem(xCoordinate, yCoordinate, firstPositionClicked, secondPositionClicked, thirdPositionClicked);
        if(isClickedThreeTimes(firstPositionClicked, secondPositionClicked, thirdPositionClicked) && brokenParts[xCoordinate][yCoordinate] != TO_BE_BROKEN_PIXEL) numberOfClicks = 0;
        if(theBoardIsUncovered(clickCounter) && numberOfIterations > 0){
            numberOfIterations--;
            theNextIteration(numberOfBrokenPixels);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Placing the pixels randomly
     */
    private void placingThePixelsOnDisplay(){
        for(int row = 0; row < sideSizeOfTheArray; row++){
            for(int col = 0; col < sideSizeOfTheArray; col++){
                int randomNumber = rand.nextInt(3);
                brokenParts[row][col] = rand.nextInt(3);
                switch (randomNumber){
                    case 0:
                        display[row][col] = new RedPixel(row, col);
                        break;
                    case 1:
                        display[row][col] = new GreenPixel(row, col);
                        break;
                    case 2:
                        display[row][col] = new BluePixel(row, col);
                        break;
                }
            }
        }
    }

    private Pixel getDisplayPixel(int row, int col){
        return this.display[row][col];
    }

    private boolean hasDisplayPixel(int row, int col){
        return getDisplayPixel(row, col) != null;
    }

    private void renderPixel(Graphics g, int row, int col){
        if(hasDisplayPixel(row, col)){
            Pixel p = this.getDisplayPixel(row, col);
            p.renderPixel(g);
        }
    }

    /**
     * Checks if pixel is pressed three times without stopping
     * @param pixel1
     * @param pixel2
     * @param pixel3
     * @return
     */
    private boolean isClickedThreeTimes(Pixel pixel1, Pixel pixel2, Pixel pixel3){
        return ((pixel1 == pixel2) && (pixel1 == pixel3));
    }

    /**
     * Checks if half of the display is covered in broken pixels
     * @param count
     * @return
     */
    private boolean moreThanHalfOfTheDisplayIsBroken(int count){
        return count >= (sideSizeOfTheArray*sideSizeOfTheArray) / 2;
    }

    /**
     * Checks if the uncovered part of the screen is covered in black pixels
     * @param count
     * @return
     */
    private boolean theBoardIsUncovered(int count){
        return count >= (float)(sideSizeOfTheArray*sideSizeOfTheArray) * 2.3f;
    }

    /**
     * Method that checks if it's time for the next iterations and if the current one has a broken phone or not
     * @param numberOfBrokenPixels
     */
    private void theNextIteration(int numberOfBrokenPixels){
        if(moreThanHalfOfTheDisplayIsBroken(numberOfBrokenPixels)){
            brokenPhonesStack.push(new BrokenPhones(serialNumber));
            Modal modal = new Modal(this,"One more for the trash", "You found another broken phone");
            dispose();
            numberOfIterations--;
            new Board();
        }

        if(!moreThanHalfOfTheDisplayIsBroken(numberOfBrokenPixels)){
            workingPhonesStack.push(new WorkingPhones(serialNumber));
            Modal modal = new Modal(this,"Diamond in the rough", "Against all odds you found a working phone");
            dispose();
            numberOfIterations--;
            new Board();
        }
    }

    /**
     * Places the black pixels on the board if the conditions are met
     * @param xCoordinate
     * @param yCoordinate
     * @param firstPositionClicked
     * @param secondPositionClicked
     * @param thirdPositionClicked
     */
    private void blackPixelsAndHowToFindThem(int xCoordinate, int yCoordinate, Pixel firstPositionClicked, Pixel secondPositionClicked, Pixel thirdPositionClicked){
        if((brokenParts[xCoordinate][yCoordinate] == BROKEN_PIXEL) ||
                (isClickedThreeTimes(firstPositionClicked, secondPositionClicked, thirdPositionClicked) &&
                        brokenParts[xCoordinate][yCoordinate] == TO_BE_BROKEN_PIXEL)){
            display[xCoordinate][yCoordinate] = new BlackPixel(xCoordinate, yCoordinate);
            numberOfBrokenPixels++;
            repaint();
            numberOfClicks = 0;
        }
    }
}
