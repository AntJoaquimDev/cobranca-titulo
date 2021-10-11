package com.joaquim.cobranca.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joaquim.cobranca.model.StatusTitulo;
import com.joaquim.cobranca.model.Titulo;
import com.joaquim.cobranca.repository.TitulosRepository;
import com.joaquim.cobranca.service.ServiceCadastroTitulo;

@Controller
@RequestMapping("/titulos")
public class cadastroController {

	@Autowired
	TitulosRepository titulosRepository;

	private static final String CADASTRO_VIEW = "CadastroTitulo";

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView andView = new ModelAndView(CADASTRO_VIEW);
		andView.addObject("todosStatusTitulo", StatusTitulo.values());
		andView.addObject("titulo", new Titulo());
		return andView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarTitulo(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		// ModelAndView anvView = new ModelAndView("CadastroTitulo");
		if (errors.hasErrors()) {
			return "CadastroTitulo";
		}
		try {

			titulo = titulosRepository.save(titulo);
			attributes.addFlashAttribute("mensagem", "Titulo salvo com sucesso!");
			return "redirect:/titulos/novo";
		} catch (DataIntegrityViolationException e) {
			errors.reject("dataVencimento",null,"Formato de data inválido");
			return CADASTRO_VIEW;
		}
		
	}

	@RequestMapping
	//public ModelAndView pesquisar(@RequestParam(defaultValue ="%")String descricao) {busca com parametro
		public ModelAndView pesquisar(@ModelAttribute("filtro") Titulo filtro) {
		
		String descricao= filtro.getDescricao()==null ? "%" : filtro.getDescricao();
		List<Titulo> titulos = titulosRepository.findByDescricaoContaining(descricao);
		
		ModelAndView andView = new ModelAndView("PesquisaTitulos");
		andView.addObject("titulos", titulos);
		return andView;
	}

/*
	@GetMapping("pesquisarDescricao")
	public ModelAndView pesquisarDescricao(@RequestParam(value = "psqDesc") String descricao) {
		List<Titulo> titulos = titulosRepository.findByName(descricao);
		
		ModelAndView andView = new ModelAndView("PesquisaTitulos");
		andView.addObject("titulos", titulos);
		return andView;
	}
	*/
    @RequestMapping(value = "/{id}/receber", method = RequestMethod.PUT)
    public @ResponseBody String receber(@PathVariable Long id) {
    
    	Titulo titulo = titulosRepository.getOne(id);
    	
    	
    	titulo.setStatus(StatusTitulo.RECEBIDO);
		titulo = titulosRepository.save(titulo);
    	
		return StatusTitulo.RECEBIDO.getDescricao();
    	
    }
	
	
	@RequestMapping("{id}")
	public ModelAndView editarTitulo(@PathVariable("id") Titulo titulo) {
		ModelAndView andView = new ModelAndView("CadastroTitulo");
		andView.addObject(titulo);

		return andView;

	} 
	/*
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		titulosRepository.deleteById(id);
		
		//attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulos";
	}
	*/
		@RequestMapping("/excluirTitulo/{id}" )
		public String excluir(@PathVariable ("id") Long id, RedirectAttributes attributes) {
			titulosRepository.deleteById(id);
			
			ModelAndView andView = new ModelAndView("PesquisaTitulos");
			List<Titulo> titulos = titulosRepository.findAll();
			andView.addObject("titulos", titulos);
			
			return "redirect:/titulos";
			//return andView;
		}

	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitolus() {
		return Arrays.asList(StatusTitulo.values());
	}
}

//codigos bollerplayt

//@RequestMapping("/editarTitulo/{id}")
// public ModelAndView editarTitulo(@PathVariable ("id") Long id) {
// Optional<Titulo> titulo= titulosRepository.findById(id);
// ModelAndView andView = new ModelAndView("CadastroTitulo");
// andView.addObject("titulo",titulo.get());