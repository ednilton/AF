package br.pucgoias.af.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe que representa os dados persistentes de ordem de servicos
 * @author Edileizer
 *
 */

@Entity
@Table (name="ordemServicos")
public class OrdemServicos implements Serializable{
	
	private static final long serialVersionUID = 6487849002108370775L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idOS")
	private Integer idOS;
	
	@Column (name="cliente")
	private String cliente;
	
	@Column (name="end")
	private String endereco;
	
	@Column (name="telefone")
	private String telefone;
	
	@Column (name="dataEntrada")
	private Date dataEntrada;
	
	@Column (name="valor")
	private double valor;

	public Integer getIdOS() {
		return idOS;
	}

	public void setIdOS(Integer idOS) {
		this.idOS = idOS;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idOS == null) ? 0 : idOS.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		OrdemServicos other = (OrdemServicos) obj;
		if (idOS ==  null) {
			if (other.idOS != null)
				return false;
		} else if (!idOS.equals(other.idOS))
			return false;
		return true;
		}
	
}
