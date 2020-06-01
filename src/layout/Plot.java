package layout;

import javax.swing.*;
import java.awt.*;

public class Plot extends JPanel {

    //TODO zliczanie ilości sfer
    int spheresCounter = 3;

    Plot(){
        setPreferredSize(new Dimension(700, 500));
//        this.setBackground(Color.red);
        setBorder(BorderFactory.createLineBorder(Color.RED));
        setLayout(null);
        setVisible(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        //basis
        g.drawLine(20,getHeight() - 20,getWidth() - 20,getHeight() - 20);
        g.drawLine(20,getHeight() - 21,getWidth() - 20, getHeight() - 21);
        drawArrowLine(g, 20 + 3, getHeight() - 21, 20 - 3, getHeight() - 21,
                (3*getWidth()/100)/2, (3*getHeight()/100)/2);
        drawArrowLine(g, getWidth() - 20 - 3, getHeight() - 21, getWidth() - 20 + 3, getHeight() - 21,
                (3*getWidth()/100)/2, (3*getHeight()/100)/2);
        //loop for rest
        int counter = 1;
        while(counter < spheresCounter+1) {
            int var = (getHeight() - 20)/(spheresCounter+1);
            g.setColor(Color.black);
            g.drawLine(40, counter * var, getWidth() - 40,  counter * var);
            g2d.fillOval(getWidth()/2 - 3, counter * var - 3, 6, 6);
            drawArrowLine(g, 40 + 3, counter * var , 40 - 3, counter * var,
                    getWidth()/100, getHeight()/100);
            drawArrowLine(g, getWidth() - 40 - 3, counter * var , getWidth() - 40 + 3, counter * var,
                    getWidth()/100, getHeight()/100);
            counter++;
        }
        //vertical
        drawArrowLine(g, getWidth()/2, 20+3, getWidth()/2, 20-3, getWidth()/100, getHeight()/100);
        g.drawLine(getWidth()/2, getHeight()-20, getWidth()/2, 20);
        //numbers
        int spaceForNumbers = (getWidth()/2)/6;
        System.out.println(spaceForNumbers);
            //positive
        for(int i = 0; i < 6; i++){
            g.drawLine(getWidth()/2+i*spaceForNumbers, getHeight() - 20 -3,
                    getWidth()/2+i*spaceForNumbers, getHeight() - 20+2);
            String number = String.valueOf(i);
            g.drawString(number, getWidth()/2+i*spaceForNumbers-3, getHeight() - 20+15);
        }
            //negative
        for(int i = 0; i < 6; i++){
            g.drawLine(getWidth()/2-i*spaceForNumbers, getHeight() - 20 -3,
                    getWidth()/2-i*spaceForNumbers, getHeight() - 20+2);
            if(i != 0){
                String number = "-" + i;
                g.drawString(number, getWidth()/2-i*spaceForNumbers-6, getHeight() - 20+15);
            }
        }

    }

    //nie pytajcie jak to działa
    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }

    public static void main(String[] args) {
        JFrame aaa = new JFrame();
        Plot plot = new Plot();
        plot.setBounds(50,50, 700, 500);
        aaa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aaa.setSize(new Dimension(900,700));
        aaa.setLayout(null);
        aaa.add(plot);
        aaa.setVisible(true);

    }
}


