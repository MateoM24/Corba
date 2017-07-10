package CorbaApp2.Implementations;

import CorbaApp2.*;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Properties;

/**
 * Created by Mateusz on 2017-06-21.
 */
public class Client {
    public static void main(String[] args) throws org.omg.CORBA.ORBPackage.InvalidName {

        Properties props = new Properties();
        props.put("org.omg.CORBA.ORBInitialPort", "900");

        ORB orb = ORB.init(args, props);
        Object objRef = orb.resolve_initial_references("NameService");
        NamingContext ncRef = NamingContextHelper.narrow(objRef);
        NameComponent componentShoppingCart = new NameComponent("ShoppingCart", "");
        NameComponent[] pathSC = {componentShoppingCart};

        try {
            ShoppingCart shoppingCart = ShoppingCartHelper.narrow(ncRef.resolve(pathSC));
            Customer customerJa = new Customer(24,"Mateusz Mężyk");
            shoppingCart.customer(customerJa);
            System.out.println("ShoppingCart zainicjalizowany!\nTeraz tworzymy produkty...");

            Product woda = new Product("Woda mineralna Cisowianka",1,10,1.6);
            System.out.println("podukt: " + woda.name + "\tprice " + woda.price+ "\tstock: " + woda.stock);
            Product kurczaki = new Product("Kurczaki z Kentucky",1,15,19.99);
            System.out.println("podukt: " + kurczaki.name + "\tprice " + kurczaki.price + "\tstock: " + kurczaki.stock);

            System.out.println("Dodajemy oba produkty do koszyka");
            shoppingCart.addToCart(woda, 3,customerJa);
            shoppingCart.addToCart(kurczaki, 10,customerJa);

            System.out.println("Wyciągamy pare sztuk produktu z koszyka");
            shoppingCart.removeFromCart(kurczaki,2,customerJa);
            System.out.println("Podsumujmy..\n" + shoppingCart.sumItUp(customerJa));
            shoppingCart.clear(customerJa);

            System.out.println("nowy koszyk, inny klient");
            Customer customerInny = new Customer(77,"John Stockton");
            Product mleko = new Product("Mleko",1,50,3.30);
            shoppingCart.customer(customerInny);
            shoppingCart.addToCart(mleko,23,customerInny);
            System.out.println("innny klient to:::::::::: "+customerInny.fullName+", "+customerInny.customerId);
            System.out.println("Podsumujmy..\n" + shoppingCart.sumItUp(customerInny));
            shoppingCart.clear(customerInny);

            System.out.println("\nA teraz zasymulujmy nie wystarczającą iość produktu...");
            Product wegiel = new Product("Węgiel do grila",1,2,5.79);
            int ileChceZamowic = 3;
            shoppingCart.addToCart(wegiel, ileChceZamowic,customerJa);
            System.out.println(String.format("Ilość węgla na magazynie:%1$d\tKlient zamawia:%2$d", wegiel.stock, ileChceZamowic));

        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (StockIsEmpty stockIsEmpty) {
            stockIsEmpty.getMessage();
            stockIsEmpty.printStackTrace();
        } catch (NoEnoughItems noEnoughItems) {
            noEnoughItems.getMessage();
            noEnoughItems.printStackTrace();
        }
    }
}
