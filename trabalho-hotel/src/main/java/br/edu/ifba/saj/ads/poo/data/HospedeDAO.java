package br.edu.ifba.saj.ads.poo.data;

import br.edu.ifba.saj.ads.poo.model.Hospede;

public class HospedeDAO extends GenericDAOImpl<Hospede, Long> {
    public HospedeDAO() {
        super(Long.class);
    }
}
