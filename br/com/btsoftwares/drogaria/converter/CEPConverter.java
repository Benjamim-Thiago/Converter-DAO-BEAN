package br.com.btsoftwares.drogaria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cepConverter")
public class CEPConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String cep = value;
		if (value != null && !value.equals(""))
			cep = value.replaceAll("\\.", "").replaceAll("\\-", "");

		return cep;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String cep = (String) value;
		if (cep != null && cep.length() == 8)
			cep = cep.substring(0, 2) + "." + cep.substring(2, 5) + "-" + cep.substring(5, 8) ;
		return cep;
	}

}
