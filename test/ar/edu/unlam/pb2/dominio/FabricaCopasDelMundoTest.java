package ar.edu.unlam.pb2.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import ar.edu.unlam.pb2.dominio.*;
import ar.edu.unlam.pb2.enums.Colores;
import ar.edu.unlam.pb2.enums.Materiales;
import ar.edu.unlam.pb2.enums.*;
import ar.edu.unlam.pb2.excepciones.*;
public class FabricaCopasDelMundoTest {
	
	private FabricaDeCopasDelMundo fabrica;
	private final String NOMBRE_FABRICA = "Fabrica1";
	
	@Before
	public void init() {
		this.fabrica = new FabricaDeCopasDelMundo(NOMBRE_FABRICA);
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoSePuedeAgregarUnaCopaDelMundoEstandar() {
		Integer id = 1;
		Double precio = 2500.0;
		Integer stock = 60;
		CopaDelMundo copa = new CopaDelMundoEstandar(id,precio,Materiales.PLASTICO,stock);
		
		Boolean copaAgregada = fabrica.agregarCopaDelMundo(copa);
		
		assertTrue(copaAgregada);
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoSePuedeAgregarUnaCopaDelMundoPersonalizada() {
		
		
		
		CopaDelMundo copa = new CopaDelMundoPersonalizada(1,2500.0,Materiales.RECINA,Colores.CAOBA);
		
		
		Boolean copaAgregada = fabrica.agregarCopaDelMundo(copa);
		
		assertTrue(copaAgregada);
	}
	
	
	@Test
	public void queSePuedaAgregarUnClienteALaFabrica() throws ClienteDuplicadoException {
		Integer dni = 44239777;
		String nombre = "Pepe";
		String apellido = "Ruiz";
		Cliente cliente = new Cliente(dni,nombre,apellido);
		
		Boolean clienteAgregado = fabrica.agregarCliente(cliente);
		
		assertTrue(clienteAgregado);
	}
	
	@Test
	public void queSePuedaObtenerUnClient() throws ClienteDuplicadoException {
		Cliente cliente = new Cliente(44239777,"Pepe","Ruiz");
		fabrica.agregarCliente(cliente);
		
		Cliente clienteObtenido = fabrica.obtenerUnCliente(cliente.getDni());
		
		assertEquals(cliente,clienteObtenido);
	}

	@Test (expected = ClienteDuplicadoException.class)
	public void dadoQueExisteUnaFabricaDeCopasDelMundoAlAgregarUnClienteExistenteSeLanzaUnaClienteDuplicadoException() throws ClienteDuplicadoException {
		Cliente cliente = new Cliente(44239777,"Pepe","Ruiz");
		
		fabrica.agregarCliente(cliente);
		fabrica.agregarCliente(cliente);
	}

	@Test
	public void dadoQueExisteUnaFabricaQuePoseeCopasDelMundoSePuedenObtenerLasCopasDelMundoEstandar() {
		CopaDelMundo copa = new CopaDelMundoEstandar(1,2500.0,Materiales.PLASTICO,60);
		CopaDelMundo copa2 = new CopaDelMundoEstandar(2,2600.0,Materiales.PLASTICO,20);
		CopaDelMundo copa3 = new CopaDelMundoEstandar(3,2300.0,Materiales.RECINA,30);
		CopaDelMundo copa4 = new CopaDelMundoPersonalizada(4,2500.0,Materiales.RECINA,Colores.CAOBA);
		fabrica.agregarCopaDelMundo(copa);
		fabrica.agregarCopaDelMundo(copa2);
		fabrica.agregarCopaDelMundo(copa3);
		fabrica.agregarCopaDelMundo(copa4);
		
		List<CopaDelMundo> copasEstandar = fabrica.obtenerCopasDelMundoEstandar();
		
		assertEquals(3,copasEstandar.size());
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoPuedoObtenerUnaCopaDelMundoPorSuId() {
		CopaDelMundo copa = new CopaDelMundoEstandar(1,2500.0,Materiales.PLASTICO,60);
		fabrica.agregarCopaDelMundo(copa);
		
		CopaDelMundo copaObtenida = fabrica.obtenerCopaDelMundoPorId(copa.getId());
		
		assertEquals(copa,copaObtenida);
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoAlAgregarCincoCopasDelMundoAUnaVentaDeCopasDelMundoEstandarParaUnClienteSeDescuentanCincoUnidadesDelStockDeCopasDelMundoEstandar() throws ClienteDuplicadoException {
		CopaDelMundo copa = new CopaDelMundoEstandar(1,2500.0,Materiales.PLASTICO,60);
		CopaDelMundo copa2 = new CopaDelMundoEstandar(2,2600.0,Materiales.RECINA,20);
		fabrica.agregarCopaDelMundo(copa);
		fabrica.agregarCopaDelMundo(copa2);
		Cliente cliente = new Cliente(44239777,"Pepe","Ruiz");
		fabrica.agregarCliente(cliente);
		
		fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente, copa.getId(), 5);
		
		Integer stockEsperado = 55;
		assertEquals(stockEsperado,((CopaDelMundoEstandar)copa).getStock());
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoAlAgregarUnaVentaDeCopasDelMundoPersonalizadaParaUnClienteSeRemueveLaCopaDelMundoPersonalizadaDeLaFabrica() throws ClienteDuplicadoException {
		CopaDelMundo copa4 = new CopaDelMundoPersonalizada(4,2500.0,Materiales.RECINA,Colores.CAOBA);
		fabrica.agregarCopaDelMundo(copa4);
		Cliente cliente = new Cliente(44239777,"Pepe","Ruiz");
		fabrica.agregarCliente(cliente);
		fabrica.agregarCopaDelMundoPersonalizadaAVentaDeCliente(cliente, copa4.getId());
		
		CopaDelMundo copaObtenida = fabrica.obtenerCopaDelMundoPorId(copa4.getId());
		
		assertTrue(copaObtenida == null);
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoPersonalizadasSePuedeObtenerElPrecioDeUnaCopaDelMundoPersonalizada() {
		CopaDelMundo copa4 = new CopaDelMundoPersonalizada(4,2500.0,Materiales.RECINA,Colores.CAOBA);
		fabrica.agregarCopaDelMundo(copa4);
		Double costoManoObra = 2500.0 * 0.15;
		Double caoba = 2500.0 * 0.5;
		Double precioEsperado = 2500.0 + costoManoObra + caoba;
		
		Double precioObtenido = fabrica.obtenerPrecioDeCopaDelMundoPersonalizada(copa4.getId());
		
		assertEquals(precioEsperado,precioObtenido);
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConVentasDeCopasDelMundoEstandarYPersonalizadasVendidasAClientesSePuedeObtenerUnMapaConClaveClienteYTotalDeVentasDeCopasEstandarOrdenadoPorCliente() throws ClienteDuplicadoException {
		CopaDelMundo copa = new CopaDelMundoEstandar(1,2500.0,Materiales.PLASTICO,60);
		fabrica.agregarCopaDelMundo(copa);
		Cliente cliente = new Cliente(11111111,"Pepe","Ruiz");
		fabrica.agregarCliente(cliente);
		Cliente cliente2 = new Cliente(2222222,"Lucas","Gimenez");
		fabrica.agregarCliente(cliente2);
		fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente, copa.getId(), 5);
		fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente2, copa.getId(), 10);
		
		Map <Cliente,Double> mapa = new TreeMap<>();
		
		mapa = fabrica.obtenerTotalDePrecioDeCopasDelMundoEstandarVendidasAClientesOrdenadasPorCliente();
		
		int i = 0;
		for(Map.Entry<Cliente, Double> posicion : mapa.entrySet()) {
			if(i == 0) {
				assertEquals(posicion.getKey().getDni(), cliente.getDni());
				assertTrue(posicion.getValue() == (copa.obtenerPrecio() * 5));
			}
			if(i == 1) {
				assertEquals(posicion.getKey().getDni(), cliente2.getDni());
				assertTrue(posicion.getValue() == (copa.obtenerPrecio() * 10));
			}
			i++;
		}
	}
}
