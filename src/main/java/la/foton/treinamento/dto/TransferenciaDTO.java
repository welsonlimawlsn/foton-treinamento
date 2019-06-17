package la.foton.treinamento.dto;

public class TransferenciaDTO {

    private Integer contaDebito;
    private Double valor;
    private Integer contaCredito;

    public Integer getContaDebito() {
        return contaDebito;
    }

    public void setContaDebito(Integer contaDebito) {
        this.contaDebito = contaDebito;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getContaCredito() {
        return contaCredito;
    }

    public void setContaCredito(Integer contaCredito) {
        this.contaCredito = contaCredito;
    }
}
