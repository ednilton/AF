package br.pucgoias.os.persistencia;

import java.io.Serializable;
import java.util.List;

import br.pucgoias.util.OrdemServicosException;


/**
 * Interface que define as operacoes da camada de persistencia generica
 * @author Edileizer
 *
 */
public interface GenericoDAO<T, ID extends Serializable> {
	
	/**
	 * Retorna a classe a ser persistida
	 * @return
	 */
	public Class<T> getObjectClass();
	
	/**
	 * Inclui um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws OrdemServicosException
	 */
	public T incluir(T object) throws OrdemServicosException;
	
	/**
	 * Altera um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws OrdemServicosException
	 */
	public T alterar(T object) throws OrdemServicosException;
	
	/**
	 * Consulta um objeto T da base de dados
	 * @param id
	 * @return
	 * @throws OrdemServicosException
	 */
	public T consultar(Integer id) throws OrdemServicosException;
	
	/**
	 * Exclui um objeto T  da base de dados
	 * @param id
	 * @throws OrdemServicosException
	 */
	public void excluir(Integer id) throws OrdemServicosException;
	
	/**
	 * Lista os objetos T da base de dados
	 * @return
	 * @throws OrdemServicosException
	 */
	public List<T> listar() throws OrdemServicosException;
	
}
