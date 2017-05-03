package sopa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sopa {

	private static String miPathIn = "src/entrada/";
	private static String miPathOut = "src/salida/";

	private int dimension;
	private int cantidadPalabras;
	private String[][] sopa;
	private String[][] palabras;
	private String[] resultado;
	private int[] cantidadesLetrasPorPalabra;

	public Sopa(String path) {

		try {
			Scanner sc = new Scanner(new File(miPathIn + path + ".in"));
			dimension = sc.nextInt();
			cantidadPalabras = sc.nextInt();
			cantidadesLetrasPorPalabra = new int[cantidadPalabras];
			if (cantidadPalabras > 10000) {
				extracted();
			}
			sopa = new String[dimension][dimension];
			palabras = new String[cantidadPalabras][20];
			resultado = new String[cantidadPalabras];

			String aux;

			for (int i = 0; i < dimension; i++) {
				aux = sc.next();
				for (int j = 0; j < dimension; j++) {
					sopa[i][j] = aux.substring(j, j + 1);
				}
			}

			for (int i = 0; i < cantidadPalabras; i++) {
				aux = sc.next();
				cantidadesLetrasPorPalabra[i] = aux.length();
				for (int j = 0; j < aux.length(); j++) {
					palabras[i][j] = aux.substring(j, j + 1);
				}
			}
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void extracted() {
		throw new ArithmeticException("Cantidad exagerada");
	}

	public void buscar() {

		int posicionLinea = 0;
		int posicionLetra = 0;
		int cantidadEncontrados = 0;
		boolean encontrado;

		while (posicionLinea < this.cantidadPalabras) {
			int i = 0;
			encontrado = false;
			while (i < dimension && !encontrado) {
				int j = 0;
				while (j < dimension && !encontrado) {
					if (sopa[i][j].equals(palabras[posicionLinea][posicionLetra])) {
						int m = i, n = j;
						// Busco hacia la derecha
						while (!encontrado && n + 1 < dimension
								&& sopa[m][n + 1].equals(palabras[posicionLinea][posicionLetra + 1])) {
							posicionLetra++;
							n++;
							if (posicionLetra+1 == cantidadesLetrasPorPalabra[posicionLinea]) {
								encontrado = true;
								resultado[cantidadEncontrados] = (posicionLinea + 1) + " E";
								cantidadEncontrados++;
							}
						}
						posicionLetra = 0;
						n = j;

						// Busco hacia abajo
						while (!encontrado && m + 1 < dimension
								&& sopa[m + 1][n].equals(palabras[posicionLinea][posicionLetra + 1])) {
							posicionLetra++;
							m++;
							if (posicionLetra+1 == cantidadesLetrasPorPalabra[posicionLinea]) {
								encontrado = true;
								resultado[cantidadEncontrados] = (posicionLinea + 1) + " S";
								cantidadEncontrados++;
							}
						}
						posicionLetra = 0;
						m = i;

						// Busco hacia la izquierda
						while (!encontrado && n - 1 > 0
								&& sopa[m][n - 1].equals(palabras[posicionLinea][posicionLetra + 1])) {
							posicionLetra++;
							n--;
							if (posicionLetra+1 == cantidadesLetrasPorPalabra[posicionLinea]) {
								encontrado = true;
								resultado[cantidadEncontrados] = (posicionLinea + 1) + " O";
								cantidadEncontrados++;
							}
						}
						posicionLetra = 0;
						n = j;

						// Busco hacia arriba
						while (!encontrado && m - 1 > 0
								&& sopa[m - 1][n].equals(palabras[posicionLinea][posicionLetra + 1])) {
							posicionLetra++;
							m--;
							if (posicionLetra+1 == cantidadesLetrasPorPalabra[posicionLinea]) {
								encontrado = true;
								resultado[cantidadEncontrados] = (posicionLinea + 1) + " N";
								cantidadEncontrados++;
							}
						}
					}
					j++;
				}
				i++;
			}
			posicionLinea++;
		}
	}

	public void guardar(String path) {
		PrintWriter salida;
		try {
			salida = new PrintWriter(new FileWriter(miPathOut + path + ".out"));

			for (int k = 0; k < resultado.length; k++) {
				salida.println(resultado[k]);
			}
			salida.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
