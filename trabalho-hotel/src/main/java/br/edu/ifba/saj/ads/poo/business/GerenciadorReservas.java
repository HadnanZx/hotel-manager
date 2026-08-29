package br.edu.ifba.saj.ads.poo.business;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.saj.ads.poo.model.Reserva;
import br.edu.ifba.saj.ads.poo.model.Quarto;
import java.time.LocalDate;
import br.edu.ifba.saj.ads.poo.model.Hospede;
import br.edu.ifba.saj.ads.poo.model.TipoQuarto;
import java.util.List;

public class GerenciadorReservas implements GenericService<Reserva, Long>{
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private ArrayList<Hospede> hospedes = new ArrayList<Hospede>();
    private ArrayList<Quarto> quartos = new ArrayList<Quarto>();

    public GerenciadorReservas() {
        quartos.add(new Quarto(101, TipoQuarto.SOLTEIRO, 150.0));
        quartos.add(new Quarto(102, TipoQuarto.SOLTEIRO, 150.0));
        quartos.add(new Quarto(201, TipoQuarto.CASAL, 250.0));
        quartos.add(new Quarto(202, TipoQuarto.CASAL, 250.0));
        quartos.add(new Quarto(301, TipoQuarto.SUITE, 400.0));
    }

    public void cadastrarHospede(Hospede hospede) {
        hospedes.add(hospede);
    }

    public ArrayList<Hospede> getHospedes() {
        return hospedes;
    }

    public ArrayList<Quarto> getQuartos() {
        return quartos;
    }

    public ArrayList<Reserva> getReservas(){
        return reservas;
    }

    public boolean temConflito(Quarto quartoNovo, LocalDate checkinNovo, LocalDate checkoutNovo){
        for (Reserva reservaExistente : reservas) {
            if (reservaExistente.getQuarto() == quartoNovo) {
                boolean semConflito = checkoutNovo.isBefore(reservaExistente.getCheckin()) 
                                    || checkinNovo.isAfter(reservaExistente.getCheckout());
                if (!semConflito) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean criarReserva(Hospede hospede, Quarto quarto, LocalDate checkin, LocalDate checkout){
        if (!checkout.isAfter(checkin)) {
            return false;
        }
        if (temConflito(quarto, checkin, checkout)) {
            return false;
        }
        Reserva novaReserva = new Reserva(hospede, quarto, checkin, checkout);
        reservas.add(novaReserva);
        return true;
    }

    @Override
    public Reserva criar(Reserva entidade) throws CheckoutInvalidoException, ConflitoDeReservaException{
        if (!entidade.getCheckout().isAfter(entidade.getCheckin())) {
            throw new CheckoutInvalidoException("Checkout deve ser depois do checkin.");
        }
        if (temConflito(entidade.getQuarto(), entidade.getCheckin(), entidade.getCheckout())) {
            throw new ConflitoDeReservaException("Este quarto já está reservado nesse período.");
        }
        reservas.add(entidade);
        return entidade;
    }

    @Override
    public List<Reserva> listarTodos(){
        return reservas;
    }
}