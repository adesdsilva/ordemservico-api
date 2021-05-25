package com.sunsytem.enums;

public enum Prioridade {
	
	BAIXA(0, "Baixa"),
	MEDIA(1, "Media"),
	ALTA(2, "Alta");
	
	private Integer codigo;
	private String descricao;
	
	private Prioridade(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Prioridade toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		for(Prioridade p : Prioridade.values()) {
			if(codigo.equals(p.getCodigo())) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Prioridade inválida!" + codigo);
	}

	
}















