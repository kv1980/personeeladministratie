package be.vdab.personeeladministratie.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private static final String REDIRECT_BIJ_START = "redirect:/werknemershierarchie/{id}";
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
			return REDIRECT_BIJ_START;
		} catch (PresidentNietGevondenException ex) {
			redirectAttributes.addAttribute("fout",ex.getMessage());
			return REDIRECT_BIJ_FOUTEN;
		}
	}
	
	@GetMapping("{werknemer}")
	ModelAndView toonWerknemer(@PathVariable Optional<Werknemer> werknemer,RedirectAttributes redirectAttributes) {
		if (werknemer.isPresent()) {
			return new ModelAndView(VIEW_WERKNEMER).addObject(werknemer.get());
		}
		redirectAttributes.addAttribute("fout", "Foutboodschap: u heeft een niet bestaande werknemer gezocht.");
		return new ModelAndView(REDIRECT_BIJ_FOUTEN);
	}
	
	@GetMapping("{werknemer}/opslag")
	ModelAndView toonOpslagPagina(@PathVariable Optional<Werknemer> werknemer,RedirectAttributes redirectAttributes) {
		if (werknemer.isPresent()) {
			return new ModelAndView(VIEW_OPSLAG)
					.addObject(werknemer.get())
					.addObject(new OpslagForm());
		}
		redirectAttributes.addAttribute("fout", "Foutboodschap: u heeft een niet bestaande werknemer gezocht.");
		return new ModelAndView(REDIRECT_BIJ_FOUTEN);
	}
	
	@GetMapping("{werknemer}/rijksregisternummer")
	ModelAndView toonRijksregisternummerPagina(@PathVariable Optional<Werknemer> werknemer,RedirectAttributes redirectAttributes) {
		if (werknemer.isPresent()) {
			return new ModelAndView(VIEW_RIJKSREGISTERNUMMER).addObject(werknemer.get());
		}
		redirectAttributes.addAttribute("fout", "Foutboodschap: u heeft een niet bestaande werknemer gezocht.");
		return new ModelAndView(REDIRECT_BIJ_FOUTEN);
	}
}
