package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.Colores;
import ar.edu.unlam.pb2.enums.Materiales;

public class CopaDelMundoPersonalizada extends CopaDelMundo{

	private Colores color;
	private final Double CAOBA = super.getPrecio() * 0.5;
	private final Double CEDRO = super.getPrecio() * 0.10;
	private final Double ROBLE_OSCURO = super.getPrecio() * 0.15;
	private final Double COSTO_MANO_OBRA = super.getPrecio() * 0.15;

	public CopaDelMundoPersonalizada(Integer id, Double precio, Materiales plastico, Colores caoba) {
		super(id, precio, plastico);
		this.color = caoba;
		
	}

	@Override
	public Double obtenerPrecio() {
		if(this.color.equals(Colores.CAOBA)) {
			return super.getPrecio() + COSTO_MANO_OBRA+ CAOBA;
		}
		if(this.color.equals(Colores.CEDRO)) {
			return super.getPrecio() + COSTO_MANO_OBRA+ CEDRO;
		}
		return super.getPrecio() + COSTO_MANO_OBRA+ ROBLE_OSCURO;
	}


}
