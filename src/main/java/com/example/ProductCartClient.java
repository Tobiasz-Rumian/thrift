package com.example;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TSocket;

public class ProductCartClient {
    public static void main(String[] args) {
        try {
            TSocket transport = new TSocket("localhost", 9090);
            transport.open();
            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            TMultiplexedProtocol mp = new TMultiplexedProtocol(protocol, "ProductManager");
            ProductManager.Client productManagerClient = new ProductManager.Client(mp);
            TMultiplexedProtocol mp2 = new TMultiplexedProtocol(protocol, "ProductCart");
            ProductCart.Client cartClient = new ProductCart.Client(mp2);
            perform(productManagerClient, cartClient);
            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(ProductManager.Client productManagerclient, ProductCart.Client cartClient) throws TException {
        System.out.println(productManagerclient.getAllProducts());
        OrderItem it1 = new OrderItem(1, 123);
        cartClient.addItem(it1);
        it1.amount = 200;
        cartClient.updateItem(it1);
        OrderItem it2 = new OrderItem(2, 456);
        cartClient.addItem(it2);
        cartClient.removeItem(it2.id);
        cartClient.placeOrder();
        System.out.println(productManagerclient.getAllProducts());
    }
}
