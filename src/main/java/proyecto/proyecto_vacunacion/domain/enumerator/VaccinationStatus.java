package proyecto.proyecto_vacunacion.domain.enumerator;

public enum VaccinationStatus {
    VACUNADO("V"), NO_VACUNADO("NV");

    private String codigo;

    private VaccinationStatus(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
