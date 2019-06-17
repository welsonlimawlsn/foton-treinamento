package la.foton.treinamento.entities;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ContaCorrente")
public class ContaCorrente extends Conta {

    @Column(name = "limiteChequeEspecial")
    private double limiteChequeEspecial;

    public ContaCorrente() {
        tipo = TipoDaConta.CORRENTE;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    @Override
    public void debita(double valor) throws NegocioException {
        if (valor > saldo + limiteChequeEspecial)
            throw new NegocioException(Mensagem.SALDO_INSUFICIENTE);
        saldo -= valor;
    }


}
