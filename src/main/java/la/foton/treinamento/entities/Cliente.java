package la.foton.treinamento.entities;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    private String cpf;

    @Column
    private String nome;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private SituacaoDoCliente situacao;

    public Cliente() {
        situacao = SituacaoDoCliente.PENDENTE;
    }

    public Cliente(String cpf, String nome) {
        super();
        this.cpf = cpf;
        this.nome = nome;
    }

    public void ativa() {
        this.situacao = SituacaoDoCliente.ATIVO;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public SituacaoDoCliente getSituacao() {
        return situacao;
    }
}
