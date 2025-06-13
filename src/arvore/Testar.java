package arvore;

import dados.Item;

class Testar{
    public static void main(String[] args){
        Arvore arvore = new Arvore();

        arvore.inserir(new Item("Produto A", 123456, 10.0f, 100, "Categoria 1"));
        arvore.inserir(new Item("Produto B", 123457, 10.0f, 10, "Categoria 1"));
        arvore.inserir(new Item("Produto C", 123458, 10.0f, 11, "Categoria 2"));
        arvore.inserir(new Item("Produto D", 123459, 10.0f, 12, "Categoria 2"));
        arvore.inserir(new Item("Produto E", 123450, 10.0f, 13, "Categoria 5"));
        arvore.inserir(new Item("Produto F", 123451, 10.0f, 14, "Categoria 6"));
        arvore.inserir(new Item("Produto B", 123452, 10.0f, 1, "Categoria 1"));


        Item[] itens = new Item[10];
        itens = arvore.CamPosFixado();

        for(int i = 0; i < itens.length; i++){
            if(itens[i] != null){
                System.out.println("nome produto: " + itens[i].getChave() + ", Estoque: " + itens[i].getCodigoBarras());
            }
        }

        arvore.mostrarItemSupermercado("Produto z");
        arvore.mostraQuantidadeItemCategoria();
    }
}