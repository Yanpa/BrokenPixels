package board;

import phones.BrokenPhones;
import phones.PhoneSerialNumber;
import phones.Stack;
import phones.WorkingPhones;
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
    private int brokenPixel = 0, toBeBrokenPixel = 1, workingPixel = 2, numberOfClicks = 0, numberOfBrokenPixels = 0;
    PhoneSerialNumber phoneSerialNumber = new PhoneSerialNumber();
    String serialNumber;
    Stack<WorkingPhones> workingPhonesStack = new Stack<WorkingPhones>(5);
    Stack<BrokenPhones> brokenPhonesStack = new Stack<BrokenPhones>(5);

    Random rand = new Random();

    public Board(){
        this.display = new Pixel[sideSizeOfTheArray][sideSizeOfTheArray];
        placingThePixelsOnDisplay();
        serialNumber = phoneSerialNumber.theSerialNumber();

        this.setSize(680, 750);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);
    }

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

        if((xCoordinate >= 0 && xCoordinate <=63) && (yCoordinate >= 0 && yCoordinate <=63)) numberOfClicks++;
        System.out.println("You clicked \nX: " + xCoordinate + "\n" + "Y: " + yCoordinate);
        System.out.println(numberOfClicks);


        if((brokenParts[xCoordinate][yCoordinate] == brokenPixel) ||
                (isClickedThreeTimes(firstPositionClicked, secondPositionClicked, thirdPositionClicked) &&
                        brokenParts[xCoordinate][yCoordinate] == toBeBrokenPixel)){
                            display[xCoordinate][yCoordinate] = new BlackPixel(xCoordinate, yCoordinate);
                            numberOfBrokenPixels++;
                            repaint();
                            numberOfClicks = 0;
        }

        if(numberOfClicks == 1)
            firstPositionClicked = display[xCoordinate][yCoordinate];
        if(numberOfClicks == 2)
            secondPositionClicked = display[xCoordinate][yCoordinate];
        if(numberOfClicks == 3)
            thirdPositionClicked = display[xCoordinate][yCoordinate];

        if(isClickedThreeTimes(firstPositionClicked, secondPositionClicked, thirdPositionClicked) && brokenParts[xCoordinate][yCoordinate] != toBeBrokenPixel) numberOfClicks = 0;
        if(!isClickedThreeTimes(firstPositionClicked, secondPositionClicked, thirdPositionClicked)) numberOfClicks = 0;
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

    private boolean isClickedThreeTimes(Pixel pixel1, Pixel pixel2, Pixel pixel3){
        return ((pixel1 == pixel2) && (pixel1 == pixel3));
    }

    private boolean moreThanHalfOfTheDisplayIsBroken(int count){
        return count >= (sideSizeOfTheArray*sideSizeOfTheArray) / 2;
    }

    private void endGame(int numberOfIterations, int numberOfBrokenPixels){
        while (numberOfIterations > 0){
            if(moreThanHalfOfTheDisplayIsBroken(numberOfBrokenPixels)){
                brokenPhonesStack.push(new BrokenPhones(serialNumber));
                dispose();
                numberOfIterations--;
                new Board();
            }

            if(!moreThanHalfOfTheDisplayIsBroken(numberOfBrokenPixels)){
                workingPhonesStack.push(new WorkingPhones(serialNumber));
                dispose();
                numberOfIterations--;
                new Board();
            }
        }
    }
}
