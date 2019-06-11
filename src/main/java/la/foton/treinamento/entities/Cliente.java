package la.foton.treinamento.entities;

public class Cliente {

    private String cpf;
    private String nome;
    private SituacaoDoCliente situacao;

    public Cliente() {
        situacao = SituacaoDoCliente.PENDENTE;
    }

    public SituacaoDoCliente getSituacao() {
        return situacao;
    }

    public void ativa() {
        this.situacao = SituacaoDoCliente.ATIVO;
    }

}
