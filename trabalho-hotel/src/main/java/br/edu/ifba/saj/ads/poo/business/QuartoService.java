package br.edu.ifba.saj.ads.poo.business;

import br.edu.ifba.saj.ads.poo.model.Quarto;
import br.edu.ifba.saj.ads.poo.data.QuartoDAO;

public class QuartoService extends GenericServiceImpl<Quarto, Long> {

    public QuartoService(QuartoDAO quartoDAO) {
        super(quartoDAO);
    }

    @Override
    public void validar(Quarto entidade) throws RegraDeNegocioException {
        if (entidade.getPreco() <= 0) {
            throw new RegraDeNegocioException("Preço deve ser maior que zero.");
        }
    }
}