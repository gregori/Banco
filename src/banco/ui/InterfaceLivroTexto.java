package banco.ui;

import java.util.List;

import banco.dao.LivroDao;
import banco.dao.AutorDao;
import banco.modelo.Livro;
import banco.modelo.Autor;

public class InterfaceLivroTexto extends InterfaceModeloTexto {
	
	private LivroDao dao;
	
	public InterfaceLivroTexto() {
		super();
		dao = new LivroDao();
	}
	
	private Livro obtemDadosLivro(Livro livro) {
		
		System.out.print("Insira o titulo do livro: ");
		String titulo = entrada.nextLine();
		
		System.out.println("Insira o ano de publicação do livro (número de 4 dígitos): ");
		int anoPublicacao = entrada.nextInt();
		entrada.nextLine();
		
		System.out.println("Insira a editora do livro: ");
		String editora = entrada.nextLine();
		entrada.nextLine();
		
		System.out.println("Insira o id do autor do livro: ");
		int autorId = entrada.nextInt();
		
		AutorDao autorDao = new AutorDao();
		
		Autor autor = autorDao.getByKey(autorId);
		
		Livro novoLivro = new Livro(0, titulo, anoPublicacao, editora, autor);
		
		return novoLivro;
	}
	
	@Override
	public void adicionar() {
		System.out.println("Adicionar livro");
		System.out.println();
		
		Livro novoLivro = obtemDadosLivro(null);	
		dao.insert(novoLivro);
		
	}

	@Override
	public void listarTodos() {
		List<Livro> livros = dao.getAll();
		
		System.out.println("Lista de livros");
		System.out.println();
		
		System.out.println("id\tTítulo\tAno Publicação\tEditora");
		
		for (Livro livro : livros) {
			imprimeItem(livro);
		}
		
	}

	@Override
	public void editar() {
		listarTodos();
		
		System.out.println("Editar livro");
		System.out.println();
		
		System.out.print("Entre o id do livro: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		Livro livroAModifcar = dao.getByKey(id);
		
		Livro novoLivro = obtemDadosLivro(livroAModifcar);
		
		novoLivro.setId(id);
		
		dao.update(novoLivro);
		
	}

	@Override
	public void excluir() {
		listarTodos();
		
		System.out.println("Excluir livro");
		System.out.println();
		
		System.out.print("Entre o id do livro: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		dao.delete(id);
		
	}

}
