package br.edu.ifba.saj.ads.poo.data;

import br.edu.ifba.saj.ads.poo.model.Reserva;

public class ReservaDAO extends GenericDAOImpl<Reserva, Long> {
    public ReservaDAO() {
        super(Long.class);
    }
}