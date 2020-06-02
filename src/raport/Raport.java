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
    public static final String plotPath = "wykres.png";

    private final String style = "body{margin:0 20% 0 20%}table.pz-table{font-family:arial,sans-serif;border-collapse:collapse;width:100%}.pz-table td,.pz-table th{border:1px solid #000;text-align:left;padding:8px}.pz-table td.span,.pz-table th{background-color:#ddd}table.legend{border:1px solid #000}.legend td{min-width:50px}hr.dashed{border:none;border-top:2px dotted #000;color:#fff;background-color:#fff;height:1px;width:50%}hr.big{border:none;border-top:2px solid #000;color:#fff;background-color:#fff;height:1px;width:50%}";
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

        body.append(getLegend());
        body.append("<h2>Wykres:</h2>");
        body.append(getPlotImage());
        body.append("<br><br>");
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

    private String getLegend() {
        return "<br><br><table class=\"legend\"> <tr> <td colspan=\"2\"> <h3>Legenda</h3> </td></tr><tr> <td> <hr class=\"big\"> </td><td>- scenariusz optymistyczny</td></tr><tr> <td> <hr class=\"dashed\"> </td><td>- scenariusz pesymistyczny</td></tr><tr> <td style=\"background-color: rgba(50, 168, 82, 0.5);\">&nbsp;&nbsp;&nbsp;&nbsp;</td><td>- scenariusz niespodziankowy</td></tr><tr> <td style=\"background-color: rgba(119,51,68, 0.5);\">&nbsp;&nbsp;&nbsp;&nbsp;</td><td>- scenariusz najbardziej prawdopodobny</td></tr></table>";
    }

    public static void savePlotToImage(JPanel panel) {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);
        try {
            ImageIO.write(image, "png", new File(Raport.plotPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        createFile();
        writeToFile();
    }
}
