package proyecto.proyecto_vacunacion.domain.enumerator;

public enum VaccineType {
    SPUTNIK("S"), ASTRA_ZENECA("AZ"), PFIZER("P"), JHONSON_AND_JHONSON("J&J");

    private String codigo;

    private VaccineType(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
