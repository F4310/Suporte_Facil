package telasdez;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import BD.SuporteBD;

public class SAlteraSenha extends JFrame implements ActionListener {

	private JButton btnAlterar = new JButton("Alterar");
	private JButton btnCancelar = new JButton("Cancelar");
	private JPasswordField txtNovaSenha = new JPasswordField();
	private JPasswordField txtConfirmaNovaSenha = new JPasswordField();
	private JLabel lblNovaSenha = new JLabel("Nova Senha");
	private JLabel lblConfirmaNovaSenha = new JLabel("Confirme a Nova Senha");

	SuporteDados f = new SuporteDados();

	SuporteBD suporteBD = new SuporteBD();
	JFrame janela;

	public SAlteraSenha() {
		// TODO Auto-generated constructor stub
		janela = new JFrame("Alterar Senha");
		JPanel panForm = new JPanel(new GridLayout(6, 1));

		panForm.add(lblNovaSenha);
		panForm.add(txtNovaSenha);
		panForm.add(lblConfirmaNovaSenha);
		panForm.add(txtConfirmaNovaSenha);
		panForm.add(btnAlterar);
		panForm.add(btnCancelar);

		janela.add(panForm, BorderLayout.CENTER);
		janela.setSize(300, 200);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		btnAlterar.addActionListener(this);
		btnCancelar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String cmd = e.getActionCommand();

		if ("Alterar".equals(cmd)) {

			String senha, confirmacao;
			senha = txtNovaSenha.getText();
			confirmacao = txtConfirmaNovaSenha.getText();

			if (senha.equals(confirmacao)) { //se as senhas forem iguais, prossegue com a alteração

				if (suporteBD.alterarSenha(confirmacao) == true) { //passa senha como parametro chamando a função e já confirmando  o sucesso

					JOptionPane.showMessageDialog(null, "Senha alterada");
					this.dispose();

				} else { //algum erro que retorne falso

					System.out.println("Oh no! Ocorreu um erro."); 
					
				}
			}

			else {
				
				JOptionPane.showMessageDialog(null, "As senhas digitadas não são iguais!");
				txtNovaSenha.setText(null);
				txtConfirmaNovaSenha.setText(null);
				
			}

		}

		if ("Cancelar".equals(cmd)) {
			System.out.println("Cancelou");
		}

	}

}
