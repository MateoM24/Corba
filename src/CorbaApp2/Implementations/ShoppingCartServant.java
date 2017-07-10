package CorbaApp2.Implementations;

import CorbaApp2.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Mateusz on 2017-06-21.
 */
public class ShoppingCartServant extends _ShoppingCartImplBase {

    private Customer customer;
    private OrderItem[] orders = new OrderItem[50];
    private Map<Integer, OrderItem[]> map = new ConcurrentHashMap<>();

    @Override
    public Customer customer() {return this.customer;}

    @Override
    public void customer(Customer newCustomer) {this.customer = newCustomer;}

    @Override
    public OrderItem[] orders() {return orders;}

    @Override
    public void addToCart(Product product, int qty, Customer cust) throws StockIsEmpty {
        getOrCreateOrderItemArray(cust);

        int qTyCheck = product.stock - qty;
        if ((qTyCheck) < 0) {
            throw new StockIsEmpty("Braki w magazynie");
        }

        int firstAvalaible = 0;
        while (orders[firstAvalaible] != null) {
            firstAvalaible++;
        }
        orders[firstAvalaible] = new OrderItem(product.price, qty, product);
    }

    @Override
    public void removeFromCart(Product product, int qty, Customer cust) throws NoEnoughItems {
        getOrderItemArray(cust);
        if (orders != null) {
            for (int i = 0; i < orders.length; i++) {
                if (orders[i] != null && orders[i].product.name.equals(product.name)) {
                    int actualQty = orders[i].quantity;
                    int finalQty = actualQty - qty;

                    if (finalQty < 0) {
                        throw new NoEnoughItems("Nie ma czego usuwać w koszyku");
                    } else if (finalQty > 0) {
                        orders[i].quantity = finalQty;
                    } else if (finalQty == 0) {
                        orders[i] = null;
                    }
                }
            }
        }
    }

    @Override
    public String sumItUp(Customer cust) {
        getOrCreateOrderItemArray(cust);
        double sum = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                sum += orders[i].price * orders[i].quantity;
            }
        }
        StringBuilder summary = new StringBuilder(String.format("Podsumowanie zakupów klienta %1$s\nŁączna suma wynosi:%2$.2f\n", customer.fullName, sum));
        summary.append("Kupione produkty");
        for (int i = 0; i < orders.length; i++) {
            OrderItem item = orders[i];
            if (item != null) {
                summary.append(String.format("\nProdukt: %s, cena: %2$2f, ilość: %3$d", item.product.name, item.price, item.quantity));
            }
        }
        System.out.println(summary.toString());
        return summary.toString();
    }
    @Override
    public void clear(Customer cust) {
        getOrderItemArray(cust);
        for (int i = 0; i < orders.length; i++) {
            orders[i] = null;
        }
    }


    private void getOrCreateOrderItemArray(Customer cust){
        OrderItem[] items = getOrderItemArray(cust);
        if (items==null){
            items = new OrderItem[50];
            map.put(cust.customerId,items);
        }
        orders = items;
    }
    private OrderItem[] getOrderItemArray(Customer cust){
        OrderItem[] items = map.get(cust.customerId);
        orders = items;
        return items;
    }
}
