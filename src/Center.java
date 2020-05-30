import javax.swing.*;
import java.awt.*;


public class Center extends JPanel{
    FirstWindow first = new FirstWindow();
    choosingSpheres choosingSpheres = new choosingSpheres();
    int counter = 0;

    public Center(){
        setPreferredSize(new Dimension(400, 100));
        setBackground(Color.lightGray);
        add(first);
        add(choosingSpheres);
    }

    public void change(){
        System.out.println("aa");
        if(counter == 0) {
            first.setVisible(false);
            choosingSpheres.setVisible(true);
        }
        if(counter == 1){

        }
        // tak ze wszyskimi
        counter++;
    }

}
