namespace java com.robblibrary.apollo.finagle.thrift.hello

include "result.thrift"

service HelloServer{

    result.StringResult sayHello(1:string name);
}