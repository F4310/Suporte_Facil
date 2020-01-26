package telasdez;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BD.SuporteBD;

public class SLogin implements ActionListener {
	private JButton btnEntrar = new JButton("Entrar");
	private JButton btnVoltar = new JButton("Voltar");
	private JTextField txtNome = new JTextField();
	private JPasswordField txtSenha = new JPasswordField();
	private JLabel lblNome = new JLabel("Nome");
	private JLabel lblSenha = new JLabel("Senha");
	SuporteDados f = new SuporteDados();
	SuporteBD suporte = new SuporteBD();
	ResultSet rs;
	JFrame janela;

	
	//Contrutor classe SLogin
	public SLogin() {

		janela = new JFrame("Autentificação");
		JPanel panForm = new JPanel(new GridLayout(6, 1));

		panForm.add(lblNome);
		panForm.add(txtNome);
		panForm.add(lblSenha);
		panForm.add(txtSenha);
		panForm.add(btnEntrar);
		panForm.add(btnVoltar);

		janela.add(panForm,BorderLayout.CENTER);

		janela.setSize(300, 200);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnEntrar.addActionListener(this);
		btnVoltar.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();
		if ("Entrar".equals(cmd)) {
			
			if (txtNome.getText().equals("") || txtSenha.getPassword().length==0) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Autentificação", JOptionPane.ERROR_MESSAGE);
				
			} else {
				f = Login();
				rs = suporte.login(f);
				int id = 0;

				try {

					// verificando se o rs retornou algo - Fabio
					if (rs.isBeforeFirst() == true) {
						while (rs.next()) {

							id = rs.getInt(1); // envia o id do atendente para prox tela
												

						}
						new SConsultaChamado(id);
						janela.dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Incorreto, verifique os dados!");
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}

		if ("Voltar".equals(cmd)) {
			new SelecaoModo();
			janela.dispose();
		}

	}

	public SuporteDados Login() {

		f.setNomeLogin(txtNome.getText());
		f.setSenhaLogin(txtSenha.getText());

		return f;

	}

}
