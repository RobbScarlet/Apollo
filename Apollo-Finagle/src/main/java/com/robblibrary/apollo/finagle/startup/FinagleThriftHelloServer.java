package com.robblibrary.apollo.finagle.startup;

import com.robblibrary.apollo.finagle.server.HelloServerImpl;
import com.robblibrary.apollo.finagle.thrift.hello.HelloServer;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.thrift.ThriftServerFramedCodec;
import com.twitter.server.AbstractTwitterServer;
import com.twitter.util.Await;
import com.twitter.util.Duration;
import com.twitter.util.TimeoutException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.InetSocketAddress;
import java.util.Arrays;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-06-13
 */
public class FinagleThriftHelloServer extends AbstractTwitterServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FinagleThriftHelloServer.class);

    public static class Main {
        public static void main(String[] args) throws TimeoutException, InterruptedException {
            new HelloTwitterServer().start();
            new FinagleThriftHelloServer().main(args);
        }
    }

    @Override
    public void onInit() {
        log().info("Java Server initialization...");
    }

    @Override
    public void main() throws Throwable {
        log().info("on-main for " + name());
        log().info("args:" + Arrays.asList(args()));

        // Make sure that public fields/methods from AbstractTwitterServer are available here
        statsReceiver();
        defaultHttpPort();
    }
    @Override
    public Duration defaultCloseGracePeriod(){
        return Duration.fromSeconds(10000);
    }

    @Override
    public void postMain() {
//        ListeningServer server = Thrift.server().serveIface("localhost:8888", service);
        try {
            Await.ready(adminHttpServer());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onExit(){
        log().info("on-exit for " + name() + ".......");
    }
}

class HelloTwitterServer extends Thread{
    HelloTwitterServer(){
        setDaemon(true);
    }

    public void run(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/application-context.xml");
        ctx.start();

        int port = 8888;
        String serviceName = "helloServer";
        HelloServer.ServiceIface helloServer = ctx.getBean(HelloServerImpl.class);

        ServerBuilder serverBuilder = ServerBuilder.get().name(serviceName).codec(ThriftServerFramedCodec.get())
                .bindTo(new InetSocketAddress(port));

        TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
        HelloServer.Service service = new HelloServer.Service(helloServer, protocolFactory);
        ServerBuilder.safeBuild(service, serverBuilder);
    }
}
