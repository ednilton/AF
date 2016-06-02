package br.pucgoias.os.persistencia;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.pucgoias.util.OrdemServicosException;

/**
 * Classe que define as operacoes da camada de persistencia generica
 * @author Edileizer
 *
 */
public class GenericoDAOImpl<T, ID extends Serializable> implements GenericoDAO<T, ID> {

	private EntityManager entityManager;
	private final Class<T> oClass;

	//Classe a ser persistida
	public Class<T> getObjectClass() {
		return this.oClass;
	}

	//Gerenciador de persistencia
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public GenericoDAOImpl(){
		this.oClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Inclui um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws OrdemServicosException
	 */
	public T incluir(T object) throws OrdemServicosException {
		try{
			getEntityManager().merge(object);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new OrdemServicosException(e,"Não foi possível realizar a inclusão.");
		}
		return object;
	}

	/**
	 * Altera um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws OrdemServicosException
	 */
	public T alterar(T object) throws OrdemServicosException {
		try{
			getEntityManager().merge(object);
		}
		catch (Exception e) {
			throw new OrdemServicosException(e,"Não foi possível realizar a alteração.");
		}
		return object;
	}

	/**
	 * Consulta um objeto T da base de dados
	 * @param id
	 * @return
	 * @throws OrdemServicosException
	 */
	public T consultar(Integer id) throws OrdemServicosException {
		T object = null;
		try{
			object = getEntityManager().find(getObjectClass(), id);
		}
		catch (EntityNotFoundException e) {
			throw new OrdemServicosException(e,"Registro não encontrado.");
		}
		catch (Exception e) {
			throw new OrdemServicosException(e,"Não foi possível realizar a consulta.");
		}
		return object;
	}

	/**
	 * Exclui um objeto T  da base de dados
	 * @param id
	 * @throws OrdemServicosException
	 */
	public void excluir(Integer id) throws OrdemServicosException {
		try{
			getEntityManager().remove(getEntityManager().getReference(getObjectClass(), id ));
		}
		catch (EntityNotFoundException e) {
			throw new OrdemServicosException(e,"Registro não encontrado para exclusão.");
		}
		catch (Exception e) {
			throw new OrdemServicosException(e,"Não foi possível realizar a exclusão.");
		}
		
	}

	/**
	 * Lista os objetos T da base de dados
	 * @return
	 * @throws OrdemServicosException
	 */
	@SuppressWarnings("unchecked")
	public List<T> listar() throws OrdemServicosException {
		List<T> lista = null;  
        try {  
            Query query = getEntityManager().createQuery("SELECT object(o) FROM " + getObjectClass().getSimpleName() + " AS o");  
            lista = query.getResultList();  
        } catch (Exception e) {  
            throw new OrdemServicosException(e, "Problemas na localização dos objetos");  
        }  
        return lista;  
     }

}
