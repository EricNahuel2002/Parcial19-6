package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.Materiales;

public class CopaDelMundoEstandar extends CopaDelMundo{


	private Integer stock;
	private final Double COSTO_MANO_OBRA = super.getPrecio() * 0.20;

	public CopaDelMundoEstandar(Integer id, Double precio, Materiales plastico, Integer stock) {
		super(id,precio,plastico);
		this.stock = stock;
	}

	@Override
	public Double obtenerPrecio() {
		return super.getPrecio() + COSTO_MANO_OBRA;
	}

	public void disminuirStock(Integer cantidadAVender) {
		this.stock -= cantidadAVender;
	}

	public Integer getStock() {
		return stock;
	}
	
}
