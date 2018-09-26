package banco;
import java.util.ArrayList;
import java.util.Scanner;

import banco.modelo.Cliente;
import banco.modelo.Conta;


public class Principal {

	public static void main(String[] args) {
		ArrayList<Conta> banco = new ArrayList<>();

		System.out.println("Agência Bancária");
			
		//contasComFlag(banco);
		
		contasComSentinela(banco);
		
		for (Conta c : banco) {
			System.out.println("Conta corrente: ");
			System.out.println(c);
		}

	}
	
	private static void contasComFlag(ArrayList<Conta> banco) {
		boolean continua = true;
		
		Scanner entrada = new Scanner(System.in);
		
		while (continua) {		
			Cliente cliente = new Cliente();
			Conta conta;
			
			System.out.print("Entre o nome do cliente: ");
			cliente.setNome(entrada.nextLine());
			
			System.out.print("Entre o cpf do cliente: ");
			cliente.setCpf(entrada.nextLong());
			entrada.nextLine();
			
			System.out.print("Entre o rg do cliente: ");
			cliente.setRg(entrada.nextLong());
			entrada.nextLine();
			
			System.out.print("Entre o endereço do cliente: ");
			cliente.setEndereco(entrada.nextLine());
			
			System.out.print("Entre o telefone do cliente: ");
			cliente.setTelefone(entrada.nextLong());
			entrada.nextLine();
			
			System.out.print("Entre a renda mensal do cliente: ");
			cliente.setRendaMensal(entrada.nextDouble());
			entrada.nextLine();
			
			System.out.print("Entre a agência da conta bancária: ");
			int agencia = entrada.nextInt();
			entrada.nextLine();
			
			System.out.print("Entre o número da conta bancária: ");
			int numero = entrada.nextInt();
			entrada.nextLine();
			
			System.out.print("Entre o saldo inicial: ");
			double saldo = entrada.nextDouble();
			entrada.nextLine();
						
			conta = new Conta(agencia, numero, cliente, saldo);
			
			banco.add(conta);
			
			System.out.print("Deseja continuar? (s/n) ");
			continua = entrada.nextLine().equals("s");

		}
		
		entrada.close();
	}
	
	private static void contasComSentinela(ArrayList<Conta> banco) {
		Scanner entrada = new Scanner(System.in);
		
		Cliente cliente = new Cliente();
		
		System.out.print("Entre o nome do cliente, ou deixe em branco para terminar: ");
		cliente.setNome(entrada.nextLine());
		
		while (!cliente.getNome().isEmpty()) {		
			Conta conta;
						
			System.out.print("Entre o cpf do cliente: ");
			cliente.setCpf(entrada.nextLong());
			entrada.nextLine();
			
			System.out.print("Entre o rg do cliente: ");
			cliente.setRg(entrada.nextLong());
			entrada.nextLine();
			
			System.out.print("Entre o endereço do cliente: ");
			cliente.setEndereco(entrada.nextLine());
			
			System.out.print("Entre o telefone do cliente: ");
			cliente.setTelefone(entrada.nextLong());
			entrada.nextLine();
			
			System.out.print("Entre a renda mensal do cliente: ");
			cliente.setRendaMensal(entrada.nextDouble());
			entrada.nextLine();
			
			System.out.print("Entre a agência da conta bancária: ");
			int agencia = entrada.nextInt();
			entrada.nextLine();
			
			System.out.print("Entre o número da conta bancária: ");
			int numero = entrada.nextInt();
			entrada.nextLine();
			
			System.out.print("Entre o saldo inicial: ");
			double saldo = entrada.nextDouble();
			entrada.nextLine();
						
			conta = new Conta(agencia, numero, cliente, saldo);
			
			banco.add(conta);
			cliente = new Cliente();
			
			System.out.print("Entre o nome do cliente, ou deixe em branco para terminar: ");
			cliente.setNome(entrada.nextLine());
		}
		
		entrada.close();

	}
}
