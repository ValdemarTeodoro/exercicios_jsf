package br.com.faculdadedelta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.FornecedorDaoValdemar;
import br.com.faculdadedelta.modelo.FornecedorValdemar;

@FacesConverter(value = "fornecedorConverter")
public class FornecedorConverterValdemar implements Converter {
	
	FornecedorDaoValdemar dao = new FornecedorDaoValdemar();
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
			return String.valueOf(((FornecedorValdemar)valor).getId());
		}
		return null;
	}
	
}
