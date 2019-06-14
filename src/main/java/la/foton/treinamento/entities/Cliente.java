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
        this();
        this.cpf = cpf;
        this.nome = nome;
    }

    public void ativa() {
        this.situacao = SituacaoDoCliente.ATIVO;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SituacaoDoCliente getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoDoCliente situacao) {
        this.situacao = situacao;
    }
}
