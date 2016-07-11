namespace java com.robblibrary.apollo.finagle.thrift.result

enum Status{
    /* 失败*/
    FAIL = 0,
    /* 成功*/
    SUCC = 1
}
/* 失败信息*/
struct Failure{
    /* 错误码*/
    1:i32       code,
    /* 错误名称*/
    2:string    name,
    /* 错误描述*/
    3:string    description
}
/* 返回结果*/
struct Result{
    /* 状态码*/
    1:Status status,
    /* 错误信息:当状态码不为SUCC的时候*/
    2:optional list<Failure> failureList
}
/* 返回结果:字符串*/
struct StringResult{
    1:Result result,

    2:optional string strValue
}
/* 返回结果:字符串列表*/
struct StringListResult{
    1:Result result,

    2:optional list<string> strValueList
}