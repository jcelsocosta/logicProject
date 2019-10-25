package dados;

public class Node {

	public String value;
	public int length;
	public Node next;
	public Node last;
	
	public Node() {
		
	}
	
	public Node(String value,int length,Node next,Node last) {
		this.value = value;
		this.length = length;
		this.next = next;
		this.last = last;
	}
}
