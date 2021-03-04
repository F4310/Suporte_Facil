package telasdez;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BD.SuporteBD;

public class USolicitarSuporte implements ActionListener {
	private JLabel lblSol = new JLabel("Solicitante");
	private JLabel lblSet = new JLabel("Setor");
	private JLabel lblDes = new JLabel("Descrição");
	private JTextField txtSol = new JTextField();
	private JTextField txtSet = new JTextField();
	private JTextField txtDes = new JTextField();
	private JButton btnEnviar = new JButton("Enviar");
	private JButton btnCancelar = new JButton("Cancelar");
	SuporteDados f = new SuporteDados();
	private JFrame janela;

	public USolicitarSuporte() {

		janela = new JFrame("Solicitar Suporte");
		JPanel painel1 = new JPanel(new BorderLayout());
		JPanel painel2 = new JPanel(new GridLayout(3, 2));
		JPanel painel3 = new JPanel(new GridLayout(1, 2));

		painel2.add(lblSol);
		painel2.add(txtSol);
		painel2.add(lblSet);
		painel2.add(txtSet);
		painel2.add(lblDes);
		painel3.add(btnEnviar);
		painel3.add(btnCancelar);

		painel1.add(painel2, BorderLayout.NORTH);
		painel1.add(txtDes, BorderLayout.CENTER);
		painel1.add(painel3, BorderLayout.SOUTH);

		janela.add(painel1);

		janela.setSize(600, 400);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);

		btnEnviar.addActionListener(this);
		btnCancelar.addActionListener(this);

	}

	public SuporteDados chamado() {

		formataData data = new formataData();
		String dataAtual = data.dataAtual();
		f.setData(dataAtual);
		f.setSolicitante(txtSol.getText());
		f.setSetor(txtSet.getText());
		f.setDescricao(txtDes.getText());
		return f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		if ("Enviar".equals(cmd)) {
			if (txtSol.getText().equals("") || txtSet.getText().equals("") || txtDes.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Solicitar Suporte",
						JOptionPane.ERROR_MESSAGE);

			} else {

				SuporteBD suporteBD = new SuporteBD();
				try {
					f = chamado();
					suporteBD.inserir(f);
					JOptionPane.showMessageDialog(null,
							"Chamado registrado! \n Você será atendido assim que possivel.");
					new UConsultaFilaChamado();
					janela.dispose();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		
		if ("Cancelar".equals(cmd)) {
			
			janela.dispose();

		}

	}

}
