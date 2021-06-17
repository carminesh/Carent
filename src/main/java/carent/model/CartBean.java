package carent.model;

import carent.utils.Utility;

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
        if (isFull())
            return false;
        return cart.add(item);
    }

    public boolean isFull () {
        return cart.size()==3;
    }

    public long getTotal () {
        Utility.print("Sono entrato nel metodo getTotal");
        if (isEmpty())
            return 0;
        else {
            long result=0;
            CartItemBean bean;
            Iterator<?> it = cart.iterator();
            while (it.hasNext()) {
                bean = (CartItemBean) it.next();
                result+=bean.getPrezzoTotale();
            }
            return result;
        }
    }

    public boolean removeFromCart (String plate) {
        Iterator<CartItemBean> it = cart.iterator();
        while (it.hasNext()) {
            CartItemBean cartItemBean = (CartItemBean) it.next();
            if (cartItemBean.getAuto().getTarga().equals(plate)) {
                return cart.remove(cartItemBean);
            }
        }
        return false;
    }

    public Collection<CartItemBean> getCart () {
        return this.cart;
    }
}
