
public class Cliente extends Pessoa {
	private double rendaMensal;

	public Cliente() { super(); }
	
	public Cliente(String nome, String endereco, long cpf, long rg,
			long telefone, double rendaMensal) {
		super(nome, endereco, cpf, rg, telefone);

		this.rendaMensal = rendaMensal;
	}

	public double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	@Override
	public String toString() {
		return super.toString()
				+ String.format("\nRenda mensal: R$ %.2f", 
						getRendaMensal());
	}
	
	
	
}
