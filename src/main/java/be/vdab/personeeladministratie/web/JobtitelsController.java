package be.vdab.personeeladministratie.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeeladministratie.entities.Jobtitel;
import be.vdab.personeeladministratie.services.JobtitelService;

@Controller
@RequestMapping("jobtitels")
public class JobtitelsController {
	private static final String VIEW = "jobtitels/jobtitels";
	private static final String REDIRECT_BIJ_FOUTEN = "redirect:/jobtitels";
	private final JobtitelService jobtitelService;

	public JobtitelsController(JobtitelService jobtitelService) {
		this.jobtitelService = jobtitelService;
	}

	@GetMapping()
	public ModelAndView toonJobtitels() {
		List<Jobtitel> jobtitels = jobtitelService.vindAlleJobtitels();
		return new ModelAndView(VIEW).addObject("jobtitels", jobtitels);
	}

	@GetMapping("{jobtitel}")
	public ModelAndView toonWerknemersMetEenJobtitel(@PathVariable Optional<Jobtitel> jobtitel,
			RedirectAttributes redirectAttributes) {
		if (!jobtitel.isPresent()) {
			redirectAttributes.addAttribute("fout", "Foutboodschap: u heeft een niet bestaande jobtitel gezocht.");
			return new ModelAndView(REDIRECT_BIJ_FOUTEN);
		}
		List<Jobtitel> jobtitels = jobtitelService.vindAlleJobtitels();
		return new ModelAndView(VIEW).addObject("jobtitels", jobtitels).addObject(jobtitel.get());

	}
}