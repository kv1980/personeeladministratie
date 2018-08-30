package be.vdab.personeeladministratie.web;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OpslagForm {
	@NotNull
	@Min(1)
	private BigDecimal bedragOpslag;

	public BigDecimal getBedragOpslag() {
		return bedragOpslag;
	}

	public void setBedragOpslag(BigDecimal bedragOpslag) {
		this.bedragOpslag = bedragOpslag;
	}
}
