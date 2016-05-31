package br.pucgoias.af.controle;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Classe que representa o formulario web de Ordem de Serviços
 * @author Edileizer
 *
 */
@Component
public class OrdemServicosBean {
	
	private Integer idOS;
	private String cliente;
	private String endereco;
	private String telefone;
	private Date dataEntrada;
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
		
}