// import java.rmi.*;
// import java.rmi.registry.*;

// public class DNSClient {
//     public static void main(String args[]) throws Exception {
//         Registry r = LocateRegistry.getRegistry("localhost", 1234);
//         DNSServerIntf d = (DNSServerIntf) r.lookup("mvsrserver");
//         String str = args[0];
//         System.out.println("The website name is:" + str);
//         System.out.println("The IP Address is:" + d.DNS(str));
//     }
// }



import java.rmi.*;
import java.rmi.registry.*;

public class DNSClient {
    public static void main(String args[]) throws Exception{
        Registry r = LocateRegistry.getRegistry("localhost",1234);
        DNSServerIntf d = (DNSServerIntf) r.lookup("mvsrserver");
        String str = args[0];
        System.out.println("The website is "+ str);
        System.out.println("The ip address is "+ d.DNS(str));
    }
}