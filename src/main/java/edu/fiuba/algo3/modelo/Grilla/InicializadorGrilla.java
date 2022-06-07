package edu.fiuba.algo3.modelo.Grilla;

import edu.fiuba.algo3.modelo.Accionable;
import edu.fiuba.algo3.modelo.Constantes;
import edu.fiuba.algo3.modelo.Obstaculo.ControlPolicial;
import edu.fiuba.algo3.modelo.Obstaculo.Piquete;
import edu.fiuba.algo3.modelo.Obstaculo.Pozo;
import edu.fiuba.algo3.modelo.Posicion;

import java.util.Random;

public class InicializadorGrilla {

    protected Grilla grilla;
    protected int cantSorpresas;
    protected int cantControlesPoliciales;
    protected int cantPozos;
    protected int cantPiquetes;
    protected Posicion posIniVehiculo;
    protected Posicion posMeta;

    private static Random RNG = new Random();


    public InicializadorGrilla(Grilla grilla){
        this.grilla = grilla;
        this.posIniVehiculo = new Posicion(Constantes.posIniEnXDeVehiculo,
                conseguirPosConMaxYMultiplo(this.grilla.obtenerTamanioEjeY(), 3));
        this.posMeta = new Posicion(grilla.obtenerTamanioEjeX(),
                InicializadorGrilla.conseguirPosConMaxYMultiplo(this.grilla.obtenerTamanioEjeY(), 3));
    }

    public void insertarAccionables(){
        this.calcularCantidadesDeAccionables();
        this.insertarAccionablesEnGrilla();
    }

    private void calcularCantidadesDeAccionables(){
        int cantPosVal = this.grilla.obtenerCantTotalDePosValidas();
        this.cantSorpresas = (int) (cantPosVal * Constantes.cantidadDeSorpresasPorPosicionValida);
        this.cantControlesPoliciales = (int) (cantPosVal * Constantes.cantidadDePoliciasPorPosicionValida);
        this.cantPozos = (int) (cantPosVal * Constantes.cantidadDePozosPorPosicionValida);
        this.cantPiquetes = (int) (cantPosVal * Constantes.cantidadDePiquetesPorPosicionValida);
    }

    private void insertarAccionablesEnGrilla(){
        this.insertarSorpresasEnGrilla();
        this.insertarControlesPolicialesEnGrilla();
        this.insertarPozosEnGrilla();
        this.insertarPiquetesEnGrilla();
    }

    private void insertarSorpresasEnGrilla(){
        // No lo hice por temas que deberiamos acordar en discord.
    }

    private void insertarControlesPolicialesEnGrilla(){
        ControlPolicial controlPolicial;
        int cantControlesPolicialesEjeX = (int) (this.cantControlesPoliciales * Constantes.porcentajeDeAccionablesEnEjeX);
        int cantControlesPolicialesEjeY = this.cantControlesPoliciales - cantControlesPolicialesEjeX;

        for (int i = 0; i < cantControlesPolicialesEjeX; i++){
            controlPolicial = new ControlPolicial();
            this.insertarAccionableEnEjeXDeGrilla(controlPolicial);
        }
        for (int j = 0; j < cantControlesPolicialesEjeY; j++){
            controlPolicial = new ControlPolicial();
            this.insertarAccionableEnEjeYDeGrilla(controlPolicial);
        }

    }

    private void insertarPozosEnGrilla(){
        Pozo pozo;
        int cantPozosEjeX = (int) (this.cantPozos * Constantes.porcentajeDeAccionablesEnEjeX);
        int cantPozosEjeY = this.cantPozos - cantPozosEjeX;

        for (int i = 0; i < cantPozosEjeX; i++){
            pozo = new Pozo();
            this.insertarAccionableEnEjeXDeGrilla(pozo);
        }
        for (int j = 0; j < cantPozosEjeY; j++){
            pozo = new Pozo();
            this.insertarAccionableEnEjeYDeGrilla(pozo);
        }
    }

    private void insertarPiquetesEnGrilla(){
        Piquete piquete;
        int cantPiquetesEjeX = (int) (this.cantPiquetes * Constantes.porcentajeDeAccionablesEnEjeX);
        int cantPiquetesY = this.cantPiquetes - cantPiquetesEjeX;

        for (int i = 0; i < cantPiquetesEjeX; i++){
            piquete = new Piquete();
            this.insertarAccionableEnEjeXDeGrilla(piquete);
        }
        for (int j = 0; j < cantPiquetesY; j++){
            piquete = new Piquete();
            this.insertarAccionableEnEjeYDeGrilla(piquete);
        }
    }

    private void insertarAccionableEnEjeXDeGrilla(Accionable accionable){
        Posicion posAInsertar;
        do {
            posAInsertar = this.conseguirAzarPosEnCalleHorizontal();
        } while (this.esPosYaOcupada(posAInsertar));

        this.grilla.insertarAccionableEnPosicion(posAInsertar, accionable);
    }

    private void insertarAccionableEnEjeYDeGrilla(Accionable accionable){
        Posicion posAInsertar;
        do {
            posAInsertar = this.conseguirAzarPosEnCalleVertical();
        } while (this.esPosYaOcupada(posAInsertar));

        this.grilla.insertarAccionableEnPosicion(posAInsertar, accionable);
    }

    // Las calles son horizontales cuando Y es multiplo de 3.
    private Posicion conseguirAzarPosEnCalleHorizontal(){
        return new Posicion(InicializadorGrilla.conseguirPosConMaxYNoMultiplo(this.grilla.obtenerTamanioEjeX(), 3),
                InicializadorGrilla.conseguirPosConMaxYMultiplo(this.grilla.obtenerTamanioEjeY(), 3));
    }

    // Las calles son verticales cuando X es multiplo de 3.
    private Posicion conseguirAzarPosEnCalleVertical(){
        return new Posicion(InicializadorGrilla.conseguirPosConMaxYMultiplo(this.grilla.obtenerTamanioEjeX(), 3),
                InicializadorGrilla.conseguirPosConMaxYNoMultiplo(this.grilla.obtenerTamanioEjeY(), 3));
    }

    private boolean esPosYaOcupada(Posicion pos){
        return (this.grilla.obtenerAccionableEnPosicion(pos) == null)
                || Posicion.posicionesCoincidenEnCoords(this.posIniVehiculo, pos)
                || Posicion.posicionesCoincidenEnCoords(this.posMeta, pos);
    }

    public Posicion conseguirPosIniValidaDeVehiculo(){
        return new Posicion(Constantes.posIniEnXDeVehiculo,
                InicializadorGrilla.conseguirPosConMaxYMultiplo(this.grilla.obtenerTamanioEjeY(), 3));
    }

    public Posicion conseguirPosValidaDeMeta(){
        return new Posicion(this.grilla.obtenerTamanioEjeX(),
                InicializadorGrilla.conseguirPosConMaxYMultiplo(this.grilla.obtenerTamanioEjeY(), 3));
    }

    // Una pos va a ser multiplo de un valor, cuando pos%valorMultiplo == 0.
    private static int conseguirPosConMaxYMultiplo(int valorMaximo, int valorMultiplo){
        int pos = RNG.nextInt(valorMaximo - 1) + 1;
        while ( ( pos %valorMultiplo ) != 0){
            pos = RNG.nextInt(valorMaximo - 1) + 1;
        }

        return pos;
    }

    // Una pos va a no ser multiplo de un valor, cuando pos%valorNoMultiplo != 0.
    private static int conseguirPosConMaxYNoMultiplo(int valorMaximo, int valorNoMultiplo){
        int pos = RNG.nextInt(valorMaximo - 1) + 1;
        while ( ( pos % valorNoMultiplo ) == 0){
            pos = RNG.nextInt(valorMaximo - 1) + 1;
        }

        return pos;
    }

    public Posicion obtenerPosIniVehiculo(){
        return posIniVehiculo;
    }
}