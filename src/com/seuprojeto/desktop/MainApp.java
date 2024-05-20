package com.seuprojeto.desktop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Calculator calculator = new Calculator();

    @Override
    public void start(Stage primaryStage) {
        TextField custoHoraField = new TextField();
        TextField horasField = new TextField();
        TextField custoMaterialField = new TextField();
        TextField quantidadeMaterialField = new TextField();
        TextField porcentagemLucroField = new TextField();
        Button calcularButton = new Button("Calcular");
        Label resultadoLabel = new Label();

        calcularButton.setOnAction(event -> {
            double custoHora = Double.parseDouble(custoHoraField.getText());
            double horas = Double.parseDouble(horasField.getText());
            double custoMaterial = Double.parseDouble(custoMaterialField.getText());
            double quantidadeMaterial = Double.parseDouble(quantidadeMaterialField.getText());
            double porcentagemLucro = Double.parseDouble(porcentagemLucroField.getText());

            double custoTotal = calculator.calcularCustoHoraMaquina(custoHora, horas) +
                                calculator.calcularCustoMaterial(custoMaterial, quantidadeMaterial);
            double valorCobrado = calculator.calcularValorCobrado(custoTotal, porcentagemLucro);

            resultadoLabel.setText("Valor Cobrado: " + valorCobrado);
        });

        VBox root = new VBox(10, 
            new Label("Custo Hora:"), custoHoraField, 
            new Label("Horas:"), horasField, 
            new Label("Custo Material:"), custoMaterialField, 
            new Label("Quantidade Material:"), quantidadeMaterialField, 
            new Label("Porcentagem Lucro:"), porcentagemLucroField, 
            calcularButton, resultadoLabel
        );
        
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Calculadora de Custos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
