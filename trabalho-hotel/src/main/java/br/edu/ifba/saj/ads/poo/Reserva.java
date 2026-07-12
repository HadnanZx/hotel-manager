package br.edu.ifba.saj.ads.poo;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private Data checkin;
    private Data checkout;

    public Reserva(Hospede hospede, Quarto quarto, Data checkin, Data checkout){
        this.hospede = hospede;
        this.quarto = quarto;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Hospede getHospede(){
        return hospede;
    }
    public Quarto getQuarto(){
        return quarto;
    }
    public Data getCheckin(){
        return checkin;
    }
    public Data getCheckout(){
        return checkout;
    }
}
