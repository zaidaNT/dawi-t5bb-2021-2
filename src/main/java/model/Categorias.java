package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_categorias")

public class Categorias {
	@Id
	@Column(name = "idcategoria")
	private int idcategoria;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Override
	public String toString() {
		return "Categorias [idcategoria=" + idcategoria + ", descripcion=" + descripcion + "]";
	}
	
	public Categorias() {
		super();
	}
	
	public Categorias(int idcategoria, String descripcion) {
		super();
		this.idcategoria = idcategoria;
		this.descripcion = descripcion;
	}
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
}
