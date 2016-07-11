package com.robblibrary.apollo.finagle.server;

import com.robblibrary.apollo.finagle.thrift.hello.HelloServer;
import com.robblibrary.apollo.finagle.thrift.result.Code;
import com.robblibrary.apollo.finagle.thrift.result.Result;
import com.robblibrary.apollo.finagle.thrift.result.StringResult;
import com.twitter.util.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-06-13
 */
@Controller
public class HelloServerImpl implements HelloServer.ServiceIface{
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServerImpl.class);

    @Override
    public Future<StringResult> sayHello(String name) {
        StringResult stringResult = new StringResult();
        Result result = new Result();
        result.setCode(Code.SUCCESS);
        stringResult.setResult(result);
        if(name == null){
            return null;
        }
        stringResult.setStr("Hello, " + name);
        return Future.value(stringResult);
    }
}
