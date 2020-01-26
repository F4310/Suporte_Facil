package telasdez;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BD.SuporteBD;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SConsultaChamado extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JButton btnAtender = new JButton("Atender");
	JButton btnExcluir = new JButton("Excluir");
	JButton btnCAtendidos = new JButton("Chamados Atendidos");
	JButton btnVoltar = new JButton("Voltar");
	JButton btnAltSenha = new JButton("Alterar sua Senha");
	SuporteBD suporteBD = new SuporteBD();

	DefaultTableModel modelo;

	JTable jtable;

	ResultSet rs;
	int idAtendente;

	public SConsultaChamado(int idAt) {
		super("Consulta Chamados");
		idAtendente = idAt;
		
		Container j = getContentPane();
		j.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnAtender.addActionListener(this);
		btnExcluir.addActionListener(this);
		btnCAtendidos.addActionListener(this);
		btnVoltar.addActionListener(this);
		btnAltSenha.addActionListener(this);
		
		
		modelo = new DefaultTableModel();

		jtable = new JTable(modelo);


		modelo.addColumn("Código");
		modelo.addColumn("Data");
		modelo.addColumn("Nome");
		modelo.addColumn("Setor");
		modelo.addColumn("Descricao");

		jtable.getColumnModel().getColumn(0).setPreferredWidth(30);
		jtable.getColumnModel().getColumn(4).setPreferredWidth(300);
		jtable.setRowHeight(30);
		
		
	


		rs = suporteBD.populaGrid();
		try {
			while (rs.next()) {
				
				modelo.addRow(new Object[] { String.valueOf(rs.getInt("idChamado")),String.valueOf(rs.getString("dataHora")), rs.getString("solicitante"),
						rs.getString("setor"), rs.getString("descricao") });
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		JScrollPane scroll = new JScrollPane(jtable);
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setPreferredSize(new Dimension(720, 320)); //tamanho do painel da tabela
		p1.add(scroll);

		scroll.setBounds(0, 5, 720, 300); //tamanho da tabela e etc;

		j.add(p1);
		j.add(btnCAtendidos);
		j.add(btnAtender);
		j.add(btnExcluir);
		j.add(btnVoltar);
		j.add(btnAltSenha);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(740, 420);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		
		String cmd = e.getActionCommand();
		
		if ("Atender".equals(cmd)) {
			Object[] options = { "Sim", "Não" };
			int i = JOptionPane.showOptionDialog(null, "Deseja mudar o status para atendido?\n",
					"Atender Chamado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[1]);
			if (i == JOptionPane.YES_OPTION) {

				String id = (String.valueOf(jtable.getValueAt(jtable.getSelectedRow(), 0)));
				suporteBD.updateAtendido(id,idAtendente);
				modelo.removeRow(jtable.getSelectedRow());
				JOptionPane.showMessageDialog(null, "Chamado atendido com sucesso","Atender Chamado",JOptionPane.INFORMATION_MESSAGE);
			}
		}

			if ("Excluir".equals(cmd)) {
				Object[] options1 = { "Sim", "Não" };
				int i = JOptionPane.showOptionDialog(null, "Deseja realmente excluir este chamado?\n",
						"Exclusão de Chamado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1,
						options1[1]);
				if (i == JOptionPane.YES_OPTION) {

					String id = (String.valueOf(jtable.getValueAt(jtable.getSelectedRow(), 0)));
					suporteBD.excluir(id);
					JOptionPane.showMessageDialog(null, "Chamado excluido com sucesso","Exclusão de Chamado",JOptionPane.INFORMATION_MESSAGE);
					modelo.removeRow(jtable.getSelectedRow());
				}

			}
			
			if ("Chamados Atendidos".equals(cmd)) {
				Object[] options = { "Sim", "Não" };
				int i = JOptionPane.showOptionDialog(null, "Consultar Chamados já atendidos?\n",
						"Consultar Atendidos", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						options, options[1]);
				if (i == JOptionPane.YES_OPTION) {

					new SConsultaAtendidos();
					
				}
			}
			
			if ("Voltar".equals(cmd)) {
				new SelecaoModo();
				this.dispose();
			}
			
			if("Alterar sua Senha".equals(cmd)){
				new SAlteraSenha();
			}
			
		
	}

	
}