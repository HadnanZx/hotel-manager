package br.edu.ifba.saj.ads.poo.business;

import br.edu.ifba.saj.ads.poo.model.Hospede;
import br.edu.ifba.saj.ads.poo.data.HospedeDAO;

public class HospedeService extends GenericServiceImpl<Hospede, Long>{

    public HospedeService(HospedeDAO hospedeDAO){
        super(hospedeDAO);
    }

    @Override
    public void validar(Hospede entidade) throws RegraDeNegocioException{
        if(entidade.getNome() == null || entidade.getNome().isBlank()){
            throw new RegraDeNegocioException("Nome do hóspede é obrigatório.");
        }
        if(entidade.getCpf() == null || entidade.getCpf().isBlank()){
            throw new RegraDeNegocioException("CPF do hóspede é obrigatório.");
        }
    }
}