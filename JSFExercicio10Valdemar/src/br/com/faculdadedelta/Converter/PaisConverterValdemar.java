package br.com.faculdadedelta.Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.PaisDaoValdemar;
import br.com.faculdadedelta.modelo.PaisValdemar;

@FacesConverter(value = "paisConverter")
public class PaisConverterValdemar implements Converter {
	PaisDaoValdemar dao = new PaisDaoValdemar();
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		try {
			if(valor != null) {
				return dao.pesquisarPorId(Long.valueOf(valor));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if(valor != null) {
			return String.valueOf(((PaisValdemar)valor).getId());
		}
		return null;
	}
	
}
