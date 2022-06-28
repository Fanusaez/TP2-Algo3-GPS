package edu.fiuba.algo3.View;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Accionable;
import edu.fiuba.algo3.modelo.GPS;
import edu.fiuba.algo3.modelo.Meta;
import edu.fiuba.algo3.modelo.Obstaculo.ControlPolicial;
import edu.fiuba.algo3.modelo.Obstaculo.Piquete;
import edu.fiuba.algo3.modelo.Obstaculo.Pozo;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Sorpresa.Desfavorable;
import edu.fiuba.algo3.modelo.Sorpresa.Favorable;
import edu.fiuba.algo3.modelo.Vehiculos.Auto;
import edu.fiuba.algo3.modelo.Vehiculos.EstadoVehiculo;
import edu.fiuba.algo3.modelo.Vehiculos.Moto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.nio.InvalidMarkException;

public class MapaLayout extends Pane {
    int largoEjeX;
    int largoEjeY;
    double separacionEntreCuadras = 32;
    double separacionEntreCuadrasInicio = 20;
    private Button vehviculo = new Button();

    public MapaLayout(Stage window, App app, GPS gps){
        largoEjeX = gps.obtenerTamanioEjesMapa().get(0);
        largoEjeY = gps.obtenerTamanioEjesMapa().get(1);
        Canvas canvas = new Canvas(900, 900);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        crearLineasMatriz(gps, gc, largoEjeX, largoEjeY);
        insertarPoliciasEnGrilla(gps, largoEjeX, largoEjeY);
        insertarPozosEnGrilla(gps, largoEjeX, largoEjeY);
        insertarPiquetesEnGrilla(gps, largoEjeX, largoEjeY);
        insertarSorpresasEnGrilla(gps, largoEjeX, largoEjeY);
        insertarVehiculo(gps);
        insertarBootones(gps);
        getChildren().add(canvas);
    }
    public void crearLineasMatriz(GPS gps, GraphicsContext gc, int largoEjeX, int largoEjeY){
        for(int i = 0; i <= largoEjeX; i++){
            if(i % 3 == 0){
                gc.strokeLine((separacionEntreCuadras * i) + separacionEntreCuadrasInicio, separacionEntreCuadrasInicio, (separacionEntreCuadras * i) + separacionEntreCuadrasInicio, (largoEjeY * separacionEntreCuadras) + separacionEntreCuadrasInicio);
            }
        }
        for(int j = 0; j <= largoEjeY; j++){
            if(j % 3 == 0){
                gc.strokeLine(separacionEntreCuadrasInicio, (separacionEntreCuadras * j) + separacionEntreCuadrasInicio, (largoEjeX * separacionEntreCuadras) + separacionEntreCuadrasInicio, (separacionEntreCuadras * j) + separacionEntreCuadrasInicio);
            }
        }
    }

    public void insertarPoliciasEnGrilla(GPS gps, int largoEjeX, int largoEjeY){
        for (int i = 0; i < largoEjeX; i++){
            for (int j = 0; j < largoEjeY; j++) {
                Accionable accionable = gps.obtenerAccionableEnPosicion(i, j);
                if(accionable.sosAccionable(new ControlPolicial())){
                    Button boton = new Button();
                    boton.setGraphic(new ImagenBoton("src/fotos/policia.jpg", 36, 36));
                    boton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(5))));
                    boton.setLayoutX(separacionEntreCuadras * i);
                    boton.setLayoutY(separacionEntreCuadras * j);
                    getChildren().add(boton);
                }
            }
        }

    }

    public void insertarPozosEnGrilla(GPS gps, int largoEjeX, int largoEjeY){
        for (int i = 0; i < largoEjeX; i++){
            for (int j = 0; j < largoEjeY; j++) {
                Accionable accionable = gps.obtenerAccionableEnPosicion(i, j);
                if(accionable.sosAccionable(new Pozo())){
                    Button boton = new Button();
                    boton.setGraphic(new ImagenBoton("src/fotos/pozo.jpg", 36, 36));
                    boton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(5))));
                    boton.setLayoutX(separacionEntreCuadras * i);
                    boton.setLayoutY(separacionEntreCuadras * j);
                    getChildren().add(boton);
                }
            }
        }

    }

    public void insertarPiquetesEnGrilla(GPS gps, int largoEjeX, int largoEjeY){
        for (int i = 0; i < largoEjeX; i++){
            for (int j = 0; j < largoEjeY; j++) {
                Accionable accionable = gps.obtenerAccionableEnPosicion(i, j);
                if(accionable.sosAccionable(new Piquete())){
                    Button boton = new Button();
                    boton.setGraphic(new ImagenBoton("src/fotos/piquete.jpg", 36, 36));
                    boton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(5))));
                    boton.setLayoutX(separacionEntreCuadras * i);
                    boton.setLayoutY(separacionEntreCuadras * j);
                    getChildren().add(boton);
                }
            }
        }

    }

    public void insertarSorpresasEnGrilla(GPS gps, int largoEjeX, int largoEjeY){
        for (int i = 0; i < largoEjeX; i++){
            for (int j = 0; j < largoEjeY; j++) {
                Accionable accionable = gps.obtenerAccionableEnPosicion(i, j);
                if(accionable.sosAccionable(new Favorable()) || accionable.sosAccionable(new Desfavorable()) || accionable.sosAccionable(new Desfavorable())){
                    Button boton = new Button();
                    boton.setGraphic(new ImagenBoton("src/fotos/sorpresa2.jpeg", 36, 36));
                    boton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(5))));
                    boton.setLayoutX(separacionEntreCuadras * i);
                    boton.setLayoutY(separacionEntreCuadras * j);
                    getChildren().add(boton);
                }
            }
        }

    }

    public void insertarMetaEnGrilla(GPS gps, int largoEjeX, int largoEjeY){
        for (int i = 0; i < largoEjeX; i++){
            for (int j = 0; j < largoEjeY; j++) {
                Accionable accionable = gps.obtenerAccionableEnPosicion(i, j);
                if(accionable.sosAccionable(new Meta())){
                    Button boton = new Button();
                    boton.setGraphic(new ImagenBoton("src/fotos/meta.jpg", 36, 36));
                    boton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(5))));
                    boton.setLayoutX(separacionEntreCuadras * i);
                    boton.setLayoutY(separacionEntreCuadras * j);
                    getChildren().add(boton);
                }
            }
        }

    }

    public void insertarVehiculo(GPS gps){
        Posicion posVehiculo = gps.obtenerPosicionVehiculo();
        EstadoVehiculo estado = gps.otenerEstadoVehiculo();

        if (estado.sosEstado(new Auto(posVehiculo))){
            vehviculo.setGraphic(new ImagenBoton("src/fotos/auto.png", 36, 36));
        }
        else if (estado.sosEstado(new Moto(posVehiculo))){
            vehviculo.setGraphic(new ImagenBoton("src/fotos/moto.jpg", 36, 36));
        }
        else{
            vehviculo.setGraphic(new ImagenBoton("src/fotos/4x4.jpg", 36, 36));
        }
        vehviculo.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(5))));
        vehviculo.setLayoutX(separacionEntreCuadras * posVehiculo.obtenerPosX());
        vehviculo.setLayoutY(separacionEntreCuadras * posVehiculo.obtenerPosY());
        getChildren().add(vehviculo);
    }

    private void insertarBootones(GPS gps){
       insertarBotonEste(gps);
       insertarBotonNorte(gps);
       insertarBotonOeste(gps);
       insertarBotonSur(gps);
    }

    private void insertarBotonOeste(GPS gps){
        Button moverOeste = new Button();
        moverOeste.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(5))));
        moverOeste.setOnAction(e->{
            gps.moverVehiculoIzquierda();
            insertarVehiculo(gps);
        });
        moverOeste.setGraphic(new ImagenBoton("src/fotos/Oeste.png", 50, 50));
        HBox barraInicio = new HBox(moverOeste);
        barraInicio.setAlignment(Pos.TOP_LEFT);
        barraInicio.setTranslateX(950);
        barraInicio.setTranslateY(100);
        getChildren().addAll(barraInicio);
    }

    private void insertarBotonEste(GPS gps){
        Button moverEste = new Button();
        moverEste.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(5))));
        moverEste.setOnAction(e->{
            gps.moverVehiculoDerecha();
            insertarVehiculo(gps);
        });
        moverEste.setGraphic(new ImagenBoton("src/fotos/Este.png", 50, 50));
        HBox barraInicio = new HBox(moverEste);
        barraInicio.setAlignment(Pos.TOP_LEFT);
        barraInicio.setTranslateX(1050);
        barraInicio.setTranslateY(100);
        getChildren().addAll(barraInicio);
    }

    private void insertarBotonNorte(GPS gps){
        Button moverNorte = new Button();
        moverNorte.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(5))));
        moverNorte.setOnAction(e->{
            gps.moverVehiculoArriba();
            insertarVehiculo(gps);
        });
        moverNorte.setGraphic(new ImagenBoton("src/fotos/Norte.png", 50, 50));
        HBox barraInicio = new HBox(moverNorte);
        barraInicio.setAlignment(Pos.TOP_LEFT);
        barraInicio.setTranslateX(1000);
        barraInicio.setTranslateY(30);
        getChildren().addAll(barraInicio);
    }

    private void insertarBotonSur(GPS gps){
        Button moverSur = new Button();
        moverSur.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, new Insets(5))));
        moverSur.setOnAction(e->{
            gps.moverVehiculoAbajo();
            insertarVehiculo(gps);
        });
        moverSur.setGraphic(new ImagenBoton("src/fotos/SUR.png", 50, 50));
        HBox barraInicio = new HBox(moverSur);
        barraInicio.setAlignment(Pos.TOP_LEFT);
        barraInicio.setTranslateX(1000);
        barraInicio.setTranslateY(175);
        getChildren().addAll(barraInicio);
    }







}
