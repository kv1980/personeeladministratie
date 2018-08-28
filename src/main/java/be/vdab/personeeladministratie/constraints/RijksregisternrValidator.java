package be.vdab.personeeladministratie.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RijksregisternrValidator implements ConstraintValidator<Rijksregisternr,Long>{
	@Override
	public void initialize(Rijksregisternr rijksregisternr) {
	}
	
	@Override
	public boolean isValid(Long rijksregisternr, ConstraintValidatorContext context) {
		long nummer = rijksregisternr.longValue();
		if (nummer < Math.pow(10,7)) {
			return false;
		}
		if (nummer < Math.pow(10,10)) {
			nummer += 2*Math.pow(10, 11);
		}
		long eersteNegenCijfers = nummer/100;
		long laatsteTweeCijfers = nummer%100;
		long controlegetal = 97 - eersteNegenCijfers%97;
		return laatsteTweeCijfers == controlegetal;
	}
}
