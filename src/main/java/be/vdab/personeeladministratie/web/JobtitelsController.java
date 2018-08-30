package be.vdab.personeeladministratie.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeeladministratie.entities.Jobtitel;
import be.vdab.personeeladministratie.entities.Werknemer;
import be.vdab.personeeladministratie.services.JobtitelService;
import be.vdab.personeeladministratie.services.WerknemerService;

@Controller
@RequestMapping("jobtitels")
public class JobtitelsController {
	private static final String VIEW = "jobtitels/jobtitels";
	private static final String REDIRECT_BIJ_FOUTEN = "redirect:/jobtitels"; 
	private final JobtitelService jobtitelService;
	private final WerknemerService werknemerService;

	public JobtitelsController(JobtitelService jobtitelService, WerknemerService werknemerService) {
		this.jobtitelService = jobtitelService;
		this.werknemerService = werknemerService;
	}

	@GetMapping()
	public ModelAndView toonJobtitels() {
		List<Jobtitel> jobtitels = jobtitelService.vindAlleJobtitels();
		List<Werknemer> werknemers = new ArrayList<>();
		return new ModelAndView(VIEW)
						.addObject("jobtitelnaam","")
						.addObject("jobtitels",jobtitels)
						.addObject("werknemers",werknemers);
	}
	
	@GetMapping("{jobtitel}")
	public ModelAndView toonWerknemersMetEenJobtitel(@PathVariable Optional<Jobtitel> jobtitel,RedirectAttributes redirectAttributes) {
		if (!jobtitel.isPresent()) {
			redirectAttributes.addAttribute("fout", "Foutboodschap: u heeft een niet bestaande werknemer gezocht.");
			return new ModelAndView(REDIRECT_BIJ_FOUTEN);
		}
		List<Jobtitel> jobtitels = jobtitelService.vindAlleJobtitels();
		List<Werknemer> werknemers = werknemerService.vindWerknemersMetJobtitel(jobtitel.get().getNaam());
		return new ModelAndView(VIEW)
						.addObject("jobtitelnaam",jobtitel.get().getNaam())
						.addObject("jobtitels",jobtitels)
						.addObject("werknemers",werknemers);
	}		
}