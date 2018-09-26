
public class Conta {
	private int agencia;
	private int numero;
	private Cliente cliente;
	private double saldo;
	
	public Conta(int agencia, int numero, Cliente cliente, double saldo) {
		this.agencia = agencia;
		this.numero = numero;
		this.cliente = cliente;
		this.saldo = saldo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Agência: " + getAgencia()		
			+ "\nNúmero: " + getNumero()
			+ "\nCliente: " + getCliente()
			+ String.format("\nSaldo: R$ %.2f", 
					getSaldo());
	}
	
	
	
}
