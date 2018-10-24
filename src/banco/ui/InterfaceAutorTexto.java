package banco.ui;

import java.util.List;

import banco.dao.LivroDao;
import banco.dao.AutorDao;
import banco.modelo.Autor;

public class InterfaceAutorTexto extends InterfaceModeloTexto {

	private AutorDao dao;
	private LivroDao clienteDao;
	
	public InterfaceAutorTexto() {
		super();
		dao = new AutorDao();
	}
	
	@Override
	public void adicionar() {
		System.out.println("Adicionar autor");
		System.out.println();
		
		Autor novoAutor = obtemDadosConta(null);	
		dao.insert(novoAutor);
	}

	private Autor obtemDadosConta(Autor conta) {
		System.out.print("Insira o nome do autor: ");
		String nome = entrada.nextLine();
		
		System.out.print("Insira o cpf da autor: ");
		long cpf = entrada.nextLong();
		
		return new Autor(0, nome, cpf);
	}

	@Override
	public void listarTodos() {
		List<Autor> contas = dao.getAll();
		
		System.out.println("Lista de autores");
		System.out.println();
		
		System.out.println("id\tNome\tCPF");
		
		for (Autor conta : contas) {
			imprimeItem(conta);
		}
	}

	@Override
	public void editar() {
		listarTodos();
		
		System.out.println("Editar autor");
		System.out.println();
		
		System.out.print("Entre o id do autor: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		Autor contaAModificar = dao.getByKey(id);
		
		Autor novoAutor = obtemDadosConta(contaAModificar);
		
		novoAutor.setId(id);
		
		dao.update(novoAutor);
	}

	@Override
	public void excluir() {
		listarTodos();
		
		System.out.println("Excluir autor");
		System.out.println();
		
		System.out.print("Entre o id da autor: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		dao.delete(id);
	}

}
