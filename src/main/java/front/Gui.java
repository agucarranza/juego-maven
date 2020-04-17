package front;

import javax.swing.*;

public class Gui {
    public static void main() {
        JFrame frame = new JFrame("Mi primera GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        JButton button1 = new JButton("Presionar");
        frame.getContentPane().add(button1);
        frame.setVisible(true);
    }
}
