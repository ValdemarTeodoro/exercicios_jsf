package br.com.faculdadedelta.Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.UFDaoValdemar;
import br.com.faculdadedelta.modelo.UFValdemar;

@FacesConverter(value = "ufConverter")
public class UFConverterValdemar implements Converter {
	UFDaoValdemar dao = new UFDaoValdemar();
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
			return String.valueOf(((UFValdemar)valor).getId());
		}
		return null;
	}
	
}
