package negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;



public class File {
	
	private String fileIn;
	
	private String fileOut;
	
	private FileReader file;
	
	private BufferedReader readArq;
	
	private BufferedWriter fileWriter;
	
	private PrintWriter printWriter;
			
	private int rawOne;
	
	private Validation validation = new Validation();
	
	public File(String fileIn,String fileOut) {
		this.fileIn = fileIn;
		this.fileOut = fileOut;
	}

	

	public void openReadWriteCloseFile() {
		try {
			file = new FileReader(this.fileIn);
			readArq = new BufferedReader(file);
			fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOut),"UTF-8" ));
			printWriter = new PrintWriter(fileWriter);
			
			String linha = readArq.readLine();
			rawOne = Integer.parseInt(linha);
			int cont = 1;
			
			while(cont <= rawOne) {
				String linhaAux = readArq.readLine();
				printWriter.printf("Problema #"+cont+"%n");
				
				if(validation.isFnc(linhaAux)) {
					
					if(validation.isHorn(linhaAux)) {
						
						if(validation.valuation(linhaAux)) {
							
							printWriter.printf("Não, não é satisfatível.%n");
							printWriter.printf("%n");
						}else {
							
							printWriter.printf("Sim, é satisfatível.%n");
							printWriter.printf("%n");
						}
					}else {
						
						printWriter.printf("Nem todas as cláusulas são de Horn.%n");
						printWriter.printf("%n");
					}
				}else {
					printWriter.printf("Não está na FNC.%n");
					printWriter.printf("%n");
				}
				cont++;
				
			}
			
			readArq.close();
			file.close();
			printWriter.close();
			fileWriter.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}
