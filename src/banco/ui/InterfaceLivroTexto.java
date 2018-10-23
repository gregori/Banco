package banco.ui;

import java.util.List;

import banco.dao.ClienteDao;
import banco.modelo.Cliente;

public class InterfaceClienteTexto extends InterfaceModeloTexto {
	
	private ClienteDao dao;
	
	public InterfaceClienteTexto() {
		super();
		dao = new ClienteDao();
	}
	
	private Cliente obtemDadosCliente(Cliente cliente) {
		
		System.out.print("Insira o nome do cliente: ");
		String nome = entrada.nextLine();
		
		System.out.println("Insira o RG do cliente (somente números): ");
		long rg = entrada.nextLong();
		entrada.nextLine();
		
		System.out.println("Insira o CPF do cliente (somente números): ");
		long cpf = entrada.nextLong();
		entrada.nextLine();
		
		System.out.println("Insira o endereco do cliente: ");
		String endereco = entrada.nextLine();
		
		System.out.println("Insira o telefone do cliente (somente números): ");
		long telefone = entrada.nextLong();
		entrada.nextLine();
		
		System.out.println("Insira a renda mensal do cliente (XXXXX,XX): ");
		double renda = entrada.nextDouble();
		entrada.nextLine();
		
		Cliente novoCliente = new Cliente(0, nome, endereco, cpf, rg, telefone, renda);
		
		return novoCliente;
	}
	
	@Override
	public void adicionar() {
		System.out.println("Adicionar cliente");
		System.out.println();
		
		Cliente novoCliente = obtemDadosCliente(null);	
		dao.insert(novoCliente);
		
	}

	@Override
	public void listarTodos() {
		List<Cliente> clientes = dao.getAll();
		
		System.out.println("Lista de clientes");
		System.out.println();
		
		System.out.println("id\tNome\tEndereco\tCPF\tRG\tTelefone\tRenda Mensal");
		
		for (Cliente cliente : clientes) {
			imprimeItem(cliente);
		}
		
	}

	@Override
	public void editar() {
		listarTodos();
		
		System.out.println("Editar cliente");
		System.out.println();
		
		System.out.print("Entre o id do cliente: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		Cliente clienteAModifcar = dao.getByKey(id);
		
		Cliente novoCliente = obtemDadosCliente(clienteAModifcar);
		
		novoCliente.setId(id);
		
		dao.update(novoCliente);
		
	}

	@Override
	public void excluir() {
		listarTodos();
		
		System.out.println("Excluir cliente");
		System.out.println();
		
		System.out.print("Entre o id do cliente: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		dao.delete(id);
		
	}

}
