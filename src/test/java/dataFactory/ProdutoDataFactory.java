package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComValorIgualA(double valor){
        ProdutoPojo produto= new ProdutoPojo();
        produto.setProdutoNome("PS5");
        produto.setProdutoValor(valor);
        List<String> cores = new ArrayList<>();
        cores.add("rosa");
        cores.add("preto");
        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");
        List<ComponentePojo> componentes = new ArrayList<>();
        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("controel");
        componente.setComponenteQuantidade(1);
        componentes.add(componente);

        ComponentePojo segundocomp = new ComponentePojo();
        segundocomp.setComponenteNome("CDjogo");
        segundocomp.setComponenteQuantidade(1);
        componentes.add(segundocomp);

        produto.setComponentes(componentes);

        return produto;
    }
}
