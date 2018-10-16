package banco.ui;

import java.util.List;
import java.util.Scanner;

import banco.modelo.Imprimivel;

abstract public class InterfaceModeloTexto {
	abstract public void adicionar();
	abstract public void listarTodos();
	abstract public void editar();
	abstract public void excluir();
	protected Scanner entrada;
	
	public InterfaceModeloTexto() {
		entrada = new Scanner(System.in);
	}
	
	protected void imprimeItem(Imprimivel imprimivel) {
		System.out.println(imprimivel.imprimeEmLista());
	}
	
	protected void imprimeLista(List<Imprimivel> imprimiveis, String[] colunas) {
		for (String coluna : colunas) {
			System.out.print(coluna + "\t");
		}
		System.out.println();
		
		for (Imprimivel item : imprimiveis) {
			imprimeItem(item);
		}
	}
	
}
