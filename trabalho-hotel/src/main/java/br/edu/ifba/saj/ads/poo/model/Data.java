package br.edu.ifba.saj.ads.poo.model;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }

    public void incrementarDia() {
        dia = dia + 1;
    }

    public boolean anterior(Data outraData) {
        int meuNumero = (ano * 10000) + (mes * 100) + (dia * 1);
        int numeroOutra = (outraData.ano * 10000) + (outraData.mes * 100) + (outraData.dia * 1);
        if (meuNumero < numeroOutra) {
            return true;
        } else {
            return false;
        }
    }

    public boolean posterior(Data outraData) {
        int meuNumero = (ano * 10000) + (mes * 100) + (dia * 1);
        int numeroOutra = (outraData.ano * 10000) + (outraData.mes * 100) + (outraData.dia * 1);
        if (meuNumero > numeroOutra) {
            return true;
        } else {
            return false;
        }
    }

    public boolean igual(Data outraData) {
        int meuNumero = (ano * 10000) + (mes * 100) + (dia * 1);
        int numeroOutra = (outraData.ano * 10000) + (outraData.mes * 100) + (outraData.dia * 1);
        if (meuNumero == numeroOutra) {
            return true;
        } else {
            return false;
        }
    }
}
