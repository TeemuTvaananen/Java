import javax.swing.JOptionPane;
public class Main {
    public static void main(String[] args) {

        String name = JOptionPane.showInputDialog("Hello there. What's your name?");
        JOptionPane.showMessageDialog(null, "How's it hanging " + name + "?");
        int shoe_size = Integer.parseInt(JOptionPane.showInputDialog("Please state your shoe size"));
        if (shoe_size < 40) {
            JOptionPane.showMessageDialog(null, "You've got small pair of feet, remember size: " + shoe_size);
        } else if(shoe_size > 40 && shoe_size < 45) {
            JOptionPane.showMessageDialog(null, " You've got normal pair of feet, remember size: " + shoe_size);
        } else{
            JOptionPane.showMessageDialog(null, "You've got big pair of feet, remember size: " + shoe_size);
        }
    }
}