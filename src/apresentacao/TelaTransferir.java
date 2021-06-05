package apresentacao;

import fachada.Fachada;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaTransferir extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton transferirButton;
    private JPanel panel1;
    private JLabel lblmsg;

    public TelaTransferir() {
        super.setTitle("Transferir");
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setBounds(100, 100, 424, 226);
        super.setContentPane(panel1);

        transferirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contaOrigem = textField1.getText();
                String contaDestino = textField2.getText();
                double valor = Double.parseDouble(textField3.getText());
                try {
                    Fachada.transferir(contaOrigem, contaDestino, valor);
                    lblmsg.setText("Sucesso");
                } catch(Exception erro) {
                    lblmsg.setText(erro.getMessage());
                }
            }
        });
    }
}
