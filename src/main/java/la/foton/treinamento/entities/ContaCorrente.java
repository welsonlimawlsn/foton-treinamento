package la.foton.treinamento.entities;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

public class ContaCorrente extends Conta {

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
