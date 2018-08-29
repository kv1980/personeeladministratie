package be.vdab.personeeladministratie.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import be.vdab.personeeladministratie.services.WerknemerService;

@Controller
@RequestMapping("werknemershierarchie")
public class WerknemershierarchieController {
	private static final String VIEW = "werknemershierarchie/werknemershierarchie";
	WerknemerService werknemerservice;

	public WerknemershierarchieController(WerknemerService werknemerservice) {
		this.werknemerservice = werknemerservice;
	}

/*	@GetMapping()
	ModelAndView read() {
		Werknemer president = werknemersservice.toonPresident();
		if (filiaal.isPresent()) {
			return new ModelAndView(FILIAAL_VIEW).addObject(filiaal.get());
		}
		redirectAttributes.addAttribute("fout", "Foutboodschap: filiaal werd niet gevonden.");
		return new ModelAndView(REDIRECT_FILIAAL_NIET_GEVONDEN);
	}*/
}
