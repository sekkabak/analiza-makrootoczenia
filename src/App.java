import javax.swing.*;
import java.awt.*;

public class App {
    JFrame frame;
    Top top;
    Bottom bottom;
    Left left;
    Right right;
    Center center;

    public App() {init();}

    private void init(){
        frame = new JFrame("App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        BorderLayout borderLayout = new BorderLayout();
        top = new Top();
        bottom = new Bottom();
        left = new Left();
        right = new Right();
        center = new Center();

        borderLayout.preferredLayoutSize(frame);
        frame.setLayout(borderLayout);

        frame.add(top);
        frame.add(bottom);
        frame.add(right);
        frame.add(left);
        frame.add(center);

        borderLayout.addLayoutComponent(top,BorderLayout.NORTH);
        borderLayout.addLayoutComponent(bottom,BorderLayout.SOUTH);
        borderLayout.addLayoutComponent(right,BorderLayout.EAST);
        borderLayout.addLayoutComponent(left,BorderLayout.WEST);
        borderLayout.addLayoutComponent(center,BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
