package la.foton.treinamento.entities;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ContaPoupanca")
public class ContaPoupanca extends Conta {

    @Column(name = "diaAniversario")
    private int diaAniversario;

    public ContaPoupanca() {
        tipo = TipoDaConta.POUPANCA;
    }

    public int getDiaAniversario() {
        return diaAniversario;
    }

    public void setDiaAniversario(int diaAniversario) {
        this.diaAniversario = diaAniversario;
    }

    @Override
    public void debita(double valor) throws NegocioException {
        if (valor > saldo)
            throw new NegocioException(Mensagem.SALDO_INSUFICIENTE);
        saldo -= valor;
    }
}
