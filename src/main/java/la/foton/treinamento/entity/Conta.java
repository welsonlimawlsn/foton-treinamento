package la.foton.treinamento.entity;

public class Conta {

    private int agencia;
    private int numero;
    private double saldo;
    private Cliente titular;
    private EstadoDaConta estado;

    public Conta() {
        estado = EstadoDaConta.ATIVA;
        saldo = 0;
    }

    public void credita(double valor) {
        saldo += valor;
    }

    public void debita(double valor) throws Exception {
        if (valor > saldo)
            throw new Exception("Saldo insuficiente");
        saldo -= valor;
    }

    public void transfere(Conta contaCredita, double valor) throws Exception {
        this.debita(valor);
        contaCredita.credita(valor);
    }

    public void encerra() throws Exception {
        if (estado == EstadoDaConta.ATIVA) {
            estado = EstadoDaConta.ENCERRADA;
        } else if (estado == EstadoDaConta.ENCERRADA) {
            throw new Exception("Conta já encerrada");
        }
        estado = EstadoDaConta.ENCERRADA;
    }

    public double getSaldo() {
        return saldo;
    }

    public EstadoDaConta getEstado() {
        return estado;
    }
}
