package be.vdab.personeeladministratie.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE })
@Constraint(validatedBy = RijksregisternrValidator.class)
public @interface Rijksregisternr {
	String message() default "{be.vdab.personeeladministratie.constraints.Rijksregisternr.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}