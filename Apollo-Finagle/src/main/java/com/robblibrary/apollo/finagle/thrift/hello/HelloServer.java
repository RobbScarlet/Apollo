/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.robblibrary.apollo.finagle.thrift.hello;

import com.twitter.finagle.thrift.DeserializeCtx;
import com.twitter.finagle.thrift.ThriftClientRequest;
import com.twitter.util.*;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.thrift.*;
import org.apache.thrift.async.*;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class HelloServer {
  public interface Iface {
    public com.robblibrary.apollo.finagle.thrift.result.StringResult sayHello(String name) throws TException;
  }

  public interface AsyncIface {
    public void sayHello(String name, AsyncMethodCallback<AsyncClient.sayHello_call> resultHandler) throws TException;
  }

  public interface ServiceIface {
    public Future<com.robblibrary.apollo.finagle.thrift.result.StringResult> sayHello(String name) throws Exception;
  }

  public static class Client implements TServiceClient, Iface {
    public static class Factory implements TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(TProtocol iprot, TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(TProtocol prot)
    {
      this(prot, prot);
    }

    public Client(TProtocol iprot, TProtocol oprot)
    {
      iprot_ = iprot;
      oprot_ = oprot;
    }

    protected TProtocol iprot_;
    protected TProtocol oprot_;

    protected int seqid_;

    public TProtocol getInputProtocol()
    {
      return this.iprot_;
    }

    public TProtocol getOutputProtocol()
    {
      return this.oprot_;
    }

    public com.robblibrary.apollo.finagle.thrift.result.StringResult sayHello(String name) throws TException
    {
      send_sayHello(name);
      return recv_sayHello();
    }

    public void send_sayHello(String name) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("sayHello", TMessageType.CALL, ++seqid_));
      sayHello_args __args__ = new sayHello_args();
      __args__.setName(name);
      __args__.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public com.robblibrary.apollo.finagle.thrift.result.StringResult recv_sayHello() throws TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      if (msg.seqid != seqid_) {
        throw new TApplicationException(TApplicationException.BAD_SEQUENCE_ID, "sayHello failed: out of sequence response");
      }
      sayHello_result result = new sayHello_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "sayHello failed: unknown result");
    }
  }

  public static class AsyncClient extends TAsyncClient implements AsyncIface {
    public static class Factory implements TAsyncClientFactory<AsyncClient> {
      private final TAsyncClientManager clientManager;
      private final TProtocolFactory protocolFactory;
      public Factory(TAsyncClientManager clientManager, TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(TProtocolFactory protocolFactory, TAsyncClientManager clientManager, TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void sayHello(String name, AsyncMethodCallback<sayHello_call> __resultHandler__) throws TException {
      checkReady();
      sayHello_call __method_call__ = new sayHello_call(name, __resultHandler__, this, super.protocolFactory, super.transport);
      manager.call(__method_call__);
    }

    public static class sayHello_call extends TAsyncMethodCall {
      private String name;

      public sayHello_call(String name, AsyncMethodCallback<sayHello_call> __resultHandler__, TAsyncClient __client__, TProtocolFactory __protocolFactory__, TNonblockingTransport __transport__) throws TException {
        super(__client__, __protocolFactory__, __transport__, __resultHandler__, false);
        this.name = name;
      }

      public void write_args(TProtocol __prot__) throws TException {
        __prot__.writeMessageBegin(new TMessage("sayHello", TMessageType.CALL, 0));
        sayHello_args __args__ = new sayHello_args();
        __args__.setName(name);
        __args__.write(__prot__);
        __prot__.writeMessageEnd();
      }

      public com.robblibrary.apollo.finagle.thrift.result.StringResult getResult() throws TException {
        if (getState() != State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        TMemoryInputTransport __memoryTransport__ = new TMemoryInputTransport(getFrameBuffer().array());
        TProtocol __prot__ = super.client.getProtocolFactory().getProtocol(__memoryTransport__);
        return (new Client(__prot__)).recv_sayHello();
      }
     }
   }


  public static class ServiceToClient implements ServiceIface {
    private final com.twitter.finagle.Service<ThriftClientRequest, byte[]> service;
    private final TProtocolFactory protocolFactory;
    private final scala.PartialFunction<com.twitter.finagle.service.ReqRep,com.twitter.finagle.service.ResponseClass> responseClassifier;

    public ServiceToClient(com.twitter.finagle.Service<ThriftClientRequest, byte[]> service, TProtocolFactory protocolFactory, scala.PartialFunction<com.twitter.finagle.service.ReqRep,com.twitter.finagle.service.ResponseClass> responseClassifier) {
      
      this.service = service;
      this.protocolFactory = protocolFactory;
      this.responseClassifier = responseClassifier;
    }

    public ServiceToClient(com.twitter.finagle.Service<ThriftClientRequest, byte[]> service, TProtocolFactory protocolFactory) {
      
      this.service = service;
      this.protocolFactory = protocolFactory;
      this.responseClassifier = com.twitter.finagle.service.ResponseClassifier.Default();
    }

    public Future<com.robblibrary.apollo.finagle.thrift.result.StringResult> sayHello(String name) {
      try {
        // TODO: size
        TMemoryBuffer __memoryTransport__ = new TMemoryBuffer(512);
        TProtocol __prot__ = this.protocolFactory.getProtocol(__memoryTransport__);
        __prot__.writeMessageBegin(new TMessage("sayHello", TMessageType.CALL, 0));
        sayHello_args __args__ = new sayHello_args();
        __args__.setName(name);
        __args__.write(__prot__);
        __prot__.writeMessageEnd();


        byte[] __buffer__ = Arrays.copyOfRange(__memoryTransport__.getArray(), 0, __memoryTransport__.length());
        final ThriftClientRequest __request__ = new ThriftClientRequest(__buffer__, false);

        Function<byte[], Try<com.robblibrary.apollo.finagle.thrift.result.StringResult>> replyDeserializer =
          new Function<byte[], Try<com.robblibrary.apollo.finagle.thrift.result.StringResult>>() {
            public Try<com.robblibrary.apollo.finagle.thrift.result.StringResult> apply(byte[] __buffer__) {
              TMemoryInputTransport __memoryTransport__ = new TMemoryInputTransport(__buffer__);
              TProtocol __prot__ = ServiceToClient.this.protocolFactory.getProtocol(__memoryTransport__);
              try {
                return new Return(((new Client(__prot__)).recv_sayHello()));
              } catch (Exception e) {
                return new Throw(e);
              }
            }
          };
        DeserializeCtx serdeCtx = new DeserializeCtx<com.robblibrary.apollo.finagle.thrift.result.StringResult>(__args__, replyDeserializer);

        return com.twitter.finagle.context.Contexts.local().let(
          DeserializeCtx.Key(),
          serdeCtx,
          new com.twitter.util.Function0<Future<com.robblibrary.apollo.finagle.thrift.result.StringResult>>() {
            public Future<com.robblibrary.apollo.finagle.thrift.result.StringResult> apply() {

              Future<byte[]> __done__ = service.apply(__request__);
              return __done__.flatMap(new Function<byte[], Future<com.robblibrary.apollo.finagle.thrift.result.StringResult>>() {
                public Future<com.robblibrary.apollo.finagle.thrift.result.StringResult> apply(byte[] __buffer__) {
                  TMemoryInputTransport __memoryTransport__ = new TMemoryInputTransport(__buffer__);
                  TProtocol __prot__ = ServiceToClient.this.protocolFactory.getProtocol(__memoryTransport__);
                  try {
                    return Future.value((new Client(__prot__)).recv_sayHello());
                  } catch (Exception e) {
                    return Future.exception(e);
                  }
                }
              });
            }
          });
      } catch (TException e) {
        return Future.exception(e);
      }
    }
  }

  public static class Processor implements TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(Iface iface)
    {
      iface_ = iface;
      processMap_.put("sayHello", new sayHello());
    }

    protected static interface ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException;
    }

    private Iface iface_;
    protected final HashMap<String,ProcessFunction> processMap_ = new HashMap<String,ProcessFunction>();

    public boolean process(TProtocol iprot, TProtocol oprot) throws TException
    {
      TMessage msg = iprot.readMessageBegin();
      ProcessFunction fn = processMap_.get(msg.name);
      if (fn == null) {
        TProtocolUtil.skip(iprot, TType.STRUCT);
        iprot.readMessageEnd();
        TApplicationException x = new TApplicationException(TApplicationException.UNKNOWN_METHOD, "Invalid method name: '"+msg.name+"'");
        oprot.writeMessageBegin(new TMessage(msg.name, TMessageType.EXCEPTION, msg.seqid));
        x.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
        return true;
      }
      fn.process(msg.seqid, iprot, oprot);
      return true;
    }

    private class sayHello implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        sayHello_args args = new sayHello_args();
        try {
          args.read(iprot);
        } catch (TProtocolException e) {
          iprot.readMessageEnd();
          TApplicationException x = new TApplicationException(TApplicationException.PROTOCOL_ERROR, e.getMessage());
          oprot.writeMessageBegin(new TMessage("sayHello", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        iprot.readMessageEnd();
        sayHello_result result = new sayHello_result();
        result.success = iface_.sayHello(args.name);

        oprot.writeMessageBegin(new TMessage("sayHello", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }
    }
  }

  public static class Service extends com.twitter.finagle.Service<byte[], byte[]> {
    private final ServiceIface iface;
    private final TProtocolFactory protocolFactory;
    protected HashMap<String, Function2<TProtocol, Integer, Future<byte[]>>> functionMap = new HashMap<String, Function2<TProtocol, Integer, Future<byte[]>>>();
    public Service(final ServiceIface iface, final TProtocolFactory protocolFactory) {
      this.iface = iface;
      this.protocolFactory = protocolFactory;
      functionMap.put("sayHello", new Function2<TProtocol, Integer, Future<byte[]>>() {
        public Future<byte[]> apply(final TProtocol iprot, final Integer seqid) {
          sayHello_args args = new sayHello_args();
          try {
            args.read(iprot);
          } catch (TProtocolException e) {
            try {
              iprot.readMessageEnd();
              TApplicationException x = new TApplicationException(TApplicationException.PROTOCOL_ERROR, e.getMessage());
              TMemoryBuffer memoryBuffer = new TMemoryBuffer(512);
              TProtocol oprot = protocolFactory.getProtocol(memoryBuffer);

              oprot.writeMessageBegin(new TMessage("sayHello", TMessageType.EXCEPTION, seqid));
              x.write(oprot);
              oprot.writeMessageEnd();
              oprot.getTransport().flush();
              byte[] buffer = Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length());
              return Future.value(buffer);
            } catch (Exception e1) {
              return Future.exception(e1);
            }
          } catch (Exception e) {
            return Future.exception(e);
          }

          try {
            iprot.readMessageEnd();
          } catch (Exception e) {
            return Future.exception(e);
          }
          Future<com.robblibrary.apollo.finagle.thrift.result.StringResult> future;
          try {
            future = iface.sayHello(args.name);
          } catch (Exception e) {
            future = Future.exception(e);
          }

          try {
            return future.flatMap(new Function<com.robblibrary.apollo.finagle.thrift.result.StringResult, Future<byte[]>>() {
              public Future<byte[]> apply(com.robblibrary.apollo.finagle.thrift.result.StringResult value) {
                sayHello_result result = new sayHello_result();
                result.success = value;
                result.setSuccessIsSet(true);

                try {
                  TMemoryBuffer memoryBuffer = new TMemoryBuffer(512);
                  TProtocol oprot = protocolFactory.getProtocol(memoryBuffer);

                  oprot.writeMessageBegin(new TMessage("sayHello", TMessageType.REPLY, seqid));
                  result.write(oprot);
                  oprot.writeMessageEnd();

                  return Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()));
                } catch (Exception e) {
                  return Future.exception(e);
                }
              }
            }).rescue(new Function<Throwable, Future<byte[]>>() {
              public Future<byte[]> apply(Throwable t) {
                return Future.exception(t);
              }
            });
          } catch (Exception e) {
            return Future.exception(e);
          }
        }
      });
    }

    public Future<byte[]> apply(byte[] request) {
      TTransport inputTransport = new TMemoryInputTransport(request);
      TProtocol iprot = protocolFactory.getProtocol(inputTransport);

      TMessage msg;
      try {
        msg = iprot.readMessageBegin();
      } catch (Exception e) {
        return Future.exception(e);
      }

      Function2<TProtocol, Integer, Future<byte[]>> fn = functionMap.get(msg.name);
      if (fn == null) {
        try {
          TProtocolUtil.skip(iprot, TType.STRUCT);
          iprot.readMessageEnd();
          TApplicationException x = new TApplicationException(TApplicationException.UNKNOWN_METHOD, "Invalid method name: '"+msg.name+"'");
          TMemoryBuffer memoryBuffer = new TMemoryBuffer(512);
          TProtocol oprot = protocolFactory.getProtocol(memoryBuffer);
          oprot.writeMessageBegin(new TMessage(msg.name, TMessageType.EXCEPTION, msg.seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()));
        } catch (Exception e) {
          return Future.exception(e);
        }
      }

      return fn.apply(iprot, msg.seqid);
    }
  }

  public static class sayHello_args implements TBase<sayHello_args, sayHello_args._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("sayHello_args");

  private static final TField NAME_FIELD_DESC = new TField("name", TType.STRING, (short)1);


  public String name;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    NAME((short)1, "name");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NAME
  	return NAME;
        default:
  	return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }


  // isset id assignments

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME, new FieldMetaData("name", TFieldRequirementType.DEFAULT,
      new FieldValueMetaData(TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(sayHello_args.class, metaDataMap);
  }


  public sayHello_args() {
  }

  public sayHello_args(
    String name)
  {
    this();
    this.name = name;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public sayHello_args(sayHello_args other) {
    if (other.isSetName()) {
      this.name = other.name;
    }
  }

  public sayHello_args deepCopy() {
    return new sayHello_args(this);
  }

  @Override
  public void clear() {
    this.name = null;
  }

  public String getName() {
    return this.name;
  }

  public sayHello_args setName(String name) {
    this.name = name;

    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been asigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;
    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME:
      return getName();
    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NAME:
      return isSetName();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof sayHello_args)
      return this.equals((sayHello_args)that);
    return false;
  }

  public boolean equals(sayHello_args that) {
    if (that == null)
      return false;
    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    boolean present_name = true && (isSetName());
    builder.append(present_name);
    if (present_name)
      builder.append(name);
    return builder.toHashCode();
  }

  public int compareTo(sayHello_args other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    sayHello_args typedOther = (sayHello_args)other;

    lastComparison = Boolean.valueOf(isSetName()).compareTo(typedOther.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = TBaseHelper.compareTo(this.name, typedOther.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) {
        break;
      }
      switch (field.id) {
        case 1: // NAME
          if (field.type == TType.STRING) {
            this.name = iprot.readString();
          } else {
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.name != null) {
      oprot.writeFieldBegin(NAME_FIELD_DESC);
      oprot.writeString(this.name);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("sayHello_args(");
    boolean first = true;
    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }
}


  public static class sayHello_result implements TBase<sayHello_result, sayHello_result._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("sayHello_result");

  private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.STRUCT, (short)0);


  public com.robblibrary.apollo.finagle.thrift.result.StringResult success;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    SUCCESS((short)0, "success");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 0: // SUCCESS
  	return SUCCESS;
        default:
  	return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }


  // isset id assignments

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT,
      new StructMetaData(TType.STRUCT, com.robblibrary.apollo.finagle.thrift.result.StringResult.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(sayHello_result.class, metaDataMap);
  }


  public sayHello_result() {
  }

  public sayHello_result(
    com.robblibrary.apollo.finagle.thrift.result.StringResult success)
  {
    this();
    this.success = success;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public sayHello_result(sayHello_result other) {
    if (other.isSetSuccess()) {
      this.success = new com.robblibrary.apollo.finagle.thrift.result.StringResult(other.success);
    }
  }

  public sayHello_result deepCopy() {
    return new sayHello_result(this);
  }

  @Override
  public void clear() {
    this.success = null;
  }

  public com.robblibrary.apollo.finagle.thrift.result.StringResult getSuccess() {
    return this.success;
  }

  public sayHello_result setSuccess(com.robblibrary.apollo.finagle.thrift.result.StringResult success) {
    this.success = success;

    return this;
  }

  public void unsetSuccess() {
    this.success = null;
  }

  /** Returns true if field success is set (has been asigned a value) and false otherwise */
  public boolean isSetSuccess() {
    return this.success != null;
  }

  public void setSuccessIsSet(boolean value) {
    if (!value) {
      this.success = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SUCCESS:
      if (value == null) {
        unsetSuccess();
      } else {
        setSuccess((com.robblibrary.apollo.finagle.thrift.result.StringResult)value);
      }
      break;
    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SUCCESS:
      return getSuccess();
    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SUCCESS:
      return isSetSuccess();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof sayHello_result)
      return this.equals((sayHello_result)that);
    return false;
  }

  public boolean equals(sayHello_result that) {
    if (that == null)
      return false;
    boolean this_present_success = true && this.isSetSuccess();
    boolean that_present_success = true && that.isSetSuccess();
    if (this_present_success || that_present_success) {
      if (!(this_present_success && that_present_success))
        return false;
      if (!this.success.equals(that.success))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();
    boolean present_success = true && (isSetSuccess());
    builder.append(present_success);
    if (present_success)
      builder.append(success);
    return builder.toHashCode();
  }

  public int compareTo(sayHello_result other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    sayHello_result typedOther = (sayHello_result)other;

    lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(typedOther.isSetSuccess());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSuccess()) {
      lastComparison = TBaseHelper.compareTo(this.success, typedOther.success);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) {
        break;
      }
      switch (field.id) {
        case 0: // SUCCESS
          if (field.type == TType.STRUCT) {
            this.success = new com.robblibrary.apollo.finagle.thrift.result.StringResult();
            this.success.read(iprot);
          } else {
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    oprot.writeStructBegin(STRUCT_DESC);
    if (this.isSetSuccess()) {
      oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
      this.success.write(oprot);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("sayHello_result(");
    boolean first = true;
    sb.append("success:");
    if (this.success == null) {
      sb.append("null");
    } else {
      sb.append(this.success);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }
}



}
