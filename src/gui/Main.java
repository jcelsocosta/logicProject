package gui;

import negocio.File;

public class Main {

	public static void main(String[] args) {
		/**
		 * Method need two path, first the input and second the output
		 */
		File file = new File("C:\\Users\\celso\\Documents\\eclipse-projects\\metodoResolucao\\src\\gui\\Entrada.in","C:\\Users\\celso\\Documents\\eclipse-projects\\metodoResolucao\\src\\gui\\Saida.out");
		file.openReadWriteCloseFile();
	}

}
