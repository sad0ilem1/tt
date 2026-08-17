/**
 * Produto que não possui data de validade. O valor de venda é calculado
 * exatamente como na regra original: preço de custo + margem de lucro.
 */
public class ProdutoNaoPerecivel extends Produto {

    /**
     * Construtor completo. Causa exceção em caso de valores inválidos.
     * @param descricao Descrição do produto (mínimo 3 caracteres)
     * @param precoCusto Preço de compra do produto (mínimo 0.01)
     * @param margemLucro Margem de lucro para a venda (mínimo 0.01)
     * @throws IllegalArgumentException em caso dos limites acima serem desrespeitados.
     */
    public ProdutoNaoPerecivel(String descricao, double precoCusto, double margemLucro) {
        super(descricao, precoCusto, margemLucro);
    }

    /**
     * Construtor com margem de lucro padrão (20%). Causa exceção em caso de valores inválidos.
     * @param descricao Descrição do produto (mínimo 3 caracteres)
     * @param precoCusto Preço de compra do produto (mínimo 0.01)
     * @throws IllegalArgumentException em caso dos limites acima serem desrespeitados.
     */
    public ProdutoNaoPerecivel(String descricao, double precoCusto) {
        super(descricao, precoCusto);
    }

    /**
     * {@inheritDoc}
     * Não há desconto: valor = preço de custo * (1 + margem de lucro).
     */
    @Override
    public double valorDeVenda() {
        return precoCusto * (1 + margemLucro);
    }
}
