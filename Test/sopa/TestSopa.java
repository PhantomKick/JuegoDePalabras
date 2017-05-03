package sopa;

import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TestSopa {

	@Ignore
	public void testBasico() {
		Sopa sopa = new Sopa("01_basico");
		sopa.buscar();
		sopa.guardar("01_basico");
	}

	@Ignore
	public void testAmor() {
		Sopa sopa = new Sopa("02_amor");
		sopa.buscar();
		sopa.guardar("02_amor");
	}
	
	@Ignore
	public void testPaco() {
		Sopa sopa = new Sopa("03_paco");
		sopa.buscar();
		sopa.guardar("03_paco");
	}
	
	@Test
	public void testFatiga() {
		Sopa sopa = new Sopa("04_fatiga");
		sopa.buscar();
		sopa.guardar("04_fatiga");
	}
}
