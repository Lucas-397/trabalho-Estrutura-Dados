package arvore;

import java.util.Scanner;

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


	public void inserir(){
		Scanner scanner = new Scanner(System.in);
		boolean validation = false;

		Item item = new Item("", 0, 0, 0, "");

		while(!validation){
			System.out.println("Digite o nome do produto: ");
			String nomeProduto = scanner.nextLine();

			if(!nomeProduto.isEmpty()){
				item.setNomeProduto(nomeProduto);
				validation = true;
			}else{
				System.out.println("Nome do produto não pode ser vazio");
			}
		}

		validation = false;

		while(!validation){
			System.out.println("Digite o codigo de barras do produto: ");
			
			if(scanner.hasNextInt()){
				item.setCodigoBarras(scanner.nextInt());
				validation = true;
			}else{
				System.out.println("Codigo de barras invalido, digite um numero inteiro");
				scanner.nextLine();
			}
		}

		validation = false;

		while(!validation){
			System.out.println("Digite o preco unitario do produto: ");

			if(scanner.hasNextFloat()){
				item.setPrecoUnitario(scanner.nextFloat());
				validation = true;
			}else{
				System.out.println("Preço unitario invalido");
				scanner.nextLine();
			}
		}

		validation = false;

		while(!validation){
			System.out.println("Digite a quantidade em estoque do produto: ");
			
			if(scanner.hasNextInt()){
				item.setQuantidadeEstoque(scanner.nextInt());
				validation = true;
			}else{
				System.out.println("Quantidade em estoque invalido, digite um numeor inteiro");
				scanner.nextLine();
			}

		}

		validation = false;
		scanner.nextLine();

		while (!validation) {
			System.out.println("Digite a categoria do produto: ");
			String categoria = scanner.nextLine();

			if(!categoria.isEmpty()){
				item.setCategoria(categoria);
				validation = true;
			}else{
				System.out.println("Categoria não pode ser vazia");
			}
		}
		
		inserir(item);
	}

	//inserir um novo n� na arvore. Sempre insere em um atributo que seja igual a null
	public boolean inserir (Item elem){
		if (pesquisar (elem.getChave())){
			System.out.println("Produto ja cadastrada, tente novamente");
			return false;
		}else{
			if( pesquisar(String.valueOf(elem.getCodigoBarras()))){
				System.out.println("Codigo de barras ja cadastrado, tente novamente");
				return false;
			}else{
				this.raiz = inserir (elem, this.raiz);
				this.quantNos++;
				System.out.println("Produto cadastrado com sucesso");
				return true;
			}
		}
	}
	public NoArv inserir (Item elem, NoArv no){
		if (no == null){
			NoArv novo = new NoArv(elem);
			return novo;
		}else {
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
		NoArv itemSupermercado = this.pesquisarItemSupermercado(nomeProduto, this.raiz);
		if(itemSupermercado != null){
			this.produtoToString(itemSupermercado.getInfo());
			return;
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
				System.out.println("Produto encontado");
				return arv;
			}
		}
		System.out.println(" Produto " + nomeProduto + " não foi encontado");
		return arv;
	}
	
	//2.c)

	//exibe a quantidade de produtos por categoria	
	public void mostraQuantidadeItemCategoria(){
		ContagemCategoria[] listaInformacoes = contarItensCategoria();//executa pesquisa de categorias
		
		for(int i = 0; i < listaInformacoes.length; i++){//loop de exibição
			if(listaInformacoes[i] != null){
				System.out.println(listaInformacoes[i].getQuantidadeProdutos() +  " produtos em " + listaInformacoes[i].getCategoria());
			}else{
				break; // se a categoria for nula, sai do loop
			}
		}
	}

	public ContagemCategoria[] contarItensCategoria(){
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
	public boolean removerProduto (String chave){
		//confere se o produto procurado existe e executa a função caso sim          
		if (pesquisar (chave, this.raiz) != null){
			this.raiz = removerProduto (chave, this.raiz);
			this.quantNos--;
			return true;
		}
		else {
			return false;
		}
	}
	public NoArv removerProduto (String chave, NoArv arv){
		//percorre a lista buscando o produto pela chave
		if (chave.compareTo(arv.getInfo().getChave()) < 0){
			arv.setEsq(removerProduto (chave, arv.getEsq()));
		}else{
			if (chave.compareTo( arv.getInfo().getChave()) > 0){
				arv.setDir(removerProduto (chave, arv.getDir()));
			}else{
				//apos encontrar o produto (não é maior nem menor que ou seja é igual) confere se o produto é um no folha ou não folha
				//caso o produto seja um no não folha pega o confere o grau do no 
				if (arv.getDir()== null){
					// se o produto tiver grau 1 retorna o no filho
					return arv.getEsq();
				}else{
					if (arv.getEsq() == null){ 
						return arv.getDir();
					}else{
						//se o produto tem grau 2 percorre a arvore buscando o maior elemento a esquerda 
						arv.setEsq(substituirNoGrauDois(arv, arv.getEsq()));
					}
				}
			}
		}
		return arv;
	}

	public NoArv substituirNoGrauDois(NoArv arv, NoArv produto){
		//enquanto houver Nos a direita (com chave de valor maior que o atual) percorre este caminho
		if (produto.getDir() != null){
			produto.setDir(Arrumar (arv, produto.getDir()));
		}
		else{//define o maior elemento como o info do no a ser apagado
			arv.setInfo(produto.getInfo());
			produto = produto.getEsq();
		}
		return produto;
	}

//2.g)
	public void changeProdutoData(String chave){
		NoArv produto = this.pesquisar(chave, raiz);
		if(produto != null){
			Scanner scanner = new Scanner(System.in);
			boolean continuar = false;
			
			//garante que o valor digitado é um float
			while (!continuar) {
				System.out.println("Preço unitário atual: " + produto.getInfo().getPrecoUnitario());
				System.out.println("Digite o novo preço unitário: ");
				
				if (scanner.hasNextFloat()) {
					float precoUnitario = scanner.nextFloat();
					produto.getInfo().setPrecoUnitario(precoUnitario);
					continuar = true; // sai do loop
				} else {
					System.out.println("Valor inválido! Digite um número decimal (ex: 10.5).");
					scanner.next(); // consome a entrada inválida para não travar o loop
				}
			}
			//reinicia a variavel continuar e o scanner
			continuar = false;

			//garante que o valor digitado é um inteiro
			while(!continuar){
				System.out.println("Quantidade em estoque atual: " + produto.getInfo().getQuantidadeEstoque());
				System.out.println("Digite a nova quantidade em estoque: ");
				if(scanner.hasNextInt()){
					produto.getInfo().setQuantidadeEstoque(scanner.nextInt());
					continuar = true; // sai do loop
				}else{
					System.out.println("Valor inválido! Digite um número inteiro.");
					scanner.next(); // consome a entrada inválida para não travar o loop
				}
			}

			continuar = false;
			String categoria;

			while(!continuar) {
				System.out.println("Categoria atual: " + produto.getInfo().getCategoria());
				System.out.println("Digite a nova categoria: ");
				categoria = scanner.nextLine();

				if (!categoria.trim().isEmpty()) {
				produto.getInfo().setCategoria(categoria.trim());
				continuar = true; // Sai do loop
				} else {
					System.out.println("Categoria inválida! Digite uma categoria não vazia.");
				}
			}
		}	
	}

	public void produtoToString(Item produto){
		System.out.println("Nome do Produto: " + produto.getChave());
		System.out.println("Codigo de Barras: " + produto.getCodigoBarras());
		System.out.println("Preco Unitario: R$" + produto.getPrecoUnitario());
		System.out.println("Quantidade em Estoque: " + produto.getQuantidadeEstoque());
		System.out.println("Categoria: " + produto.getCategoria());
		System.out.println("-----------------------------");
	}
}
