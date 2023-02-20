package proyecto.proyecto_vacunacion.domain.transform;


import proyecto.proyecto_vacunacion.domain.enumerator.VaccineType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class VaccineTypeTransform implements AttributeConverter<VaccineType,String> {
    @Override
    public String convertToDatabaseColumn(VaccineType vaccineType) {
        if (vaccineType == null) {
            return null;
        }
        return vaccineType.getCodigo();
    }

    @Override
    public VaccineType convertToEntityAttribute(String codigo) {
        if (codigo == null) {
            return null;
        }

        return Stream.of(VaccineType.values())
                .filter(c -> c.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
