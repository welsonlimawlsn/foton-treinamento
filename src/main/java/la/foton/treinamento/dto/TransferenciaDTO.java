package la.foton.treinamento.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"numeroContaDebito", "valor", "numeroContaCredito"})
public class TransferenciaDTO {

    private Integer numeroContaDebito;
    private Double valor;
    private Integer numeroContaCredito;

    public Integer getNumeroContaDebito() {
        return numeroContaDebito;
    }

    public void setNumeroContaDebito(Integer numeroContaDebito) {
        this.numeroContaDebito = numeroContaDebito;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getNumeroContaCredito() {
        return numeroContaCredito;
    }

    public void setNumeroContaCredito(Integer numeroContaCredito) {
        this.numeroContaCredito = numeroContaCredito;
    }
}
