package banco.modelo;

public class Pessoa {
	private int id;
	private String nome;
	private String endereco;
	private long cpf;
	private long rg;
	private long telefone;
	
	public Pessoa() { }
	
	public Pessoa(int id, String nome, String endereco, long cpf, long rg,
			long telefone) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.rg = rg;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public long getRg() {
		return rg;
	}

	public void setRg(long rg) {
		this.rg = rg;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "nome: " + getNome() 
				+ "\nendere�o: " + getEndereco()
				+ "\nCPF: " 
						+ String.valueOf(getCpf())
						.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", 
								"$1.$2.$3-$4")
			    + "\nRG: "
						+ String.valueOf(getRg())
						.replaceFirst("(\\d{1})(\\d{3})(\\d{3})(\\d*)", 
								"$1.$2.$3")
				+ "\nTelefone: "	
					+ String.valueOf(getTelefone())
					.replaceFirst("(\\d{2})(\\d{4})(\\d{4})", 
							"($1) $2-$3");

	}
	
	
	
}
