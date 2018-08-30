package be.vdab.personeeladministratie.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.personeeladministratie.web.RijksregisternrForm;

public class RijksregisternrKomtOvereenMetGeboortedatumValidator 
implements ConstraintValidator<RijksregisternrKomtOvereenMetGeboortedatum,RijksregisternrForm>{
	@Override
	public void initialize(RijksregisternrKomtOvereenMetGeboortedatum rijksregisternrKomtOvereenMetGeboortedatum) {
	}

	@Override
	public boolean isValid(RijksregisternrForm form, ConstraintValidatorContext context) {
		long jaar = form.getGeboortedatum().getYear()%100;
		long maand = form.getGeboortedatum().getMonthValue();
		long dag = form.getGeboortedatum().getDayOfMonth();
		System.out.println(form.getRijksregisternr());
		long controlegetal = form.getRijksregisternr() - ((jaar*100+maand)*100+dag)*100000 ;
		return controlegetal >= 0 && controlegetal < 100000;
	}
}