package com.seuprojeto.desktop;

public class Calculator {
    public double calcularCustoHoraMaquina(double custoHora, double horas) {
        return custoHora * horas;
    }

    public double calcularCustoMaterial(double custoMaterial, double quantidade) {
        return custoMaterial * quantidade;
    }

    public double calcularValorCobrado(double custoTotal, double porcentagemLucro) {
        return custoTotal + (custoTotal * porcentagemLucro / 100);
    }
}
