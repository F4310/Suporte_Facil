package telasdez;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class UMenuUsuario implements ActionListener {
	private JButton btnSuporte = new JButton("Solicitar Suporte");
	private JButton btnCunsulta = new JButton("Consultar Fila");
	private JButton btnVoltar = new JButton("Voltar");
	JFrame janela = new JFrame("Menu Do Usuario");

	
	public UMenuUsuario() {

		JPanel panForm = new JPanel(new GridLayout(3, 1)); // Linhas , Coluna

		//Adicionando os botões
		panForm.add(btnCunsulta);
		panForm.add(btnSuporte);
		panForm.add(btnVoltar);
	
		

		janela.add(panForm);
		janela.setSize(400, 200);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Cria os Listerners dos botões
		btnCunsulta.addActionListener(this);
		btnSuporte.addActionListener(this);
		btnVoltar.addActionListener(this);
	

	}

	//Trata após ação de clicar no botão
	public void actionPerformed(ActionEvent e) {
		
		//Pega o valor do botão pressionado
		String cmd = e.getActionCommand();
		
		if ("Solicitar Suporte".equals(cmd)) {
			new USolicitarSuporte();
		}
		
		if ("Consultar Fila".equals(cmd)) {
			
			new UConsultaFilaChamado();

		}
		
		if("Voltar".equals(cmd)){
			new SelecaoModo();
			janela.dispose();
		}
	

	}
}