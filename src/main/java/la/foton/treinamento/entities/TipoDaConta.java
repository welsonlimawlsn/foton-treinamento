package la.foton.treinamento.entities;

public enum TipoDaConta {

    CORRENTE(1), POUPANCA(2);

    private Integer chave;

    TipoDaConta(Integer chave) {
        this.chave = chave;
    }

    public static TipoDaConta get(Integer chave) {
        for (TipoDaConta tipoDaConta : TipoDaConta.values()) {
            if (tipoDaConta.getChave().equals(chave)) {
                return tipoDaConta;
            }
        }
        return null;
    }

    public Integer getChave() {
        return chave;
    }
}
