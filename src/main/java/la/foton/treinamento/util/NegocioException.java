package la.foton.treinamento.util;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NegocioException extends Exception {

    private final Mensagem mensagem;

    public NegocioException(Mensagem mensagem) {
        super(mensagem.getDescricao());
        this.mensagem = mensagem;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }
}
