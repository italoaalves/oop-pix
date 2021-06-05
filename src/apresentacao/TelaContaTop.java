package apresentacao;

import javax.swing.*;

public class TelaContaTop extends JFrame {
    private JPanel panel1;
    private JTextArea textArea1;
    private JScrollPane scrollPane;

    public TelaContaTop(String conta) {
        super.setTitle("Conta top");
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setBounds(100, 100, 424, 226);
        super.setContentPane(panel1);

        textArea1.setText(conta);
    }
}
