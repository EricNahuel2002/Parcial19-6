package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ar.edu.unlam.pb2.excepciones.*;

public class FabricaDeCopasDelMundo {

	private String nOMBRE_FABRICA;
	private List<CopaDelMundo> copasDelMundo;
	private List<Cliente> clientes;
	private List<Venta> ventas;

	public FabricaDeCopasDelMundo(String nOMBRE_FABRICA) {
		this.nOMBRE_FABRICA= nOMBRE_FABRICA;
		this.copasDelMundo = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.ventas = new ArrayList<>();
	}

	public Boolean agregarCopaDelMundo(CopaDelMundo copaDelMundo) {
		return copasDelMundo.add(copaDelMundo);
	}

	public Boolean agregarCliente(Cliente cliente) throws ClienteDuplicadoException {
		Cliente encontrado = this.obtenerUnCliente(cliente.getDni());
		if(encontrado != null) {
			throw new ClienteDuplicadoException();
		}
		return clientes.add(cliente);
	}

	public List<CopaDelMundo> obtenerCopasDelMundoEstandar() {
		List<CopaDelMundo> copasEstandar = new ArrayList<>();
		for(CopaDelMundo c: copasDelMundo) {
			if(c instanceof CopaDelMundoEstandar) {
				copasEstandar.add(c);
			}
		}
		return copasEstandar;
	}

	public CopaDelMundo obtenerCopaDelMundoPorId(Integer id) {
		for(CopaDelMundo c: copasDelMundo) {
			if(c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	public void agregarCopaDelMundoEstandarAVentaDeCliente(Cliente clienteDeVenta, Integer idCopaDelMundo,
			Integer cantidadAVender) {
		Cliente cliente = this.obtenerUnCliente(clienteDeVenta.getDni());
		CopaDelMundo copa = this.obtenerCopaDelMundoPorId(idCopaDelMundo);
		Venta ventaEncontrada = this.obtenerVentaPorCliente(cliente);
		List<CopaDelMundo> copasAVender = new ArrayList<>();
		for(int i = 0; i < cantidadAVender; i++) {
			copasAVender.add(copa);
		}
		if(ventaEncontrada == null) {
				((CopaDelMundoEstandar)copa).disminuirStock(cantidadAVender);
				this.ventas.add(new Venta(cliente,copasAVender));
		}else {
		ventaEncontrada.agregarCopas(copasAVender);
		((CopaDelMundoEstandar)copa).disminuirStock(cantidadAVender);
		}
	}

	

	public void agregarCopaDelMundoPersonalizadaAVentaDeCliente(Cliente clienteDeVenta, Integer idCopaDelMundo) {
		Cliente cliente = this.obtenerUnCliente(clienteDeVenta.getDni());
		CopaDelMundo copa = this.obtenerCopaDelMundoPorId(idCopaDelMundo);
		Venta ventaEncontrada = this.obtenerVentaPorCliente(cliente);
		if(ventaEncontrada == null) {
		this.ventas.add(new Venta(cliente,copa));
		this.copasDelMundo.remove(copa);
		}else {
		ventaEncontrada.agregarCopa(copa);
		this.copasDelMundo.remove(copa);
		}
	}

	public Double obtenerPrecioDeCopaDelMundoPersonalizada(Integer id)  {
		CopaDelMundo c = this.obtenerCopaDelMundoPorId(id);
		return ((CopaDelMundoPersonalizada)c).obtenerPrecio();
	}

	public Map<Cliente, Double> obtenerTotalDePrecioDeCopasDelMundoEstandarVendidasAClientesOrdenadasPorCliente() {
		Map<Cliente, Double> aux = new TreeMap<>();
		for(Venta v: ventas) {
			aux.put(v.getCliente(), v.obtenerPrecioDeCopasDelMundoEstandarVendidas());
		}
		return aux;
	}

	private Venta obtenerVentaPorCliente(Cliente cliente) {
		for(Venta v: ventas) {
			if(v.getCliente().equals(cliente)) {
				return v;
			}
		}
		return null;
	}

	public Cliente obtenerUnCliente(Integer dni) {
		for(Cliente c: clientes) {
			if(c.getDni().equals(dni)) {
				return c;
			}
		}
		return null;
	}


}
