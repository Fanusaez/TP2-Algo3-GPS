package edu.fiuba.algo3.modelo;

public enum Constantes {

    PENALIZACIONDEMOVIMIENTOSPORPOZOPARAMOTOYAUTO(3,1),
    PENALIZACIONDEMOVIMIENTOSPORPOZOPARATODOTERRENO(2,2),
    CANTIDADEPOZOSPARAAPLICARPENALIZACIONTODOTERRENO(3,3),
    PENALIZACIONDEMOVIMIENTOSPORPIQUETEPARAMOTO(2,4),
    PENALIZACIONDEMOVIMIENTOSPORPIQUETEPARAAUTOYTODOTERRENO(0,5),
    PENALIZACIONDEMOVIMIENTOSPORCONTROLPOLICIAL(3,6),
    PENALIZACIONDEMOVIMIENTOSPORCONTROLPOLICIALESQUIVADO(0,7),
    PROBABILIDADDEPENALIZACIONENCONTROLPOLICIALPARAMOTO(0.8,8),
    PROBABILIDADDEPENALIZACIONENCONTROLPOLICIALPARAAUTO(0.5,9),
    PROBABILIDADDEPENALIZACIONENCONTROLPOLICIALPARATODOTERRENO(0.3,10),
    FACTORDEMODIFICACIONDEMOVIMIENTOSPORSORPRESAFAVORABLE(0.8,11),
    FACTORDEMODIFICACIONDEMOVIMIENTOSPORSORPRESADESFAVORABLE(1.25, 12),
    VALORDEINCREMENTODEPOSICION(1,13),
    PROBABILIDADDEOBTENERUNAMOTO(0.33,14),
    PROBABILIDADDEOBTENERUNAUTO(0.66,15),
    CANTIDADMINIMADEESQUINASPOREJE(10,16),
    CANTIDADMAXIMADEESQUINASPOREJE(15,17),
    CANTIDADDESORPRESASPORPOSICIONVALIDA(0.0415,18),
    CANTIDADDEPOLICIASPORPOSICIONVALIDA(0.0415,19),
    CANTIDADDEPOZOSPORPOSICIONVALIDA(0.0311,20),
    CANTIDADDEPIQUETESPORPOSICIONVALIDA(0.0249,21),
    PORCENTAJEDEACCIONABLESENEJEX(0.5,22),
    POSINIENXDEVEHICULO(0,23),
    CANTIPOSDESORPRESAS(3,24);

    private double valor;

    private Constantes(double valor, int id) {
        this.valor = valor;
    }

    private Constantes(int valor, int id) {
        this.valor = valor;
    }

    public double obtenerValorConstante(int id) {
        Constantes[] constantes = Constantes.values();
        return constantes[id].valor;
    }
}
