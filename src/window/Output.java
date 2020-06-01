package window;

import app.Config;
import layout.App;
import layout.Window;
import model.Area;
import model.Factor;
import model.FactorPart;
import model.FirstTwoScenarios;
import raport.Raport;
import raport.Table;

import javax.swing.*;
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

        JLabel text = new JLabel("KONIEC", SwingConstants.CENTER);
        text.setFont(Config.font);
        text.setForeground(Config.color1);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(text);

        JButton b = new JButton("Generuj i otwórz raport");
        b.addActionListener(e -> generatePdf());
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(b);
    }

    private void generatePdf() {
        Raport r = new Raport();
        r.html.add("<br><br>");

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
        r.html.add("<br><br>");


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
        r.save();
        r.html.add("<br><br>");


        //TODO
        // Scenariusz pesymistyczny
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
        r.save();
        r.html.add("<br><br>");

        //TODO
        // Scenariusz pesymistyczny
        r.html.add("<h2>Tabela 4. Scenariusz najbardziej prawdopodobny</h2>");
        Table t4 = new Table();
        t4.addHeader(new ArrayList<>(Arrays.asList("Elementy scenariusza", "Prawdopodobieństwo", "Siła wpływu 'ujemna'", "Siła wpływu 'dodatnia'")));
        for (Area a : app.dataManager.areas) {
            t4.addHeader(a.name, 4);
            for (Factor f : a.factors) {
                t4.addRow(new ArrayList<>(Arrays.asList(f.getName(), "", "", "")));
            }
            t4.addHeader(new ArrayList<>(Arrays.asList("Średnia siła wpływu", "", "", "")));
        }
        r.html.add(t4.getContent());
        r.save();

        //TODO
        // Scenariusz pesymistyczny
        r.html.add("<h2>Tabela 5. Scenariusz niespodziankowy</h2>");
        Table t5 = new Table();
        t5.addHeader(new ArrayList<>(Arrays.asList("Elementy scenariusza", "Prawdopodobieństwo", "Siła wpływu 'ujemna'", "Siła wpływu 'dodatnia'")));
        for (Area a : app.dataManager.areas) {
            t5.addHeader(a.name, 4);
            for (Factor f : a.factors) {
                t5.addRow(new ArrayList<>(Arrays.asList(f.getName(), "", "", "")));
            }
            t5.addHeader(new ArrayList<>(Arrays.asList("Średnia siła wpływu", "", "", "")));
        }
        r.html.add(t5.getContent());
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
