package com.joaquim.cobranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquim.cobranca.model.StatusTitulo;
import com.joaquim.cobranca.model.Titulo;
import com.joaquim.cobranca.repository.TitulosRepository;

@Service
public class ServiceCadastroTitulo {
/*
	@Autowired
	public static TitulosRepository titulosRepository;
	
	public static void receber(Long id) {
        Titulo titulo = titulosRepository.getOne(id);
    	
    	
    	titulo.setStatus(StatusTitulo.RECEBIDO);
		titulo = titulosRepository.save(titulo);
	}
/*
 * public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}
	
 * 
 * 
	
	public static void salvar(Titulo titulo) {
		try {
			titulosRepository.save(titulo);
		} catch (DataIntegrityViolationException e) {
			
			throw new IllegalArgumentException("Formato de data inválida.");
		}
	}

	public static void excluir(Long id) {
		titulosRepository.deleteById(id);
	}
	
	
	*/
}
