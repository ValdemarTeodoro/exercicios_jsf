package br.com.faculdadedelta.modelo;

public class ProcessoValdemar {

		private Long id;
		private String numeroProcesso;
		private String descricaoProcesso;
		private double valorProcesso;
		public ProcessoValdemar() {
			super();
		}
		public ProcessoValdemar(Long id, String numeroProcesso, String descricaoProcesso, double valorProcesso) {
			super();
			this.id = id;
			this.numeroProcesso = numeroProcesso;
			this.descricaoProcesso = descricaoProcesso;
			this.valorProcesso = valorProcesso;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNumeroProcesso() {
			return numeroProcesso;
		}
		public void setNumeroProcesso(String numeroProcesso) {
			this.numeroProcesso = numeroProcesso;
		}
		public String getDescricaoProcesso() {
			return descricaoProcesso;
		}
		public void setDescricaoProcesso(String descricaoProcesso) {
			this.descricaoProcesso = descricaoProcesso;
		}
		public double getValorProcesso() {
			return valorProcesso;
		}
		public void setValorProcesso(double valorProcesso) {
			this.valorProcesso = valorProcesso;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ProcessoValdemar other = (ProcessoValdemar) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		
		
}
