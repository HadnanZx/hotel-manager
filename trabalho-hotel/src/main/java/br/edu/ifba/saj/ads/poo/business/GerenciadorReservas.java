package br.edu.ifba.saj.ads.poo.business;

import br.edu.ifba.saj.ads.poo.model.Reserva;
import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.data.ReservaDAO;

public class GerenciadorReservas extends GenericServiceImpl<Reserva, Long> {

    public GerenciadorReservas(ReservaDAO reservaDAO) {
        super(reservaDAO);
    }

    @Override
    public void validar(Reserva entidade) throws RegraDeNegocioException {
        if (!entidade.getCheckout().isAfter(entidade.getCheckin())) {
            throw new RegraDeNegocioException("Checkout deve ser depois do checkin.");
        }
        if (temConflito(entidade.getQuarto(), entidade)) {
            throw new RegraDeNegocioException("Este quarto já está reservado nesse período.");
        }
    }

    private boolean temConflito(Quarto quartoNovo, Reserva reservaNova) {
        for (Reserva reservaExistente : buscarTodos()) {
            if (reservaExistente.getQuarto() == quartoNovo && reservaExistente != reservaNova) {
                boolean semConflito = reservaNova.getCheckout().isBefore(reservaExistente.getCheckin())
                                    || reservaNova.getCheckin().isAfter(reservaExistente.getCheckout());
                if (!semConflito) {
                    return true;
                }
            }
        }
        return false;
    }
}