package com.robblibrary.apollo.finagle;

import com.robblibrary.apollo.finagle.thrift.hello.HelloServer;
import com.robblibrary.apollo.finagle.thrift.result.StringResult;
import com.twitter.finagle.Thrift;
import com.twitter.util.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-06-13
 */
public class HelloServerClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServerClientTest.class);

    public static void main(String[] args) throws Exception {
        HelloServer.ServiceIface client = Thrift.client().newIface("localhost:8888", HelloServer.ServiceIface.class);

        Future<StringResult> response;
        for(int i=1; i<10000; i++){
            if (i % 888 == 0){
                response = client.sayHello(null);
            }else{
                response = client.sayHello("Remilia");
            }
            if(response != null){
                System.out.println(response.toJavaFuture().get());
            }
        }

    }


}
