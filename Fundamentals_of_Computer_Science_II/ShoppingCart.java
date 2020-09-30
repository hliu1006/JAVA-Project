//CS211
//Name: Hong Liu
//Date: April 19th
//Assignment 3#

// A class to stores information.
import java.util.*;
public class ShoppingCart {
    private ArrayList<ItemOrder> shopCart = new ArrayList<ItemOrder>();;
    private boolean value = false;
    
    //This method would construct a empty catalog.
    public ShoppingCart() {

    }
    
    //This method would add an item order to the list and replace any previous order for this item with the new order..
    public void add(ItemOrder other) {
        if (shopCart.size() == 0) {
            shopCart.add(other);
        } else {
            for (int i = 0; i < shopCart.size(); i++){
                if (shopCart.get(i).getItem() == other.getItem()) {
                    shopCart.set(i, other);
                    return;
                }
            }
        shopCart.add(other);
       }
   } 

    //This method would set whether or not this order gets a discount.
    public void setDiscount(boolean value){
       this.value = value;
   }
       
    //This method would return the total cost of the shopping cart.
    public double getTotal() {
        double total = 0;
        for (int i = 0; i < shopCart.size(); i++){
            ItemOrder good = shopCart.get(i);
            total += good.getPrice();
        }
        if (value == true) {
            total *= 0.9;
        }
        total = (int)(total * 100);
        return total / 100;
    }   
}
