package be.vdab.personeeladministratie.adapters;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String,LocalDate> 	{

	@Override
	public String marshal(LocalDate date) throws Exception {
		return date.toString();
	}

	@Override
	public LocalDate unmarshal(String text) throws Exception {
		return LocalDate.parse(text);
	}
}
