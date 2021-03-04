package telasdez;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BD.SuporteBD;

public class SelecaoModo implements ActionListener {
	private JButton btnSolicitante = new JButton("Modo Solicitante");
	private JButton btnAtendente = new JButton("Modo Técnico");
	private JLabel lblEscolha = new JLabel("Escolha o modo de Usuario");
	JFrame janela;
	SuporteBD suporteBD = new SuporteBD();
	public static void main(String[] args) {

		new SelecaoModo();
	}

	public SelecaoModo() {

		janela = new JFrame("Menu Do Usuario");
		JPanel panForm = new JPanel(new GridLayout(1, 2));
		JPanel painel1 = new JPanel(new BorderLayout());
		
		panForm.add(btnSolicitante);
		panForm.add(btnAtendente);
		painel1.add(lblEscolha,BorderLayout.NORTH);
		painel1.add(panForm,BorderLayout.SOUTH);

		janela.add(painel1);
		
		suporteBD.testeConexao();
		janela.setSize(300, 80);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnSolicitante.addActionListener(this);
		btnAtendente.addActionListener(this);
	
		

	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("Modo Solicitante".equals(cmd)) {
			new UMenuUsuario();
			janela.dispose();
		}
		if ("Modo Técnico".equals(cmd)) {
			new SLogin();
			janela.dispose();
		}

	}
}
