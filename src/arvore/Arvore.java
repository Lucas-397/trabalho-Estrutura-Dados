package arvore;

import dados.ContagemCategoria;
import dados.Item;

public class Arvore {
	private NoArv raiz;
	private int quantNos;//opcional

	public Arvore(){
		this.quantNos=0;
		this.raiz = null;
	}
	public boolean eVazia (){
		return (this.raiz == null);
	}
	public NoArv getRaiz(){
		return this.raiz;
	}
	public int getQuantNos(){
		return this.quantNos;
	}
	//inserir um novo n� na arvore. Sempre insere em um atributo que seja igual a null
	public boolean inserir (Item elem){
		if (pesquisar (elem.getChave()) || pesquisar(String.valueOf(elem.getCodigoBarras()))){
			return false;
		}else{
			this.raiz = inserir (elem, this.raiz);
			this.quantNos++;
			return true;
		}
	}
	public NoArv inserir (Item elem, NoArv no){
		if (no == null){
			NoArv novo = new NoArv(elem);
			return novo;
		}else {
			//if (elem.getChave() < no.getInfo().getChave()){
			if(elem.getChave().compareTo(no.getInfo().getChave()) < 0){
				no.setEsq(inserir(elem, no.getEsq()));
				return no;
			}else{
				no.setDir(inserir(elem, no.getDir()));
				return no;
			}
		}
	}
	//Pesquisa se um determinado valor est� na �rvore
	public boolean pesquisar (String chave){
		if (pesquisar (chave, this.raiz) != null){
			return true;
		}else{
			return false;
		}
	}
	private NoArv pesquisar (String chave, NoArv no){

		if (no != null){
			String noArv = no.getInfo().getChave();
			if (chave.compareTo(noArv) < 0){
				no = pesquisar (chave, no.getEsq());
			}else{
				if (chave.compareTo(noArv) > 0){
					no = pesquisar (chave, no.getDir());
				}
			}
		}
		return no;
	}

//remove um determinado n� procurando pela chave. O n� pode estar em qualquer
//posi��o na �rvore
	public boolean remover (String chave){
		if (pesquisar (chave, this.raiz) != null){
			this.raiz = remover (chave, this.raiz);
			this.quantNos--;
			return true;
		}
		else {
			return false;
		}
	}
	public NoArv remover (String chave, NoArv arv){
		if (chave.compareTo(arv.getInfo().getChave()) < 0){
			arv.setEsq(remover (chave, arv.getEsq()));
		}else{
			if (chave.compareTo( arv.getInfo().getChave()) > 0){
				arv.setDir(remover (chave, arv.getDir()));
			}else{
				if (arv.getDir()== null){
					return arv.getEsq();
				}else{
					if (arv.getEsq() == null){ 
						return arv.getDir();
					}else{
						arv.setEsq(Arrumar (arv, arv.getEsq()));
					}
				}
			}
		}
		return arv;
	}
	private NoArv Arrumar (NoArv arv, NoArv maior){
		if (maior.getDir() != null){
			maior.setDir(Arrumar (arv, maior.getDir()));
		}
		else{
			arv.setInfo(maior.getInfo());
			maior = maior.getEsq();
		}
		return maior;
	}
	//caminhamento central
	public Item [] CamCentral (){
		int []n= new int[1];
		n[0]=0;
		Item [] vet = new Item[this.quantNos];
		return (FazCamCentral (this.raiz, vet, n));
	}
	private Item [] FazCamCentral (NoArv arv, Item [] vet, int []n){
		if (arv != null) {
			vet = FazCamCentral (arv.getEsq(),vet,n);
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = FazCamCentral (arv.getDir(),vet,n);
		}
		return vet;
	}
	//caminhamento pr�-fixado
	public Item [] CamPreFixado (){
		int []n= new int[1];
		n[0]=0;
		Item [] vet = new Item[this.quantNos];
		return (FazCamPreFixado (this.raiz, vet, n));
	}
	private Item [] FazCamPreFixado (NoArv arv, Item [] vet, int []n){
		if (arv != null) {
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = FazCamPreFixado (arv.getEsq(), vet,n);
			vet = FazCamPreFixado (arv.getDir(), vet,n);
		}
		return vet;
	}
	//caminhamento p�s-fixado
	public Item [] CamPosFixado (){
		int []n= new int[1];
		n[0]=0;
		Item [] vet = new Item[this.quantNos];
		return (FazCamPosFixado (this.raiz, vet, n));
	}
	private Item [] FazCamPosFixado (NoArv arv, Item[] vet, int []n){
		if (arv != null) {
			vet = FazCamPosFixado (arv.getEsq(), vet,n);
			vet = FazCamPosFixado (arv.getDir(), vet,n);
			vet[n[0]] = arv.getInfo();
			n[0]++;
		}
		return vet;
	}

	//A partir daqui somente meus codigos

	//funcao para verificar se o codigo de barras existes antes de inserir na arvore
	//2.a)
	public boolean pesquisarCodigoBarras (int codigoBarras){
		Item[]  itens = this.CamPreFixado();
		for (Item item : itens) {
			if(item.getCodigoBarras() == codigoBarras) {
				return true;
			}
		}
		return false;
	}
	
	//2.b)
	public void mostrarItemSupermercado(String nomeProduto){
		Item itemSupermercado = this.pesquisarItemSupermercado(nomeProduto, this.raiz).getInfo();
		if(itemSupermercado.getChave() != raiz.getInfo().getChave()){
			System.out.println("Produto encontado");
			this.produtoToString(itemSupermercado);
		}else{
			System.out.println(" Produto " + nomeProduto + " não foi encontado");
		}

	}

	
	public NoArv pesquisarItemSupermercado(String nomeProduto, NoArv arv){
		if(this.pesquisar(nomeProduto)){
			if(arv.getInfo().getChave() != nomeProduto){
				//percorre a arvore atras do item
				if(nomeProduto.compareTo(arv.getInfo().getChave()) > 0){
					arv = pesquisarItemSupermercado(nomeProduto, arv.getDir());
				}else if(nomeProduto.compareTo(arv.getInfo().getChave()) < 0){
					arv = pesquisarItemSupermercado(nomeProduto, arv.getEsq());
				}
			}else{
				return arv;
			}
		}
		return arv;
	}
	
	//2.c)

	//exibe a quantidade de produtos por categoria	
	public void mostraQuantidadeItemCategoria(){
		ContagemCategoria[] listaInformacoes = contarItenCategoria();
		for(int i = 0; i < listaInformacoes.length; i++){
			if(listaInformacoes[i] != null){
				System.out.println(listaInformacoes[i].getQuantidadeProdutos() +  " produtos em " + listaInformacoes[i].getCategoria());
			}
		}
	}

	public ContagemCategoria[] contarItenCategoria(){
		Item[] produtos = this.CamPosFixado();
		
		int[] quantidadeProdutos = new int[this.quantNos];
		String[] categorias = new String[this.quantNos];
		int categoriasIndex = 0;

		for(Item produto: produtos){
			//confere se a categoria do produto já esta na lista de categorias caso nao retorna a categoria do item
			String  categoriaItem = searchCategoriaProduto(categorias, produto);

			//adiciona categoria do item caso a categoria retornada seja nula
			if(categoriaItem ==  null){
				categorias[categoriasIndex] = produto.getCategoria();
				quantidadeProdutos[categoriasIndex]++;
				categoriasIndex++;
			}else{
				//encontra a qual categoria o item pertence e aumenta a quantidade para um
				for(int i = 0; i < categorias.length; i++){
					if(categorias[i] != null){
						if(categorias[i].equals(categoriaItem)){
							quantidadeProdutos[i]++;
						}
					}
				}
			}
		}

		System.out.println(quantidadeProdutos[0]);
		return montaListaContagemCategoria(categorias, quantidadeProdutos);
	}

	// pesquisa a qual categoria o produto pertence
	public String searchCategoriaProduto(String[] categorias, Item produto){
		for(String categoria: categorias){
			if(categoria != null){
				if(categoria.equals(produto.getCategoria())){
					return categoria;
				}
			}
		}
		return null;
	}
	
	// monta a lista de categorias utilizando o tipo ContagemCategoria e retorna-o (feito para facilitar a leitura)
	public ContagemCategoria[] montaListaContagemCategoria(String[] categorias, int[] quantidadeProdutos){
		ContagemCategoria[] listaInformacoes = new ContagemCategoria[this.quantNos];
		
		for(int i = 0; i < listaInformacoes.length; i++){
			if(categorias[i] != null){
				listaInformacoes[i] = new ContagemCategoria(categorias[i], quantidadeProdutos[i]);
			}
		}
		System.out.println(listaInformacoes[5]);
		return listaInformacoes;
	}

	//2.d)
	//exibe os produtos que estão no intervalo de preço
	public void mostrarProdutosIntervalo(int minimo, int maximo){
		Item[] produtos = this.filtraProdutosPreco(minimo, maximo);

		if(produtos[0] != null){
			System.out.println("Produtos encontrados no intervalo de " + minimo + " a " + maximo + ":");
			System.out.println("-----------------------------");	
			for(Item produto: produtos){
				if(produto != null) {
					this.produtoToString(produto);
				}
			}
		}else{
			System.out.println("Não há produtos no intervalo de " + minimo + " a " + maximo);
		}
	}

	// filtra os produtos que estão no intervalo de preço
	public Item[] filtraProdutosPreco(int minimo, int maximo){
		Item[] produtosIntervalo = new Item[this.quantNos];
		int produtosIndex = 0;
		Item[] produtos = this.CamPosFixado();
		for(Item produto : produtos){
			if(produto.getPrecoUnitario() <= maximo && produto.getPrecoUnitario() >= minimo){
				produtosIntervalo[produtosIndex] = produto;
				produtosIndex++;
			}
		}

		return produtosIntervalo;
	}

	//2.e)

	public void exibeMenorPreco(){
		float menorPreco = this.searchMenorPreco(this.raiz.getInfo().getPrecoUnitario(), this.raiz);
		Item produtoMenorPreco = this.pesquisarByPrecoUnitario(menorPreco);
		if(produtoMenorPreco != null){
			System.out.println("O produto de menor preco custa: R$ " + menorPreco);
			System.out.println("-----------------------------");
			this.produtoToString(produtoMenorPreco);
		}
	}

	//pesquisa o menor produto pelo precoUnitario
	public float searchMenorPreco(float menorPreco,NoArv arv){
		if(arv != null){
			menorPreco = searchMenorPreco(menorPreco, arv.getEsq());
			if(arv.getInfo().getPrecoUnitario() < menorPreco){
				menorPreco = arv.getInfo().getPrecoUnitario();
			}
			menorPreco = searchMenorPreco(menorPreco, arv.getDir());
		}

		return menorPreco;
	}
	public void exibeMaiorPreco(){
		float maiorPreco = this.searchMenorPreco(this.raiz.getInfo().getPrecoUnitario(), this.raiz);
		Item produtoMaiorPreco = this.pesquisarByPrecoUnitario(maiorPreco);

		if(produtoMaiorPreco != null){
			System.out.println("O produto de maior preco custa: R$ " + maiorPreco);
			System.out.println("-----------------------------");
			this.produtoToString(produtoMaiorPreco);
		}
	}

	//pesquisa o menor produto pelo precoUnitario
	public float searchMaiorPreco(float maiorPreco,NoArv arv){
		if(arv != null){
			maiorPreco = searchMenorPreco(maiorPreco, arv.getEsq());
			if(arv.getInfo().getPrecoUnitario() > maiorPreco){
				maiorPreco = arv.getInfo().getPrecoUnitario();
			}
			maiorPreco = searchMenorPreco(maiorPreco, arv.getDir());
		}

		return maiorPreco;
	}

	public Item pesquisarByPrecoUnitario(float precoUnitario){
		Item[] produtos = this.CamPosFixado();

		for(Item produto : produtos){
			if(produto.getPrecoUnitario() == precoUnitario){
				return produto;
			}
		}
		return null;
	}

	//2.f)

	public void produtoToString(Item produto){
		System.out.println("Nome do Produto: " + produto.getChave());
		System.out.println("Codigo de Barras: " + produto.getCodigoBarras());
		System.out.println("Preco Unitario: R$" + produto.getPrecoUnitario());
		System.out.println("Quantidade em Estoque: " + produto.getQuantidadeEstoque());
		System.out.println("Categoria: " + produto.getCategoria());
		System.out.println("-----------------------------");
	}
}
