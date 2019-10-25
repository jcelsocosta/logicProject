package dados;

public class Queue {

	private Node head;
	private Node sentinel;
	private Node tail;

	public Queue() {
		this.head = null;
		this.sentinel = null;
		this.tail = null;
	}

	/**
	 * insertion sort by size
	 */
	public void insert(String value, int length) {
		Node aux = head;
		Node temp = new Node();
		Node newNode = new Node();

		if (this.head == null) {
			newNode.value = value;
			newNode.length = length;
			newNode.next = null;
			newNode.last = null;
			this.head = newNode;
			this.tail = newNode;
		} else {
			if (length < aux.length) {
				newNode.value = value;
				newNode.length = length;
				newNode.next = aux;
				aux.last = newNode;
				head = newNode;
			} else {
				while (aux != null && length > aux.length) {
					temp = aux;
					aux = aux.next;
				}
				newNode.value = value;
				newNode.length = length;
				newNode.next = aux;
				temp.next = newNode;
				tail = newNode;
			}
		}

	}

	public int queueLength() {
		Node aux = head;
		int resul = 0;
		while (aux != null) {
			aux = aux.next;
			resul++;

		}
		return resul;
	}

	/**
	 * 
	 * @param subExpression1
	 * @param subExpression2
	 * @return
	 */
	public boolean compareLiteral(Node subExpression1, Node subExpression2) {
		String temp1 = subExpression1.value;
		String temp2 = subExpression2.value;
		String temp = null;
		String tempAux = null;
		int len1 = subExpression1.length, len2 = subExpression2.length;
		if (len1 == 3) {
			for (int i = 0; i < len1 - 1; i++) {
				if (temp1.charAt(i) >= 'P' && temp1.charAt(i) <= 'S') {
					temp1 = Character.toString(temp1.charAt(i));
					temp = "~" + temp1;
				}
			}
		} else if (len1 == 4) {
			for (int i = 0; i < len1 - 1; i++) {
				if (temp1.charAt(i) == '~') {
					int u = i + 1;
					temp = Character.toString(temp1.charAt(i + 1));
					
				}
			}
		}

		if (len1 == 4) {
			for (int i = 0; i < len2 - 1; i++) {
				if (temp2.charAt(i) >= 'P' && temp2.charAt(i) <= 'S') {
					tempAux = '~'+Character.toString(temp2.charAt(i));
					if (tempAux.equals(temp)) {
						return true;
					}
				}

			}
		}
		else if (len1 == 3) {
			for (int i = 0; i < len2; i++) {
				if (temp2.charAt(i) == '~') {
					if (temp2.charAt(i + 1) >= 'P' && temp2.charAt(i + 1) <= 'S') {
						tempAux = temp2.substring(i, i + 2);;
						if (tempAux.equals(temp)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public boolean findContradiction() {
		Node aux = head;
		Node temp = new Node();
		int len = queueLength(), i = 0;
		boolean flag = false;
		if (aux == null) {

			return false;
		} else {
			for (i = 0; i < len - 1; i++) {
				temp = aux;

				for (int j = i + 1; j < len; j++) {
					if (aux.next != null) {
						aux = aux.next;
					}
					flag = compareLiteral(temp, aux);
					if (flag) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void print() {
		Node aux = head;
		if (aux == null) {
			System.out.println("Fila vazia!");
		}
		while (aux != null) {
			System.out.println(aux.value+" "+ aux.length);
			aux = aux.next;
		}

	}

}
