package br.com.faculdadedelta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.ClienteDaoValdemar;
import br.com.faculdadedelta.modelo.ClienteValdemar;

@FacesConverter(value = "clienteConverter")
public class ClienteConverterValdemar implements Converter{
	
	private ClienteDaoValdemar dao = new ClienteDaoValdemar();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if(valor != null) {
			try {
				return dao.PesquisadoPorId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if(valor != null) {
			return String.valueOf(((ClienteValdemar)valor).getId());
		}
		return null;
	}

}
