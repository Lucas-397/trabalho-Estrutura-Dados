package dados;

public class ItemChar {
	private char chave;
	// aqui podem ser declarados outros atributos conforme sua necessidade.

	//Construtor de objetos da classe Item
	public ItemChar(char ch) {
		this.chave = ch;
	}
	//Modifica o valor do atributo chave
	public void setChave (char ch){
		this.chave = ch;
	}
	//Faz a leitura do valor do atributo chave
	public char getChave (){
		return this.chave;
	}
}
