namespace java com.robblibrary.apollo.thrift.base

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
/* 返回结果:基础类型*/
struct BaseResult{
    1:Result result,
    /* A boolean value*/
    2:optional bool     boolValue,
    /* A signed byte*/
    3:optional byte     byteValue,
    /* A 16-bit signed integer*/
    4:optional i16      int16Value,
    /* A 32-bit signed integer*/
    5:optional i32      int32Value,
    /* A 64-bit signed integer*/
    6:optional i64      int64Value,
    /* A 64-bit floating point number*/
    7:optional double   doubleValue,
    /* An encoding-agnostic text or binary string*/
    8:optional string   strValue
}
/* 返回结果:Boolean*/
struct BooleanResult{
    1:Result result,

    2:optional bool boolValue
}
/* 返回结果:整数*/
struct IntegerResult{
    1:Result result,

    2:optional i32 intValue
}
/* 返回结果:浮点数*/
struct FloatResult{
    1:Result result,

    2:optional double doubleValue,
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