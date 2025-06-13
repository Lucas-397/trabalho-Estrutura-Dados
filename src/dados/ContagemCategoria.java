package dados;

public class ContagemCategoria {
    private String Categoria;
    private int quantidadeProdutos;

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public int getQuantidadeProdutos() {
        return quantidadeProdutos;
    }

    public void setQuantidadeProdutos(int quantidadeProdutos) {
        this.quantidadeProdutos = quantidadeProdutos;
    }

    public ContagemCategoria(String categoria, int quantidadeProdutos) {
        Categoria = categoria;
        this.quantidadeProdutos = quantidadeProdutos;
    }
    
    
}
