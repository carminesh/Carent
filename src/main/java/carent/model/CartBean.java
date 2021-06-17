package carent.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class CartBean {
    private Collection<CartItemBean> cart;

    public CartBean () {
        cart = new LinkedList<CartItemBean>();
    }

    public boolean isInCart (String plate) {
        Iterator<?> it = cart.iterator();
        CartItemBean bean;
        while (it.hasNext()) {
            bean= (CartItemBean) it.next();
            if (bean.getAuto().getTarga().equals(plate))
                return true;
        }
        return false;
    }

    public boolean isEmpty () {
        int cont=0;
        Iterator<?> it = cart.iterator();
        while (it.hasNext()) {
            it.next();
            ++cont;
            return false;
        }
        return cont==0;
    }

    public boolean add (CartItemBean item) {
        return cart.add(item);
    }
}
