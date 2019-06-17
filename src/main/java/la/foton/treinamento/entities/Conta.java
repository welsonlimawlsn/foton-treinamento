package la.foton.treinamento.entities;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;
import la.foton.treinamento.util.TipoDaContaConverter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Conta {

    @Id
    private Integer numero;

    @Column
    private Integer agencia;

    @Column
    protected Double saldo;

    @ManyToOne
    @JoinColumn(name = "cpf")
    private Cliente titular;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private EstadoDaConta estado;

    @Column
    @Convert(converter = TipoDaContaConverter.class)
    protected TipoDaConta tipo;

    @PrePersist
    public void prePersist() {
        this.agencia = 1234;
    }

    public Conta() {
        estado = EstadoDaConta.ATIVA;
        saldo = 0.0;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public EstadoDaConta getEstado() {
        return estado;
    }

    public TipoDaConta getTipo() {
        return tipo;
    }

    public void credita(double valor) {
        saldo += valor;
    }

    public abstract void debita(double valor) throws NegocioException;

    public void transfere(Conta contaCredita, double valor) throws NegocioException {
        this.debita(valor);
        contaCredita.credita(valor);
    }

    public void encerra() throws NegocioException {
        if (estado == EstadoDaConta.ENCERRADA) {
            throw new NegocioException(Mensagem.CONTA_JA_ENCERRADA);
        }
        if (saldo > 0) {
            throw new NegocioException(Mensagem.CONTA_POSSUI_SALDO);
        }
        estado = EstadoDaConta.ENCERRADA;
    }
}
