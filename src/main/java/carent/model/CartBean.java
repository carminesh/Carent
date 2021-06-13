package carent.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

public class CartBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Collection<CartItemBean> carrello;
	
	public CartBean () {
		this.carrello = new LinkedList<CartItemBean>();
	}

	public Collection<CartItemBean> getCarrello() {
		return carrello;
	}

	public void setCarrello(Collection<CartItemBean> carrello) {
		this.carrello = carrello;
	}
	
	public void inserisci (CartItemBean item) {
		carrello.add(item);
	}
	
	public String toString () {
		String carrelloString = ""; 
		for (CartItemBean item: carrello)
			carrelloString+=item.toString()+"\n";
		return carrelloString;
	}
	
}
