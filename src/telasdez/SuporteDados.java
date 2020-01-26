package telasdez;


public class SuporteDados {

	private int id;
	
	private String solicitante;
	private String setor;
	private String descricao;
	private String data;
	private String nomeLogin;
	private String senhaLogin;
	
	//Altera senha
	private String altSenha;


	
	
	public String getAltSenha() {
		return altSenha;
	}
	public void setAltSenha(String altSenha) {
		this.altSenha = altSenha;
	}
	public String getNomeLogin() {
		return nomeLogin;
	}
	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin = nomeLogin;
	}
	public String getSenhaLogin() {
		return senhaLogin;
	}
	public void setSenhaLogin(String senhaLogin) {
		this.senhaLogin = senhaLogin;
	}
	
	public String getData() {
		
		return data;
	}
	public void setData(String dataAtual) {
		this.data = dataAtual;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}

