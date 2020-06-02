package app;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Config {
    public static final String AppName = "Analiza makrootoczenia";

    public static final boolean DEBUG = false;

//    public static final String AppDescription = "<html>" +
//            "ładny opis programu" +
//            "<br/>" +
//            "<div style='background-color:red'>a to jest nowa linia</div>" +
//            "<img width='400' src='https://www.zooplus.pl/magazyn/wp-content/uploads/2019/12/kot-przyb%C5%82%C4%99da-768x512.jpeg'></img>" +
//            "</html>";


    public static final String AppDescription = "<html>" +
            "ładny opis programu" +
            "<br/>" +
            "<div style='background-color:red'>a to jest nowa linia</div>" +
            "</html>";

    public static final Dimension initialState = new Dimension(900, 800);

    public static final int frameMarginLeftRight = 25;

    public static final String[] influenceValidList = {"-5", "-4", "-3", "-2", "-1", "0", "+1", "+2", "+3", "+4", "+5"};

    public static final String[] factorPartsName = {"Wzrost", "Stabilizacja", "Regres"};

    public static final int defaultFactorsCount = 4;

    public static final String[] initialAreas = {
            "Polityczna",
            "Ekonomiczna",
            "Społeczna",
            "Demograficzna",
            "Technologiczna",
            "Regulacyjna"
    };

    public static final Font font = new Font("Consolas", Font.BOLD, 15);
    public static final Color color1 = new Color(119,51,68);
    public static final Color color2 = new Color(227,181,164);
    public static final Color color3 = new Color(245,233,226);
    public static final Color color4 = new Color(11,0,20);
    public static final Color color5 = new Color(212,77,92);

    public static final Color plotColor1 = new Color(50, 168, 82, 50);
    public static final Color plotColor2 = new Color(119,51,68, 50);

    public static final Border border = BorderFactory.createLineBorder(color1, 3);
}
