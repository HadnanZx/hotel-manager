package br.edu.ifba.saj.ads.poo.business;

import java.util.ArrayList;

import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.model.Reserva;
import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.model.Data;
import br.edu.ifba.saj.ads.poo.model.Hospede;

public class GerenciadorReservas {
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

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
