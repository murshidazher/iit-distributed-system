package ds.tutorial.communication.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: BankService.proto")
public final class CheckBalanceServiceGrpc {

  private CheckBalanceServiceGrpc() {}

  public static final String SERVICE_NAME = "ds.tutorial.communication.grpc.generated.CheckBalanceService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.tutorial.communication.grpc.generated.CheckBalanceRequest,
      ds.tutorial.communication.grpc.generated.CheckBalanceResponse> getCheckBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkBalance",
      requestType = ds.tutorial.communication.grpc.generated.CheckBalanceRequest.class,
      responseType = ds.tutorial.communication.grpc.generated.CheckBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.tutorial.communication.grpc.generated.CheckBalanceRequest,
      ds.tutorial.communication.grpc.generated.CheckBalanceResponse> getCheckBalanceMethod() {
    io.grpc.MethodDescriptor<ds.tutorial.communication.grpc.generated.CheckBalanceRequest, ds.tutorial.communication.grpc.generated.CheckBalanceResponse> getCheckBalanceMethod;
    if ((getCheckBalanceMethod = CheckBalanceServiceGrpc.getCheckBalanceMethod) == null) {
      synchronized (CheckBalanceServiceGrpc.class) {
        if ((getCheckBalanceMethod = CheckBalanceServiceGrpc.getCheckBalanceMethod) == null) {
          CheckBalanceServiceGrpc.getCheckBalanceMethod = getCheckBalanceMethod =
              io.grpc.MethodDescriptor.<ds.tutorial.communication.grpc.generated.CheckBalanceRequest, ds.tutorial.communication.grpc.generated.CheckBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.tutorial.communication.grpc.generated.CheckBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.tutorial.communication.grpc.generated.CheckBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CheckBalanceServiceMethodDescriptorSupplier("checkBalance"))
              .build();
        }
      }
    }
    return getCheckBalanceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CheckBalanceServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckBalanceServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckBalanceServiceStub>() {
        @java.lang.Override
        public CheckBalanceServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckBalanceServiceStub(channel, callOptions);
        }
      };
    return CheckBalanceServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CheckBalanceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckBalanceServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckBalanceServiceBlockingStub>() {
        @java.lang.Override
        public CheckBalanceServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckBalanceServiceBlockingStub(channel, callOptions);
        }
      };
    return CheckBalanceServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CheckBalanceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CheckBalanceServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CheckBalanceServiceFutureStub>() {
        @java.lang.Override
        public CheckBalanceServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CheckBalanceServiceFutureStub(channel, callOptions);
        }
      };
    return CheckBalanceServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CheckBalanceServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void checkBalance(ds.tutorial.communication.grpc.generated.CheckBalanceRequest request,
        io.grpc.stub.StreamObserver<ds.tutorial.communication.grpc.generated.CheckBalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCheckBalanceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.tutorial.communication.grpc.generated.CheckBalanceRequest,
                ds.tutorial.communication.grpc.generated.CheckBalanceResponse>(
                  this, METHODID_CHECK_BALANCE)))
          .build();
    }
  }

  /**
   */
  public static final class CheckBalanceServiceStub extends io.grpc.stub.AbstractAsyncStub<CheckBalanceServiceStub> {
    private CheckBalanceServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckBalanceServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckBalanceServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkBalance(ds.tutorial.communication.grpc.generated.CheckBalanceRequest request,
        io.grpc.stub.StreamObserver<ds.tutorial.communication.grpc.generated.CheckBalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckBalanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CheckBalanceServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CheckBalanceServiceBlockingStub> {
    private CheckBalanceServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckBalanceServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckBalanceServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.tutorial.communication.grpc.generated.CheckBalanceResponse checkBalance(ds.tutorial.communication.grpc.generated.CheckBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckBalanceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CheckBalanceServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CheckBalanceServiceFutureStub> {
    private CheckBalanceServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CheckBalanceServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CheckBalanceServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.tutorial.communication.grpc.generated.CheckBalanceResponse> checkBalance(
        ds.tutorial.communication.grpc.generated.CheckBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckBalanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_BALANCE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CheckBalanceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CheckBalanceServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_BALANCE:
          serviceImpl.checkBalance((ds.tutorial.communication.grpc.generated.CheckBalanceRequest) request,
              (io.grpc.stub.StreamObserver<ds.tutorial.communication.grpc.generated.CheckBalanceResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CheckBalanceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CheckBalanceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.tutorial.communication.grpc.generated.BankService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CheckBalanceService");
    }
  }

  private static final class CheckBalanceServiceFileDescriptorSupplier
      extends CheckBalanceServiceBaseDescriptorSupplier {
    CheckBalanceServiceFileDescriptorSupplier() {}
  }

  private static final class CheckBalanceServiceMethodDescriptorSupplier
      extends CheckBalanceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CheckBalanceServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CheckBalanceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CheckBalanceServiceFileDescriptorSupplier())
              .addMethod(getCheckBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
