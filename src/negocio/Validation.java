package negocio;

import dados.Queue;

public class Validation {

	private String expression;
	private int length;
	private int[] array;

	public Validation() {

	}

	public Validation(String expression) {

		this.expression = expression;
		this.length = this.expression.length();
	}

	public String getExpression() {
		return this.expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public int getLength() {
		return this.length;
	}

	/**
	 * 
	 */

	public int countParentheses(String expression) {
		int auxLength = expression.length();
		int cont = 0;
		for (int i = 0; i < auxLength; i++) {
			if (expression.charAt(i) == '(' || expression.charAt(i) == ')') {
				cont++;
			}
		}
		return cont;
	}

	/**
	 * record in array position the parentheses expression
	 */

	public void indexClause(String expression) {
		int auxLength = expression.length();
		int init = countParentheses(expression);
		int j = 0;
		array = new int[init];

		for (int i = 0; i < auxLength; i++) {
			if (expression.charAt(i) == '(') {
				array[j] = i;
				j++;
			} else if (expression.charAt(i) == ')') {

				array[j] = i;
				j++;
			}

		}

	}

	/**
	 * count characters == v (OR)
	 */

	public int isLegit(String expression) {
		int auxLength = expression.length();
		int cont = 0;
		for (int i = 0; i < auxLength; i++) {
			if (expression.charAt(i) == 'v') {
				cont++;
			}
		}
		return cont;
	}

	/**
	 * unit expression (~P) or (P)
	 */

	public boolean isUnitary(String expression) {
		int auxLength = expression.length();
		if (auxLength == 4) {
			if (expression.charAt(0) == '(' && expression.charAt(1) == '~' && expression.charAt(2) >= 'P'
					&& expression.charAt(2) <= 'S' && expression.charAt(3) == ')') {
				return true;
			}

		}

		if (auxLength == 3) {
			if (expression.charAt(0) == '(' && expression.charAt(1) >= 'P' && expression.charAt(1) <= 'S'
					&& expression.charAt(2) == ')') {
				return true;
			}
		}

		return false;
	}

	/**
	 * First, if expression less than length 4, so call method isUnitary Second, if
	 * an expression between parentheses have two more than literal positive
	 */

	public boolean isHorn(String expression) {

		int auxLength = expression.length();
		int auxArray = array.length / 2;
		int j = 0;
		int end = 0;
		int contNegative = 0;
		int contVariable = 0;
		if (auxLength == 4 || auxLength == 3) {
			if (isUnitary(expression)) {
				return true;
			}
		} else if (auxLength > 4) {
			while (end < auxArray) {
				contVariable = 0;
				contNegative = 0;
				for (int i = array[j]; i <= array[j + 1]; i++) {
					if (expression.charAt(i) >= 'P' && expression.charAt(i) <= 'S') {
						contVariable++;

					} else if (expression.charAt(i) == '~') {
						contNegative++;
					}

				}

				j = j + 2;
				end++;
				if ((contVariable - contNegative) >= 2) {

					return false;
				}
			}

		}

		return true;
	}

	/**
	 * Check expression with an objective find any characters invalid
	 */

	public boolean isFnc(String expression) {
		indexClause(expression);
		int auxLength = expression.length();
		for (int i = 0; i < auxLength; i++) {
			if (expression.charAt(i) == '>' || expression.charAt(i) == '<') {
				return false;
			}
		}
		for (int i = 0; i < auxLength - 3; i++) {
			if (expression.charAt(i) == '&') {
				if (expression.charAt(i - 2) != ')' && expression.charAt(i + 2) != '(') {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check if all literal are negative
	 */

	public boolean isNegative(String expression) {
		int auxLength = expression.length();
		int countVariable = 0, countNot = 0;
		for (int i = 0; i < auxLength; i++) {
			if (expression.charAt(i) >= 'P' && expression.charAt(i) <= 'S') {
				countVariable++;
			}
			if (expression.charAt(i) == '~') {
				countNot++;
			}
		}
		if (countVariable == countNot)
			return true;

		return false;
	}

	/**
	 * Match! clause unit on expression
	 */

	public boolean clauseUnit(String expression) {
		int auxLength = expression.length() - 4;
		for (int i = 0; i < auxLength; i++) {
			if (expression.charAt(i) == '(' && (expression.charAt(i + 1) >= 'P' || expression.charAt(i + 1) <= 'S')
					&& expression.charAt(i + 2) == ')') {
				return true;
			} else if (expression.charAt(i) == '(' && expression.charAt(i + 1) >= '~'
					&& (expression.charAt(i + 2) >= 'P' || expression.charAt(i + 2) <= 'S')
					&& expression.charAt(i + 3) == ')') {
				return true;
			}
		}
		return false;
	}

	/**
	 * for each expression record in array, add in queue with an objective find
	 * contradiction
	 */
	public boolean valuation(String expression) {
		indexClause(expression);
		int length = expression.length();
		Queue queue = new Queue();
		String buffer;
		for (int i = 0; i <= array.length - 2; i = i + 2) {
			buffer = expression.substring(array[i], array[i + 1] + 1);
			int len = ((array[i + 1] + 1) - array[i]);
			queue.insert(buffer, len);
			//System.out.println(array.length);
		}
		//System.out.println("print()");
		//queue.print();
		boolean flag = queue.findContradiction();
		 
		if (flag) {

			return true;
		}
		return false;
	}

	/**
	 * First, all expression negative (~A v ~B) & (~A) Second, if not exist clause
	 * unit Third, unit clause (A) & (~A v B) Fourth, normal expression (A v ~B) &
	 * (~A v B)
	 */

	public boolean isSatisfactory(String expression) {
		int auxLength = expression.length();

		if (isNegative(expression) == false) {
			return false;
		} else if (clauseUnit(expression) == false) {

			return true;

		} else {
			if (valuation(expression)==true) {
				return true;
			}
		}
		return false;
	}

}
