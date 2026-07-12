package br.edu.ifba.saj.ads.poo.business;

import java.util.ArrayList;

import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.model.Reserva;
import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.model.Data;

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
}
