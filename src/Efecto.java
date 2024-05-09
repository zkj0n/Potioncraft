public enum Efecto {
    INMUNIDAD(1),
    FORTALECIMIENTO(1),
    SANACION(1),
    SUERTE(1),
    SUENO(-1),
    INTOXICACION(-1),
    CONGELACION(-1),
    FURIA(-1),
    SIN_EFECTO(0);

    private final int valor;

    Efecto(int valor) {
        this.valor = valor;
    }

    public int obtenerValor() {
        return valor;
    }
}
