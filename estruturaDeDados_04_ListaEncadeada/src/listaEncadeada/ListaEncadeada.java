package listaEncadeada;

public class ListaEncadeada<T> {

	No<T> referenciaEntrada;

	public ListaEncadeada() {

		this.referenciaEntrada = null;
	}

	@SuppressWarnings("unchecked")
	public void add(T conteudo) {
		No<T> novoNo = new No<>(conteudo); // cria um novo nó com o conteúdo fornecido
		if (this.isEmpty()) { // verifica se a lista está vazia
			referenciaEntrada = novoNo; // se estiver vazia, o novo nó é adicionado como o primeiro nó da lista
			return; // encerra a função
		}

		No<T> noAuxiliar = referenciaEntrada; // cria uma referência auxiliar para o primeiro nó da lista
		for (int i = 0; i < this.size() - 1; i++) { // percorre a lista até o penúltimo nó
			noAuxiliar = (No<T>) noAuxiliar.getProximoNo(); // atualiza a referência auxiliar para apontar para o
															// próximo nó
		}
		noAuxiliar.setProximoNo(novoNo); // adiciona o novo nó como o próximo nó após o último nó existente
	}

	public T get(int index) {
		return getNo(index).getConteudo(); // retorna o conteúdo do nó na posição especificada
	}

	@SuppressWarnings("unchecked")
	private No<T> getNo(int index) {
		validaIndice(index); // verifica se o índice fornecido é válido

		No<T> noAuxiliar = referenciaEntrada; // cria uma referência auxiliar para o primeiro nó da lista
		No<T> noRetorno = null; // cria uma referência para o nó de retorno

		for (int i = 0; i <= index; i++) { // percorre a lista até o índice fornecido
			noRetorno = noAuxiliar; // atualiza a referência de retorno para apontar para o nó atual
			noAuxiliar = (No<T>) noAuxiliar.getProximoNo(); // atualiza a referência auxiliar para apontar para o
															// próximo nó
		}
		return noRetorno; // retorna o nó de retorno
	}

	
	
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		No<T> noPivo = this.getNo(index); // obtém a referência para o nó na posição especificada

		if (index == 0) { // se for o primeiro nó da lista
			referenciaEntrada = (No<T>) noPivo.getProximoNo(); // atualiza a referência de entrada para apontar para o
																// próximo nó
			return noPivo.getConteudo(); // retorna o conteúdo do nó removido
		}

		No<T> noAnterior = getNo(index - 1); // obtém a referência para o nó anterior ao nó que será removido
		noAnterior.setProximoNo(noPivo.getProximoNo()); // atualiza a referência do nó anterior para apontar para o
														// próximo nó
		return noPivo.getConteudo(); // retorna o conteúdo do nó removido
	}

	@SuppressWarnings("unchecked")
	public int size() {
		int tamanhoLista = 0; // inicializa o contador de tamanho com zero
		No<T> referenciaAux = referenciaEntrada; // cria uma referência auxiliar para o primeiro nó da lista

		while (true) { // loop infinito
			if (referenciaAux != null) { // verifica se a referência auxiliar não é nula
				tamanhoLista++; // incrementa o contador de tamanho
				if (referenciaAux.getProximoNo() != null) { // verifica se há um próximo nó na lista
					referenciaAux = (No<T>) referenciaAux.getProximoNo(); // atualiza a referência auxiliar para apontar
																			// para o próximo nó
				} else { // se não há próximo nó
					break; // interrompe o loop
				}
			} else { // se a referência auxiliar é nula
				break; // interrompe o loop
			}
		}

		return tamanhoLista; // retorna o tamanho da lista
	}

	private void validaIndice(int index) {
		if (index >= size()) {
			int ultimoIndice = size() - 1;
			throw new IndexOutOfBoundsException("Não existe conteúdo no índice " + index
					+ "desta lista. Esta lista só vai até o indice " + ultimoIndice);
		}
	}

	public boolean isEmpty() {
		return referenciaEntrada == null ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	// Método que retorna uma String representando a lista encadeada.
	public String toString() {
		String strRetorno = ""; // Inicializa a String de retorno
		No<T> noAuxiliar = referenciaEntrada; // Cria uma referência ao primeiro nó da lista

		// Percorre a lista encadeada, concatenando cada elemento com a String de
		// retorno
		for (int i = 0; i < this.size(); i++) {
			strRetorno += "No{conteudo=" + noAuxiliar.getConteudo() + "}----->"; // Concatena o conteúdo do nó atual com
																					// a String de retorno
			noAuxiliar = (No<T>) noAuxiliar.getProximoNo(); // Atualiza a referência para o próximo nó da lista
		}

		strRetorno += "null"; // Concatena "null" ao final da String de retorno para indicar que não há mais
								// elementos na lista
		return strRetorno; // Retorna a String de retorno com a representação da lista encadeada
	}

}
