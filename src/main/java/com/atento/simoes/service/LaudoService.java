package com.atento.simoes.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atento.simoes.entity.Laudo;
import com.atento.simoes.repository.LaudoRepository;

@Service
public class LaudoService {

	private LaudoRepository laudoRepository;

	@Autowired
	public LaudoService(LaudoRepository laudoRepository) {
		this.laudoRepository = laudoRepository;
	}

	// Retorna uma lista com todos Laudos inseridos
	public List<Laudo> findAll() {
		return laudoRepository.findAll();
	}

	// Retorno um Laudo a partir do ID
	public Laudo findOne(Long id) {
		Optional<Laudo> laudos = laudoRepository.findById(id);
		return laudos.get();
	}

	// Salva ou atualiza um Laudo
	public Laudo save(Laudo laudo) {
		return laudoRepository.saveAndFlush(laudo);
	}

	// Exclui um Laudo
	public void delete(Long id) {
		laudoRepository.deleteById(id);
	}

	public Object save(@Valid Optional<Laudo> laudo) {
		return laudoRepository.saveAndFlush(laudo);
	}
}
