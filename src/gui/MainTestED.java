package gui;

import dados.Queue;
import negocio.Validation;

public class MainTestED {

	public static void main(String[] args) {
		//class para tetar a estrutura de dados
		/*
		String entrada1 = "(~P v ~P v R v ~S v ~P) & (P) & (~S) & (Q v ~R v ~S v ~Q v ~P) & (Q v ~P v ~S v ~P v ~Q) & (~R v ~R v ~Q v Q) & (~S v Q v ~R v ~S v ~R)";
		String entrada2 = "(Q v ~Q v ~R v ~Q) & (R v ~P) & (S v ~Q v ~Q v ~R v ~S) & (R)";
		String entrada3 = "(Q v ~Q v ~R v ~Q) & (R v ~P) & (S v ~Q v ~Q v ~R v ~S)";
		String entrada4 = "(Q v ~Q v ~R v ~Q) ";
	*/
		
		/*
		queue.insert(entrada1, entrada1.length());
		queue.insert(entrada3,entrada3.length());
		queue.insert(entrada2,entrada2.length());
		queue.insert(entrada4,entrada4.length());
		(~Q v P) & (P) 203
		(~R) & (~R) 344
		(~P) & (Q v ~Q) & (~S v P) 397
		(Q v ~R) & (R) 602
		(~P v R) & (R) 815

	1000
		
		*/
		
		String expression = "(~Q v ~S v ~S v S) & (R) & (~R v ~S v P v ~Q v ~P) & (Q v ~Q v ~Q v ~R v ~S) & (~R) & (P v ~R v ~S v ~P) & (~Q v ~R) & (Q v ~P v ~R v ~Q v ~R) & (Q v ~S)";
		
		Validation validation = new Validation();
		boolean a = validation.valuation(expression);
		//System.out.println(a);
		if(a == false) {
			System.out.println("nao tem contradicao");
			System.out.println("Satisfativel");
		}else {
			System.out.println("Insatisfativel");
			System.out.println("tem contradicao");
		}
		//System.out.println(validation.valuation(expression));
		
	}

}
