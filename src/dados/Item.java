package dados;

public class Item {
	private String nomeProduto;
	private int codigoBarras;
	private float precoUnitario;
	private int quantidadeEstoque;
	private String categoria;
	
	public Item(String nomeProduto, int codigoBarras, float precoUnitario, int quantidadeEstoque, String categoria) {
		this.nomeProduto = nomeProduto;
		this.codigoBarras = codigoBarras;
		this.precoUnitario = precoUnitario;
		this.quantidadeEstoque = quantidadeEstoque;
		this.categoria = categoria;
	}

	public int getCodigoBarras() {
		return codigoBarras;
	}
	public float getPrecoUnitario() {
		return precoUnitario;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public void setPrecoUnitario(float precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCategoria() {
		return categoria;
	}
	// aqui podem ser declarados outros atributos conforme sua necessidade.

	//Construtor de objetos da classe Item
	//Modifica o valor do atributo chave
	public void setNomeProduto (String ch){
		this.nomeProduto = ch;
	}
	//Faz a leitura do valor do atributo chave
	public String getNomeProduto (){
		return this.nomeProduto;
	}
}