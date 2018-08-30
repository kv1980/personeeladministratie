package be.vdab.personeeladministratie.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = RijksregisternrKomtOvereenMetGeboortedatumValidator.class)
public @interface RijksregisternrKomtOvereenMetGeboortedatum {
		String message() default "{be.vdab.personeeladministratie.constraints.RijksregisternrKomtOvereenMetGeboortedatum.message}";
		Class<?>[] groups() default {};
		Class<? extends Payload>[] payload() default {};
}
