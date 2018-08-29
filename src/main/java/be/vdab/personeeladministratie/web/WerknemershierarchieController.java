package be.vdab.personeeladministratie.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeeladministratie.entities.Werknemer;
import be.vdab.personeeladministratie.exceptions.PresidentNietGevondenException;
import be.vdab.personeeladministratie.services.WerknemerService;

@Controller
@RequestMapping("werknemershierarchie")
public class WerknemershierarchieController {
	private static final String VIEW_WERKNEMERSHIERARCHIE = "werknemershierarchie/werknemershierarchie";
	private static final String VIEW_HOMEPAGE = "index";
	WerknemerService werknemerService;

	public WerknemershierarchieController(WerknemerService werknemerService) {
		this.werknemerService = werknemerService;
	}

	@GetMapping()
	ModelAndView toonPresident() {
		System.out.println("------------start van toonPresident");
		try {
			Werknemer president = werknemerService.vindPresident();
			System.out.println("------------------------------"+president.getEmail());
			ModelAndView modelAndView = new ModelAndView(VIEW_WERKNEMERSHIERARCHIE,"president",president);
			modelAndView.addObject("ondergeschikten",president.getOndergeschikten());
			System.out.println("--------------------"+president.getOndergeschikten().size());
			return modelAndView;
			
			
		} catch (PresidentNietGevondenException ex) {
			return new ModelAndView(VIEW_HOMEPAGE,"foutboodschap",ex.getMessage());
		}
	}
}
