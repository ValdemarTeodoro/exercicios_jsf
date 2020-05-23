package br.com.faculdadedelta.Converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.MunicipioDaoValdemar;
import br.com.faculdadedelta.modelo.MunicipioValdemar;

@FacesConverter(value = "municipioConverter")
public class MunicipioConverter implements Converter {
	MunicipioDaoValdemar dao = new MunicipioDaoValdemar();
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if(valor != null) {
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if(valor != null) {
			return String.valueOf(((MunicipioValdemar)valor).getId());
		}
		return null;
	}

}
