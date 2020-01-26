package telasdez;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BD.SuporteBD;

public class SConsultaAtendidos extends JFrame {
	private static final long serialVersionUID = 1L;
	 
    
    JButton jbInserir = new JButton("Inserir");
    
    SuporteBD suporteBD = new SuporteBD();
    
    DefaultTableModel modelo;
    
    JTable jtable;
   
    ResultSet rs;
    public SConsultaAtendidos(){
 	   
 	   super("Chamados Atendidos");

		Container j = getContentPane();
		j.setLayout(new FlowLayout(FlowLayout.RIGHT));

		modelo = new DefaultTableModel();

		jtable = new JTable(modelo);


		modelo.addColumn("Cód do Chamado");
		modelo.addColumn("Atendente");
		modelo.addColumn("Solicitante");
		modelo.addColumn("Setor");
		modelo.addColumn("Descricao");

		jtable.getColumnModel().getColumn(0).setPreferredWidth(30);
		jtable.setRowHeight(30);

		rs = suporteBD.populaGridCAtendidos();
		try {
			while (rs.next()) {
				
				modelo.addRow(new Object[] { String.valueOf(rs.getInt("idChamado")),String.valueOf(rs.getString("nome")), rs.getString("solicitante"),
						rs.getString("setor"), rs.getString("descricao") });
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

            JScrollPane scroll = new JScrollPane(jtable);
            JPanel p1 = new JPanel();
            p1.setLayout(null);
            p1.setPreferredSize(new Dimension(720,250));
            p1.add(scroll);
            
            scroll.setBounds(5,20,710,200);

            j.add(p1);
            
            this.setSize(740,280);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setVisible(true);

    }

}
