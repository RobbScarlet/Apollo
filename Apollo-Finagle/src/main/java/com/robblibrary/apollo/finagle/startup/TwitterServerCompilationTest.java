package com.robblibrary.apollo.finagle.startup;

import com.twitter.app.Flag;
import com.twitter.app.Flaggable;
import com.twitter.server.AbstractTwitterServer;
import com.twitter.util.TimeoutException;

public class TwitterServerCompilationTest extends AbstractTwitterServer {

    public static class Main {
        public static void main(String[] args) throws TimeoutException, InterruptedException {
            new TwitterServerCompilationTest().main(args);

//            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/application-context.xml");
//            ctx.start();
//            HelloServer.ServiceIface service = ctx.getBean(HelloServerImpl.class);
//
//            ListeningServer server = Thrift.server().serveIface("localhost:8888", service);
//            Await.ready(server);
        }
    }

    private Flag<String> foo =
            flag().create("foo", "default-foo", "help-foo", Flaggable.ofString());

    @Override
    public void preMain() {
        log().info("pre-exit");
    }

    /**
     * This method runs automatically.
     */
    @Override
    public void main() throws Throwable {
        testA();

        // Make sure that public fields/methods from AbstractTwitterServer are available here
        statsReceiver();
        defaultHttpPort();
    }

    private void testA() {
        log().info("on-main for " + name());
        getInfo();
    }

    private void getInfo() {
        log().info("args:" + args());
    }

    @Override
    public void postMain() {
        log().info("post-main");
    }

    @Override
    public void onInit() {
        log().info("on-init");
    }

    @Override
    public void onExit() {
        log().info("on-exit");
    }
}
