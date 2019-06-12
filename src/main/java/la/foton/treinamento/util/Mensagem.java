package la.foton.treinamento.util;

public enum Mensagem {
    SALDO_INSUFICIENTE("Saldo insuficiente"),
    CONTA_JA_ENCERRADA("Conta já encerrada"),
    CLIENTE_SITUACAO_PENDENTE("Cliente com situação pendente"),
    CONTA_POSSUI_SALDO("Conta possui saldo"),
    CONTA_NAO_ENCONTRADA("Conta não encotrada");

    private String descricao;

    Mensagem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return null;
    }
}
