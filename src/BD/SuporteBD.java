package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import telasdez.SuporteDados;

public class SuporteBD {

	private Connection con;

	public SuporteBD() {

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/suporte", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro de conexão com banco de dados.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}

	}

	public void inserir(SuporteDados f) throws SQLException {

		String sql = "INSERT into chamados " + "(atendido,dataHora,solicitante,setor,descricao)"
				+ " VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setBoolean(1, false);
			stmt.setString(2, f.getData());
			stmt.setString(3, f.getSolicitante());
			stmt.setString(4, f.getSetor());
			stmt.setString(5, f.getDescricao());

			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ResultSet populaGrid() {

		Statement stm;
		String sql = "SELECT `idChamado`,`atendido`,"
				+ "DATE_FORMAT( `dataHora` , '%d-%c-%Y ás %H:%m' )as `dataHora`"
				+ ",`solicitante`,`setor`,`descricao` FROM `chamados`";
		try {
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			return rs;

		} catch (SQLException e) {
			// exibeErro(e.getMessage());
			return null;
		}

	}

	public void excluir(String id) {

		try {
			String sql = "delete from chamados where idChamado= ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {

		}
	}

	public void updateAtendido(String id, int idAtendente) {
		try {
			String sql ="update chamados set atendido=1 where idChamado=?";
			String sql2 = "insert into chamados_atendidos (idChamado, idAtendente, data) values (?,?,'2016-11-24 00:00:00')";
			PreparedStatement stm = con.prepareStatement(sql);
			PreparedStatement stm2 = con.prepareStatement(sql2);
			stm.setString(1, id);
			stm2.setString(1, id);
			stm2.setInt(2, idAtendente);
			stm.executeUpdate();
			stm2.executeUpdate();
			stm.close();
			stm2.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um Erro");
		}
	}
	
	public ResultSet login(SuporteDados f){
		String sql = "select * from atendentes where nome = ? AND senha = ?";


		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, f.getNomeLogin());
			stmt.setString(2, f.getSenhaLogin());
			

		ResultSet rs = stmt.executeQuery();
		System.out.println("Conectou");
		
		return rs;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
					
		
	}
	
	public ResultSet populaGridCAtendidos() {

		Statement stm;
		String sql = "SELECT *FROM chamados INNER JOIN chamados_atendidos ON chamados.idChamado = chamados_atendidos.idChamado"
+" INNER JOIN atendentes ON atendentes.idAtendente = chamados_atendidos.idAtendente;";
		try {
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			return rs;

		} catch (SQLException e) {
			// exibeErro(e.getMessage());
			return null;
		}

	}
	
	
	public boolean alterarSenha(String senha){
		
		String sql = "UPDATE atendentes SET senha=? WHERE nome='admin'; ";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, senha);
			stmt.executeUpdate();
			stmt.close();
			return true;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	public void testeConexao(){
		try {
			if(con.isValid(0)){  
				  
				}  
				else{  
					 
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	

}
