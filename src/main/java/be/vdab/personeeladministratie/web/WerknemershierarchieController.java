package be.vdab.personeeladministratie.web;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeeladministratie.entities.Werknemer;
import be.vdab.personeeladministratie.exceptions.PresidentNietGevondenException;
import be.vdab.personeeladministratie.services.WerknemerService;

@Controller
@RequestMapping("werknemershierarchie")
public class WerknemershierarchieController {
	private static final String VIEW_WERKNEMER = "werknemershierarchie/werknemershierarchie";
	private static final String VIEW_OPSLAG = "werknemershierarchie/opslag";
	private static final String VIEW_RIJKSREGISTERNUMMER = "werknemershierarchie/rijksregisternummer";
	private static final String REDIRECT_NAAR_WERKNEMER = "redirect:/werknemershierarchie/{id}";
	private static final String REDIRECT_BIJ_FOUTEN = "redirect:/"; 
	WerknemerService werknemerService;

	public WerknemershierarchieController(WerknemerService werknemerService) {
		this.werknemerService = werknemerService;
	}

	@GetMapping()
	String start(RedirectAttributes redirectAttributes) {
		try {
			long idVanPresident = werknemerService.vindPresident().getId();
			redirectAttributes.addAttribute("id",idVanPresident);
			return REDIRECT_NAAR_WERKNEMER;
		} catch (PresidentNietGevondenException ex) {
			redirectAttributes.addAttribute("fout",ex.getMessage());
			return REDIRECT_BIJ_FOUTEN;
		}
	}
	
	@GetMapping("{werknemer}")
	ModelAndView toonWerknemer(@PathVariable Optional<Werknemer> werknemer,RedirectAttributes redirectAttributes) {
		if (!werknemer.isPresent()) {
			redirectAttributes.addAttribute("fout", "Foutboodschap: u heeft een niet bestaande werknemer gezocht.");
			return new ModelAndView(REDIRECT_BIJ_FOUTEN);
		}
		return new ModelAndView(VIEW_WERKNEMER).addObject(werknemer.get());
	}
	
	@GetMapping("{werknemer}/opslag")
	ModelAndView toonOpslag(@PathVariable Optional<Werknemer> werknemer,RedirectAttributes redirectAttributes) {
		if (!werknemer.isPresent()) {
			redirectAttributes.addAttribute("fout", "Foutboodschap: u heeft een niet bestaande werknemer gezocht.");
			return new ModelAndView(REDIRECT_BIJ_FOUTEN);
		}
		return new ModelAndView(VIEW_OPSLAG)
				.addObject(werknemer.get())
				.addObject(new OpslagForm());
	}
	
	@PostMapping("{werknemer}/opslag")
	ModelAndView geefOpslag(@PathVariable(name="werknemer") Optional<Werknemer> optioneleWerknemer, @Valid OpslagForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (!optioneleWerknemer.isPresent()) {
			redirectAttributes.addAttribute("fout", "Foutboodschap: u heeft een niet bestaande werknemer gezocht.");
			return new ModelAndView(REDIRECT_BIJ_FOUTEN);
		}
		Werknemer werknemer = optioneleWerknemer.get();
		if (bindingResult.hasErrors()) {
			return new ModelAndView(VIEW_OPSLAG).addObject(werknemer);
		}
		BigDecimal bedragOpslag = form.getBedragOpslag();
		werknemerService.verhoogSalaris(werknemer, bedragOpslag);
		redirectAttributes.addAttribute("id",werknemer.getId());
		return new ModelAndView(REDIRECT_NAAR_WERKNEMER);
	}
	
	@GetMapping("{werknemer}/rijksregisternummer")
	ModelAndView toonRijksregisternummerPagina(@PathVariable Optional<Werknemer> werknemer,RedirectAttributes redirectAttributes) {
		if (werknemer.isPresent()) {
			redirectAttributes.addAttribute("fout", "Foutboodschap: u heeft een niet bestaande werknemer gezocht.");
			return new ModelAndView(REDIRECT_BIJ_FOUTEN);
		}
		return new ModelAndView(VIEW_RIJKSREGISTERNUMMER).addObject(werknemer.get());
		
	}
}
