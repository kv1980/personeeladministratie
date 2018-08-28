package be.vdab.personeeladministratie.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class RijksregisternrValidator implements ConstraintValidator<Rijksregisternr,Long>{
	@Override
	public void initialize(Rijksregisternr rijksregisternr) {
	}
	
	@Override
	public boolean isValid(Long rijksregisternr, ConstraintValidatorContext context) {
		
		
	}
}
