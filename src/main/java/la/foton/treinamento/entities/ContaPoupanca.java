package la.foton.treinamento.entities;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

public class ContaPoupanca extends Conta {

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
