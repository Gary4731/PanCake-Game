

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.NoSuchElementException;
import java.util.Random;

public class PancakeGUI extends JPanel implements ActionListener, MouseMotionListener, MouseListener {

    private JButton resetButton, resolveButton, addButton, removeButton;
    private DrawPanel drawPanel;
    private PancakeStack pancakes;
    private int number = 1, numPancake= 0;

    public Random rand = new Random();

    //add button to GUI
    public PancakeGUI(){
        super(new BorderLayout());

        this.pancakes = new PancakeStack();
        drawPanel = new DrawPanel();
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        resolveButton = new JButton("Resolve");
        resolveButton.addActionListener(this);
        addButton = new JButton("ADD");
        addButton.addActionListener(this);
        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(resetButton);
        buttonPanel.add(resolveButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);
        add(drawPanel,BorderLayout.CENTER);

        drawPanel.addMouseListener(this);
    }

    private class DrawPanel extends JPanel {
        public DrawPanel() {
            setPreferredSize(new Dimension(400, 800));
            setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Pancake.WORLD_W=getWidth();
            Pancake.WORLD_H=getHeight();

            pancakes.drawPancakes(g);
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Pancake Sort Problem");
        // kill all threads when frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PancakeGUI());
        frame.pack();
        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width-frameDimension.width)/2,
                (screenDimension.height-frameDimension.height)/2);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == addButton && numPancake < 20)
        {
            System.out.println("Add a Pancake");
            int randSize = rand.nextInt(400) + 5;
            Color randColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            Pancake pancake = new Pancake(randSize, randColor,number);
            pancakes.push(pancake);
            number++;
            numPancake++;
            System.out.println(numPancake);
        }else if(source == removeButton && numPancake > 0){
            if(pancakes.peek() == null){
                throw new NoSuchElementException("No Pancake left");
            }else{
                System.out.println("Remove Pancake");
                pancakes.pop();
                numPancake--;
                number--;
                System.out.println(numPancake);
            }}else if(source == resolveButton){
            System.out.println("Resolve");
            pancakes.sortMethod(numPancake);
            int[] resetSize = new int[numPancake];
            Color[] resetColor = new Color[numPancake];
            int index = 0, index_A = 0, index_B = 0;
            int restHeight = 1;
            int popNum = numPancake;
            for(int i = numPancake; i>0 ; i--)
            {
                resetSize[index] = pancakes.sortMethod(numPancake).get(i-1).getSize();
                index++;
            }
            for(int i = numPancake; i>0 ; i--)
            {
                resetColor[index_A] = pancakes.sortMethod(numPancake).get(i-1).getColor();
                index_A++;
            }
            for(;popNum  > 0; popNum--)
            {
                pancakes.pop();
            }
            for(int i = 0; i<numPancake ; i++){
                Pancake pancake = new Pancake(resetSize[i], resetColor[i], restHeight);
                pancakes.push(pancake);
                restHeight++;
            }
        }else if(source == resetButton){
            System.out.println("Reset");
            int resetNum = numPancake, popNum = numPancake;
            int resetHeight = 1;
            for(;popNum  > 0; popNum--)
            {
                pancakes.pop();
            }
            for(int i = 1; i<= resetNum; i++)
            {
                int randSize = rand.nextInt(400) + 5;
                Color randColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                Pancake pancake = new Pancake(randSize, randColor,resetHeight);
                pancakes.push(pancake);
                resetHeight++;
            }
        }
        drawPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse be clicked");

        int y = e.getY();
        System.out.println(y);
        int panCakeLocation = 20-(y / 40);
        pancakes.mouseClick(panCakeLocation);
        int[] resetSize = new int[numPancake];
        Color[] resetColor = new Color[numPancake];
        int index = 0, index_A = 0;
        int restHeight = 1;
        int popNum = numPancake;
        for(int i = numPancake; i>0 ; i--)
        {
            System.out.println("first"+ pancakes.mouseClick(numPancake).get(i-1).getSize());
            resetSize[index] = pancakes.mouseClick(numPancake).get(i-1).getSize();
            System.out.println(resetSize[index]);
            index++;
        }
        for(int i = numPancake; i>0 ; i--)
        {
            System.out.println("first"+ pancakes.mouseClick(numPancake).get(i-1).getColor());
            resetColor[index_A] = pancakes.mouseClick(numPancake).get(i-1).getColor();
            System.out.println(resetColor[index_A]);
            index_A++;
        }
        for(;popNum  > 0; popNum--)
        {
            pancakes.pop();
        }
        for(int i = numPancake; i>0 ; i--){
            Pancake pancake = new Pancake(resetSize[i-1], resetColor[i-1], restHeight);
            pancakes.push(pancake);
            restHeight++;
        }
        drawPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        e.getY();
    }
}
