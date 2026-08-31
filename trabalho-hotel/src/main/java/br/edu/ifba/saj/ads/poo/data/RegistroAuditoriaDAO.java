package br.edu.ifba.saj.ads.poo.data;

import br.edu.ifba.saj.ads.poo.model.RegistroAuditoria;

public class RegistroAuditoriaDAO extends GenericDAOImpl<RegistroAuditoria, Long>{

    public RegistroAuditoriaDAO(){
        super(Long.class);
    }
}