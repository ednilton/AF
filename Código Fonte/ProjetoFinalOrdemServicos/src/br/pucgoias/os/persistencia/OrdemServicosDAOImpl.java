package br.pucgoias.os.persistencia;

import org.springframework.stereotype.Repository;

import br.pucgoias.os.entidade.OrdemServicos;

/**
 * Classe que define as operacoes da camada de persistencia de Ordem de Servicos
 * @author Edileizer
 *
 */
@Repository
public class OrdemServicosDAOImpl extends GenericoDAOImpl<OrdemServicos, Integer> implements
		OrdemServicosDAO {

}
