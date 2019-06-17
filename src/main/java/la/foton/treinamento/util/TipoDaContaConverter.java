package la.foton.treinamento.util;

import la.foton.treinamento.entities.TipoDaConta;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoDaContaConverter implements AttributeConverter<TipoDaConta, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoDaConta tipoDaConta) {
        if (tipoDaConta.getChave() == null) {
            throw new IllegalArgumentException("Unknown " + tipoDaConta);
        }
        return tipoDaConta.getChave();
    }

    @Override
    public TipoDaConta convertToEntityAttribute(Integer integer) {
        TipoDaConta tipoDaConta = TipoDaConta.get(integer);
        if (tipoDaConta == null) {
            throw new IllegalArgumentException("Unknown " + integer);
        }
        return tipoDaConta;
    }
}
