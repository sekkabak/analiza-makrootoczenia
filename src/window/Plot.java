package window;

import app.Config;
import app.Helper;
import layout.App;
import layout.Window;
import model.FirstTwoScenarios;
import model.SecondTwoScenarios;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Plot extends Window {

    //TODO zliczanie ilości sfer
    public ArrayList<FirstTwoScenarios> PositiveScenario;
    public ArrayList<FirstTwoScenarios> NegativeScenario;
    public ArrayList<SecondTwoScenarios> MostLikelyScenario;
    public ArrayList<SecondTwoScenarios> UnexpectedScenarios;
    public ArrayList<Integer> tableOfPlaces = new ArrayList<>();
    int spheresCounter;
    int space;

    public Plot(App app){
        super(app);
        this.PositiveScenario = app.dataManager.optimisticScenario;
        this.NegativeScenario = app.dataManager.pesimisticScenario;
        this.MostLikelyScenario = app.dataManager.mostLikelyScenario;
        this.UnexpectedScenarios = app.dataManager.unexpectedScenario;
        setPreferredSize(new Dimension(700, 500));
        this.setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.RED));
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void display() {

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        spheresCounter = PositiveScenario.size();
        tableOfPlaces = new ArrayList<>();
        tableOfPlaces.add(getHeight() - 20);
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
            int var = getHeight() - counter * (getHeight())/(spheresCounter+1) - 20;
            int space = (getHeight() - (getHeight())/(spheresCounter+1) - 20) - (getHeight() - 2* (getHeight())/(spheresCounter+1) - 20);
            System.out.println(space);
            tableOfPlaces.add(var);
            g.setColor(Color.black);
            g.drawLine(40, var, getWidth() - 40,  var);
            g2d.fillOval(getWidth()/2 - 3, var - 3, 6, 6);
            drawArrowLine(g, 40 + 3, var , 40 - 3, var,
                    getWidth()/100, getHeight()/100);
            drawArrowLine(g, getWidth() - 40 - 3, var , getWidth() - 40 + 3, var,
                    getWidth()/100, getHeight()/100);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2d.drawString(Helper.toRoman(counter), 70, var + space/2 + 10);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
            counter++;
        }
        //vertical
        drawArrowLine(g, getWidth()/2, 10+3, getWidth()/2, 10-3, getWidth()/100, getHeight()/100);
        g.drawLine(getWidth()/2, getHeight()-20, getWidth()/2, 10);
        //numbers
        int spaceForNumbers = (getWidth()/2)/6;
        space = spaceForNumbers;
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
        drawLinesOptimistic(g);
        drawLinesPesimistic(g);
        drawRectangleMostLikely(g);
        drawRectangleSurprise(g);
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

    public void drawLinesOptimistic(Graphics g){
        int topDotx = 0;
        int bottomDotx = 0;
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke stroke = new BasicStroke(2);
        g2d.setStroke(stroke);
        for(int i = 0; i < spheresCounter; i++){
            topDotx = tableOfPlaces.get(i);
            bottomDotx = tableOfPlaces.get(i+1);
            int x = (int) (getWidth()/2 + space * Double.parseDouble(PositiveScenario.get(i).average));
            g2d.drawLine(x, topDotx, x, bottomDotx);
        }
        g2d.dispose();
    }

    public void drawLinesPesimistic(Graphics g){
        int topDotx = 0;
        int bottomDotx = 0;
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        for(int i = 0; i < spheresCounter; i++){
            topDotx = tableOfPlaces.get(i);
            bottomDotx = tableOfPlaces.get(i+1);
            int x = (int) (getWidth()/2 + space * Double.parseDouble(NegativeScenario.get(i).average));
            g2d.drawLine(x, topDotx, x, bottomDotx);
        }
        g2d.dispose();
    }

    public void drawRectangleMostLikely(Graphics g){
        int topDotx = 0;
        int bottomDotx = 0;
        Color c = Config.plotColor2;
        for(int i = 0; i < spheresCounter; i++){
            Graphics2D g2d = (Graphics2D) g.create();
            topDotx = tableOfPlaces.get(i);
            bottomDotx = tableOfPlaces.get(i+1);
            int x1 = (int) (getWidth()/2 + space * Double.parseDouble(MostLikelyScenario.get(i).negativeInfuenceAverage));
            int x2 = (int) (getWidth()/2 + space * Double.parseDouble(MostLikelyScenario.get(i).positiveInfuenceAverage));
            Rectangle rectangle = new Rectangle(x1, bottomDotx, x2 - x1, topDotx - bottomDotx);
            g2d.draw(rectangle);
            g2d.setColor(c);
            g2d.fill(rectangle);
            g2d.dispose();
        }
    }
    public void drawRectangleSurprise(Graphics g){
        int topDotx = 0;
        int bottomDotx = 0;
        Color c = Config.plotColor1;
        for(int i = 0; i < spheresCounter; i++){
            Graphics2D g2d = (Graphics2D) g.create();
            topDotx = tableOfPlaces.get(i);
            bottomDotx = tableOfPlaces.get(i+1);
            int x1 = (int) (getWidth()/2 + space * Double.parseDouble(UnexpectedScenarios.get(i).negativeInfuenceAverage));
            int x2 = (int) (getWidth()/2 + space * Double.parseDouble(UnexpectedScenarios.get(i).positiveInfuenceAverage));
            Rectangle rectangle = new Rectangle(x1, bottomDotx, x2 - x1, topDotx - bottomDotx);
            g2d.draw(rectangle);
            g2d.setColor(c);
            g2d.fill(rectangle);
            g2d.dispose();
        }
    }

}


