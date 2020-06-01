package raport;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Raport {
    public final String filePath = "raport.html";
    private final String plotPath = "wykres.png";

    private final String style = "body{margin:0 20% 0 20%}table{font-family:arial,sans-serif;border-collapse:collapse;width:100%}td,th{border:1px solid #000;text-align:left;padding:8px}td.span,th{background-color:#ddd}";
    public ArrayList<String> html = new ArrayList<>();

    private String getPlotImage() {
        return "<img src='./" + plotPath + "' alt='wykres'></img>";
    }

    private String getBody() {
        StringBuilder body = new StringBuilder();
        body.append("<body>");

        for (String s : html) {
            body.append(s);
        }

        body.append(getPlotImage());
        body.append("</body>");
        return body.toString();
    }

    private String getHtml() {
        return "<html>" + "<head><style>" + style + "</style></head>" + getBody() + "</html>";
    }

    private void createFile() {
        try {
            File myObj = new File(this.filePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            myWriter.write(getHtml());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void loadImage(JPanel panel) {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);
        try {
            ImageIO.write(image, "png", new File(plotPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        createFile();
        writeToFile();
    }
}
