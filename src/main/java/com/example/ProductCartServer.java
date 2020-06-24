package com.example;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class ProductCartServer {

    public static ProductCartHandler handlerCart = new ProductCartHandler();
    public static ProductManagerHandler handlerManager = new ProductManagerHandler();

    public static ProductCart.Processor processorCart = new ProductCart.Processor(handlerCart);
    public static ProductManager.Processor processorManager = new ProductManager.Processor(handlerManager);

    public static void main(String[] args) {
        new Thread(() -> simple(processorCart, processorManager)).start();
    }

    public static void simple(ProductCart.Processor productCartProcessor, ProductManager.Processor productManagerProcessor) {
        try {
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            TServerTransport t = new TServerSocket(9090);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(t).processor(processor));
            processor.registerProcessor("ProductCart", productCartProcessor);
            processor.registerProcessor("ProductManager", productManagerProcessor);
            System.out.println("starting server port:" + 9090);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
