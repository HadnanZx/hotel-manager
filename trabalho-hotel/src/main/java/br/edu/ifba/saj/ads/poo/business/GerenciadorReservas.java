package br.edu.ifba.saj.ads.poo.business;

import java.util.ArrayList;
import br.edu.ifba.saj.ads.poo.model.Reserva;
import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.model.Data;
import br.edu.ifba.saj.ads.poo.model.Hospede;
import br.edu.ifba.saj.ads.poo.model.TipoQuarto;

public class GerenciadorReservas {
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

    public boolean temConflito(Quarto quartoNovo, Data checkinNovo, Data checkoutNovo) {
        for (Reserva reservaExistente : reservas) {
            if (reservaExistente.getQuarto() == quartoNovo) {
                boolean semConflito = checkoutNovo.anterior(reservaExistente.getCheckin()) 
                                    || checkinNovo.posterior(reservaExistente.getCheckout());
                if (!semConflito) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean criarReserva(Hospede hospede, Quarto quarto, Data checkin, Data checkout) {
        if (!checkout.posterior(checkin)) {
            return false;
        }
        if (temConflito(quarto, checkin, checkout)) {
            return false;
        }
        Reserva novaReserva = new Reserva(hospede, quarto, checkin, checkout);
        reservas.add(novaReserva);
        return true;
    }
}