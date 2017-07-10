package CorbaApp2.Implementations;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Properties;

/**
 * Created by Mateusz on 2017-06-21.
 */
public class Serwer {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("org.omg.CORBA.ORBInitialPort", "900");

        ORB orb = ORB.init(args, props);
        ShoppingCartServant sc = new ShoppingCartServant();
        orb.connect(sc);

        try {

            Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);
            NameComponent componentShoppingCart = new NameComponent("ShoppingCart", "");
            NameComponent[] pathSC = {componentShoppingCart};

            ncRef.rebind(pathSC, sc);

        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
        System.out.println("Serwer działa i ma się dobrze!");
        java.lang.Object sync = new java.lang.Object();
        synchronized (sync) {
            try {
                sync.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
