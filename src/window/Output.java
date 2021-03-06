package window;

import app.Config;
import layout.App;
import layout.BindedTextField;
import layout.Window;
import model.*;
import raport.Raport;
import raport.Table;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Output extends Window {
    public Output(App app) {
        super(app);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Wnioski:", SwingConstants.CENTER);
        label.setFont(Config.font);
        label.setForeground(Config.color1);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        JTextArea text = new JTextArea();
        text.getDocument().addDocumentListener(new BindedTextField(app.dataManager, "conclusions"));
        add(text);

        JButton b = new JButton("Generuj i otwórz raport");
        b.addActionListener(e -> generatePdf());
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(b);
    }

    private void generatePdf() {
        Raport r = new Raport();
        r.html.add("<br><br>");

        r.html.add("<p><label>Wykonał(a): " + app.dataManager.creator + "</label></p>");
        r.html.add("<p><label>Data: " + app.dataManager.date + "</label></p>");

        // Analiza tendencji w makrootoczeniu
        r.html.add("<h2>Tabela 1. Analiza tendencji w makrootoczeniu</h2>");
        Table t1 = new Table();
        t1.addHeader(new ArrayList<>(
                Arrays.asList("Czynniki w otoczeniu", "Trend", "Siła wpływu<br>od -5 do +5", "Prawdopodobieństwo")));
        for (Area a : app.dataManager.areas) {
            t1.addHeader(a.name, 4);
            for (Factor f : a.factors) {
                String influence = f.rows.stream()
                        .map(FactorPart::getInfluence)
                        .collect(Collectors.joining("<br>"));

                String probability = f.rows.stream()
                        .map(FactorPart::getProbability)
                        .collect(Collectors.joining("<br>"));

                t1.addRow(new ArrayList<>(Arrays.asList(f.getName(), "wzrost<br>stabilizacja<br>regres", influence, probability)));
            }
        }
        r.html.add(t1.getContent());


        // Scenariusz optymstyczny
        r.html.add("<h2>Tabela 2. Scenariusz optymistyczny</h2>");
        Table t2 = new Table();
        t2.addHeader(new ArrayList<>(Arrays.asList("Elementy scenariusza", "Siła wpływu")));

        int sizeIO = app.dataManager.areas.size();
        for (int i = 0; i < sizeIO; i++) {
            Area a = app.dataManager.areas.get(i);
            FirstTwoScenarios scenario = app.dataManager.optimisticScenario.get(i);

            t2.addHeader(a.name, 2);

            int size = a.factors.size();
            for (int j = 0; j < size; j++) {
                Factor f = a.factors.get(j);
                t2.addRow(new ArrayList<>(Arrays.asList(f.getName(), scenario.infuences.get(j))));
            }

            t2.addHeader(new ArrayList<>(Arrays.asList("Średnia siła wpływu", scenario.average)));
        }
        r.html.add(t2.getContent());

        r.html.add("<h2>Tabela 3. Scenariusz pesymistyczny</h2>");
        Table t3 = new Table();
        t3.addHeader(new ArrayList<>(Arrays.asList("Elementy scenariusza", "Siła wpływu")));
        int sizeIP = app.dataManager.areas.size();
        for (int i = 0; i < sizeIP; i++) {
            Area a = app.dataManager.areas.get(i);
            FirstTwoScenarios scenario = app.dataManager.pesimisticScenario.get(i);

            t3.addHeader(a.name, 2);

            int size = a.factors.size();
            for (int j = 0; j < size; j++) {
                Factor f = a.factors.get(j);
                t3.addRow(new ArrayList<>(Arrays.asList(f.getName(), scenario.infuences.get(j))));
            }

            t3.addHeader(new ArrayList<>(Arrays.asList("Średnia siła wpływu", scenario.calculateAvarage())));
        }
        r.html.add(t3.getContent());

        r.html.add("<h2>Tabela 4. Scenariusz najbardziej prawdopodobny</h2>");
        Table t4 = new Table();
        t4.addHeader(new ArrayList<>(Arrays.asList("Elementy scenariusza", "Prawdopodobieństwo", "Siła wpływu 'ujemna'", "Siła wpływu 'dodatnia'")));
        int sizeIN = app.dataManager.areas.size();
        for (int i = 0; i < sizeIN; i++) {
            Area a = app.dataManager.areas.get(i);
            SecondTwoScenarios scenario = app.dataManager.mostLikelyScenario.get(i);

            t4.addHeader(a.name, 4);

            int size = a.factors.size();
            for (int j = 0; j < size; j++) {
                Factor f = a.factors.get(j);
                t4.addRow(new ArrayList<>(Arrays.asList(f.getName(), scenario.probability.get(j), scenario.negativeInfuences.get(j), scenario.positiveInfuences.get(j))));
            }

            t4.addHeader(new ArrayList<>(Arrays.asList("Średnia siła wpływu", "", scenario.negativeInfuenceAverage, scenario.positiveInfuenceAverage)));
        }
        r.html.add(t4.getContent());

        r.html.add("<h2>Tabela 5. Scenariusz niespodziankowy</h2>");
        Table t5 = new Table();
        t5.addHeader(new ArrayList<>(Arrays.asList("Elementy scenariusza", "Prawdopodobieństwo", "Siła wpływu 'ujemna'", "Siła wpływu 'dodatnia'")));
        int sizeIS = app.dataManager.areas.size();
        for (int i = 0; i < sizeIS; i++) {
            Area a = app.dataManager.areas.get(i);
            SecondTwoScenarios scenario = app.dataManager.unexpectedScenario.get(i);

            t5.addHeader(a.name, 4);

            int size = a.factors.size();
            for (int j = 0; j < size; j++) {
                Factor f = a.factors.get(j);
                t5.addRow(new ArrayList<>(Arrays.asList(f.getName(), scenario.probability.get(j), scenario.negativeInfuences.get(j), scenario.positiveInfuences.get(j))));
            }

            t5.addHeader(new ArrayList<>(Arrays.asList("Średnia siła wpływu", "", scenario.negativeInfuenceAverage, scenario.positiveInfuenceAverage)));
        }
        r.html.add(t5.getContent());


        r.html.add(r.getLegend(app.dataManager.areas));
        r.html.add("<h2>Wykres:</h2>");
        r.html.add(r.getPlotImage());
        r.html.add("<br><br>");

        r.html.add("<h2>Wnioski:</h2>");
        r.html.add("<div>" + app.dataManager.conclusions + "</div>");
        r.html.add("<br><br>");

        r.save();


        //first check if Desktop is supported by Platform or not
        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop is not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        File file = new File(r.filePath);
        if (file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void display() {

    }
}
