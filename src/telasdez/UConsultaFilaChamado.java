package telasdez;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BD.SuporteBD;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UConsultaFilaChamado extends JFrame {
       private static final long serialVersionUID = 1L;
 
       
       JButton jbInserir = new JButton("Inserir");
       
       SuporteBD suporteBD = new SuporteBD();
       
       DefaultTableModel modelo;
       
       JTable jtable;
      
       ResultSet rs;
       public UConsultaFilaChamado(){
    	   
    	   super("Fila de Espera - Chamados ");

   		Container j = getContentPane();
   		j.setLayout(new FlowLayout(FlowLayout.RIGHT));

   		modelo = new DefaultTableModel();

   		jtable = new JTable(modelo);


   		modelo.addColumn("Código");
   		modelo.addColumn("Data");
   		modelo.addColumn("Nome");
   		modelo.addColumn("Setor");
   		modelo.addColumn("Descricao");

   		jtable.getColumnModel().getColumn(0).setPreferredWidth(30);
   		jtable.setRowHeight(30);
   		jtable.setEnabled(false);

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
               p1.setPreferredSize(new Dimension(720,250));
               p1.add(scroll);
               
               scroll.setBounds(5,20,710,200);

               j.add(p1);
               
               this.setSize(740,280);
               this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
               this.setVisible(true);

       }
     
  
}
