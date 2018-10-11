package com.atento.simoes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atento.simoes.entity.Laudo;
import com.atento.simoes.service.LaudoService;

@Controller
public class LaudoController {

	private final LaudoService laudoService;

	@Autowired
	public LaudoController(LaudoService laudoService) {
		this.laudoService = laudoService;
	}

	// Vai para tela principal do CRUD aonde são listados todos os laudos
	@GetMapping("/")
	public ModelAndView findAll() {

		ModelAndView mv = new ModelAndView("/cadastrolaudo");
		mv.addObject("laudos", laudoService.findAll());

		return mv;
	}

	// Vai para tela de adição de um laudo
	@GetMapping("/add")
	public ModelAndView add(Laudo laudo) {

		ModelAndView mv = new ModelAndView("/laudoAdd");
		mv.addObject("laudo", laudo);

		return mv;
	}

	// Vai para tela de edição de laudos (mesma tela de adição, contudo é enviado
	// para a view um objeto que já existe)
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {

		return add(laudoService.findOne(id));
	}

	// Exclui um post por seu ID e redireciona para a tela principal
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {

		laudoService.delete(id);

		return findAll();
	}

	// Recebe um objeto preenchido do Thymeleaf e valida
	// Se tudo estiver ok, salva e volta para tela principal
	// Se houver erro, retorna para tela atual exibindo as mensagens de erro
	@PostMapping("/save")
	public ModelAndView save(@Valid Laudo laudo, BindingResult result) {

		if (result.hasErrors()) {
			return add(laudo);
		}

		laudoService.save(laudo);

		return findAll();
	}
}