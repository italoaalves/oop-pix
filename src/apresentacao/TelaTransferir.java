package apresentacao;

import fachada.Fachada;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaTransferir extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label;
	private JLabel label_1;
	private JButton btnTransferir;
	private JLabel lblmsg;
	private JLabel label_2;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCriarConta frame = new TelaCriarConta();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaTransferir() {
		setTitle("Transferir");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 424, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(180, 8, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		label = new JLabel("CPF conta origem: ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(20, 8, 160, 14);
		contentPane.add(label);

		label_1 = new JLabel("PIKS conta destino: ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(20, 36, 160, 14);
		contentPane.add(label_1);

		textField_1 = new JTextField();
		textField_1.setBounds(180, 33, 100, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		label_2 = new JLabel("Valor:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(20, 61, 100, 14);
		contentPane.add(label_2);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(180, 61, 100, 20);
		contentPane.add(textField_2);

		lblmsg = new JLabel();
		lblmsg.setHorizontalAlignment(SwingConstants.LEFT);
		lblmsg.setBounds(20, 121, 300, 14);
		contentPane.add(lblmsg);

		btnTransferir = new JButton("Transferir");
		btnTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblmsg.setText("");
					String contaorigemcpf = textField.getText();
					String contadestino = textField_1.getText();
					double valor = Double.parseDouble(textField_2.getText());

					Fachada.transferir(contaorigemcpf, contadestino, valor);
					lblmsg.setText("Sucesso.");
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnTransferir.setBounds(227, 91, 95, 23);
		contentPane.add(btnTransferir);
	}
}
