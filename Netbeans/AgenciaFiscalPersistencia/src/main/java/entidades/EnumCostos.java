package entidades;

/**
 *
 * @author delll
 */
public enum EnumCostos {
    UN_ANIO_NORMAL(600),
    UN_ANIO_DISCAPACITADOS(200),
    DOS_ANIOS_NORMAL(900),
    DOS_ANIOS_DISCAPACITADOS(500),
    TRES_ANIOS_NORMAL(1100),
    TRES_ANIOS_DISCAPACITADOS(700);

    private final int costo;

    EnumCostos(int costo) {
        this.costo = costo;
    }

    public int getCosto() {
        return costo;
    }
}
