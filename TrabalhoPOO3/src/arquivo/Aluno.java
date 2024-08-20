package arquivo;

public class Aluno {

	private String nome;
	private String respostas;
	private int numAcertos;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRespostas() {
		return respostas;
	}
	public void setRespostas(String respostas) {
		this.respostas = respostas;
	}
	public int getNumAcertos() {
		return numAcertos;
	}
	public void setNumAcertos(int numAcertos) {
		this.numAcertos = numAcertos;
	}
	
	public Aluno(String nome, String respostas) {
		this.nome = nome;
		this.respostas = respostas;
	}
	
}