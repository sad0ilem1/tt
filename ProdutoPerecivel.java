import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Produto perecível (gênero alimentício), que possui data de validade.
 * Não pode ser cadastrado com data de validade anterior ao dia atual e não
 * pode ter seu valor de venda solicitado se já estiver fora da validade.
 * Recebe 25% de desconto no valor de venda quando faltarem 7 dias ou menos
 * para o vencimento.
 */
public class ProdutoPerecivel extends Produto {

    /** Desconto concedido quando o produto está próximo do vencimento: 25% */
    private static final double DESCONTO = 0.25;

    /** Prazo (em dias) a partir do qual o desconto passa a ser aplicado: 7 dias */
    private static final int PRAZO_DESCONTO = 7;

    /** Data de validade do produto */
    private LocalDate dataDeValidade;

    /**
     * Construtor completo. Causa exceção em caso de valores inválidos.
     * @param descricao Descrição do produto (mínimo 3 caracteres)
     * @param precoCusto Preço de compra do produto (mínimo 0.01)
     * @param margemLucro Margem de lucro para a venda (mínimo 0.01)
     * @param dataDeValidade Data de validade do produto (não pode ser anterior a hoje)
     * @throws IllegalArgumentException em caso dos limites acima serem desrespeitados.
     */
    public ProdutoPerecivel(String descricao, double precoCusto, double margemLucro, LocalDate dataDeValidade) {
        super(descricao, precoCusto, margemLucro);
        if (dataDeValidade == null || dataDeValidade.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Data de validade não pode ser anterior ao dia de hoje.");

        this.dataDeValidade = dataDeValidade;
    }

    /**
     * {@inheritDoc}
     * Aplica desconto de 25% quando faltarem 7 dias ou menos para o vencimento.
     * @throws IllegalStateException se o produto já estiver fora da validade.
     */
    @Override
    public double valorDeVenda() {
        if (dataDeValidade.isBefore(LocalDate.now()))
            throw new IllegalStateException("Produto fora da validade, venda não permitida.");

        double valor = precoCusto * (1 + margemLucro);

        long diasParaVencer = ChronoUnit.DAYS.between(LocalDate.now(), dataDeValidade);
        if (diasParaVencer <= PRAZO_DESCONTO)
            valor *= (1 - DESCONTO);

        return valor;
    }

    /**
     * Descrição em string do produto, incluindo aviso quando está próximo do
     * vencimento e, portanto, com o valor de venda promocional.
     * @return String no formato "[DESCRIÇÃO]: R$ [VALOR DE VENDA]", com
     *         sinalização adicional em caso de desconto por proximidade do vencimento.
     */
    @Override
    public String toString() {
        String base = super.toString();

        long diasParaVencer = ChronoUnit.DAYS.between(LocalDate.now(), dataDeValidade);
        if (diasParaVencer <= PRAZO_DESCONTO)
            base += " (promoção: produto próximo do vencimento)";

        return base;
    }
}
