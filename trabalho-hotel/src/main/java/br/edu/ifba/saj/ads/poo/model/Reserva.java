package br.edu.ifba.saj.ads.poo.model;
import java.time.LocalDate;

public class Reserva extends AbstractModel<Long>{
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate checkin;
    private LocalDate checkout;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate checkin, LocalDate checkout){
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
    public LocalDate getCheckin(){
        return checkin;
    }
    public LocalDate getCheckout(){
        return checkout;
    }
    public void setHospede(Hospede hospede){
    this.hospede = hospede;
    }
    public void setQuarto(Quarto quarto){
        this.quarto = quarto;
    }
    public void setCheckin(LocalDate checkin){
        this.checkin = checkin;
    }
    public void setCheckout(LocalDate checkout){
        this.checkout = checkout;
    }
    
    @Override
    public String toString(){
        return hospede + " - " + quarto + " - " + checkin + " até " + checkout;
    }
}
