package br.com.btsoftwares.drogaria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		String telefone = value;
		if (value != null && !value.equals(""))
			telefone = value.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "");

		return telefone;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String telefone = (String) value;
		if (telefone != null && telefone.length() == 10) {
			telefone = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 6) + "-" + telefone.substring(6, 10);
			
		} else if (telefone != null && telefone.length() == 11) {
			telefone = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 7) + "-" + telefone.substring(7, 11);
		}

		return telefone;
	}

}
