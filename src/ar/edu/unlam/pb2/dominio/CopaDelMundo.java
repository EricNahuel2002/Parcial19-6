package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

import ar.edu.unlam.pb2.enums.Materiales;

public abstract class CopaDelMundo {

	private Integer id;
	private Double precio;
	private Materiales material;
	
	public CopaDelMundo(Integer id, Double precio, Materiales plastico) {
		this.id = id;
		this.precio = precio;
		this.material = plastico;
	}
	
	public abstract Double obtenerPrecio();

	public Integer getId() {
		return id;
	}

	public Double getPrecio() {
		return precio;
	}

	public Materiales getMaterial() {
		return material;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, material, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CopaDelMundo other = (CopaDelMundo) obj;
		return Objects.equals(id, other.id) && material == other.material && Objects.equals(precio, other.precio);
	}

	
}
