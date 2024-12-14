package Desktop;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class proje2 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Şifre Oluşturucu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));

        JTextField passwordField = new JTextField();
        passwordField.setEditable(false);
        panel.add(passwordField);

        JSlider lengthSlider = new JSlider(8, 20, 12);
        lengthSlider.setMajorTickSpacing(2);
        lengthSlider.setMinorTickSpacing(1);
        lengthSlider.setPaintTicks(true);
        lengthSlider.setPaintLabels(true);
        lengthSlider.setSnapToTicks(true);
        panel.add(new JLabel("Şifre Uzunluğu"));
        panel.add(lengthSlider);

        JCheckBox lowerCaseCheck = new JCheckBox("Küçük Harfler");
        JCheckBox upperCaseCheck = new JCheckBox("Büyük Harfler");
        JCheckBox numbersCheck = new JCheckBox("Rakamlar");
        JCheckBox symbolsCheck = new JCheckBox("Semboller (#, $, %, vb.)");

        panel.add(lowerCaseCheck);
        panel.add(upperCaseCheck);
        panel.add(numbersCheck);
        panel.add(symbolsCheck);

        JLabel strengthLabel = new JLabel("Şifre Gücü: Zayıf");
        panel.add(strengthLabel);

        JButton generateButton = new JButton("Şifreyi Oluştur");
        panel.add(generateButton);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = generatePassword(lengthSlider.getValue(), lowerCaseCheck.isSelected(),
                        upperCaseCheck.isSelected(), numbersCheck.isSelected(), symbolsCheck.isSelected());
                passwordField.setText(password);
                String strength = getPasswordStrength(password);
                strengthLabel.setText("Şifre Gücü: " + strength);
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static String generatePassword(int length, boolean useLowerCase, boolean useUpperCase, boolean useNumbers, boolean useSymbols) {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String symbols = "#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        StringBuilder charPool = new StringBuilder();

        if (useLowerCase) {
            charPool.append(lowerCase);
        }
        if (useUpperCase) {
            charPool.append(upperCase);
        }
        if (useNumbers) {
            charPool.append(numbers);
        }
        if (useSymbols) {
            charPool.append(symbols);
        }

        if (charPool.length() == 0) {
            charPool.append(lowerCase);
        }

        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charPool.length());
            password.append(charPool.charAt(randomIndex));
        }

        return password.toString();
    }

    public static String getPasswordStrength(String password) {
        if (password.length() >= 12 && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*")
                && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            return "Güçlü";
        } else if (password.length() >= 8) {
            return "Orta";
        } else {
            return "Zayıf";
        }
    }
}
