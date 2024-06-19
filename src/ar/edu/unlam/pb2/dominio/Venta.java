package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.List;

public class Venta {
	
	private Cliente cliente;
	private List<CopaDelMundo> copas = new ArrayList<>();
	private Integer id;


	public Venta(Cliente cliente, List<CopaDelMundo> copasAVender) {
		this.cliente = cliente;
		this.copas = copasAVender;
	}

	public Venta(Cliente cliente, CopaDelMundo copa) {
		this.cliente = cliente;
		this.copas.add(copa);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<CopaDelMundo> getCopas() {
		return copas;
	}

	public Integer getId() {
		return id;
	}

	public void agregarCopas(List<CopaDelMundo> copasAVender) {
		this.copas.addAll(copasAVender);
	}

	public void agregarCopa(CopaDelMundo copa) {
		this.copas.add(copa);
	}

	public Double obtenerPrecioDeCopasDelMundoEstandarVendidas() {
		Double precioTotalDeCopasEstandarVendidas = 0.0;
		for(CopaDelMundo c: copas) {
			if(c instanceof CopaDelMundoEstandar) {
				precioTotalDeCopasEstandarVendidas += c.obtenerPrecio();
			}
		}
		return precioTotalDeCopasEstandarVendidas;
	}
	
}
