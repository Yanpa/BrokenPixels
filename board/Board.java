package board;

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
    private int brokenPixel = 0, toBeBrokenPixel = 1, workingPixel = 2, numberOfClicks = 0;

    Random rand = new Random();

    public Board(){
        this.display = new Pixel[sideSizeOfTheArray][sideSizeOfTheArray];
        placingThePixelsOnDisplay();
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
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoordinate = (e.getX() - 20) / 10;
        int yCoordinate = (e.getY() - 80) / 10;

        numberOfClicks++;
        System.out.println("You clicked \nX: " + xCoordinate + "\n" + "Y: " + yCoordinate);
        System.out.println(numberOfClicks);


        if(brokenParts[xCoordinate][yCoordinate] == brokenPixel){
            display[xCoordinate][yCoordinate] = new BlackPixel(xCoordinate, yCoordinate);
            repaint();
            numberOfClicks = 0;
        }

        if(numberOfClicks == 1)
            firstPositionClicked = display[xCoordinate][yCoordinate];
        if(numberOfClicks == 2)
            secondPositionClicked = display[xCoordinate][yCoordinate];
        if(numberOfClicks == 3)
            thirdPositionClicked = display[xCoordinate][yCoordinate];

        if(isClickedThreeTimes(firstPositionClicked, secondPositionClicked, thirdPositionClicked) && brokenParts[xCoordinate][yCoordinate] == toBeBrokenPixel){
            display[xCoordinate][yCoordinate] = new BlackPixel(xCoordinate, yCoordinate);
            repaint();
            numberOfClicks = 0;
        }
        if(isClickedThreeTimes(firstPositionClicked, secondPositionClicked, thirdPositionClicked) && brokenParts[xCoordinate][yCoordinate] != toBeBrokenPixel) numberOfClicks = 0;
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
        return ((pixel1 == pixel2) && (pixel1 == pixel3) && (pixel2 == pixel3));
    }
}
