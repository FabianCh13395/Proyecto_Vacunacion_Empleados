package proyecto.proyecto_vacunacion.domain.transform;


import proyecto.proyecto_vacunacion.domain.enumerator.VaccinationStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class VaccineStatusTrasform implements AttributeConverter<VaccinationStatus,String> {

    @Override
    public String convertToDatabaseColumn(VaccinationStatus vaccinationStatus) {
        if (vaccinationStatus == null) {
            return null;
        }
        return vaccinationStatus.getCodigo();
    }

    @Override
    public VaccinationStatus convertToEntityAttribute(String codigo) {
        if (codigo == null) {
            return null;
        }

        return Stream.of(VaccinationStatus.values())
                .filter(c -> c.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
