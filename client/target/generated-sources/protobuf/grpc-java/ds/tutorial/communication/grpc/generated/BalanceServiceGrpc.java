package ds.tutorial.communication.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: BankService.proto")
public final class BalanceServiceGrpc {

  private BalanceServiceGrpc() {}

  public static final String SERVICE_NAME = "ds.tutorial.communication.grpc.generated.BalanceService";

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
    if ((getCheckBalanceMethod = BalanceServiceGrpc.getCheckBalanceMethod) == null) {
      synchronized (BalanceServiceGrpc.class) {
        if ((getCheckBalanceMethod = BalanceServiceGrpc.getCheckBalanceMethod) == null) {
          BalanceServiceGrpc.getCheckBalanceMethod = getCheckBalanceMethod =
              io.grpc.MethodDescriptor.<ds.tutorial.communication.grpc.generated.CheckBalanceRequest, ds.tutorial.communication.grpc.generated.CheckBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.tutorial.communication.grpc.generated.CheckBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.tutorial.communication.grpc.generated.CheckBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BalanceServiceMethodDescriptorSupplier("checkBalance"))
              .build();
        }
      }
    }
    return getCheckBalanceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BalanceServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BalanceServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BalanceServiceStub>() {
        @java.lang.Override
        public BalanceServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BalanceServiceStub(channel, callOptions);
        }
      };
    return BalanceServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BalanceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BalanceServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BalanceServiceBlockingStub>() {
        @java.lang.Override
        public BalanceServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BalanceServiceBlockingStub(channel, callOptions);
        }
      };
    return BalanceServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BalanceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BalanceServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BalanceServiceFutureStub>() {
        @java.lang.Override
        public BalanceServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BalanceServiceFutureStub(channel, callOptions);
        }
      };
    return BalanceServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class BalanceServiceImplBase implements io.grpc.BindableService {

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
  public static final class BalanceServiceStub extends io.grpc.stub.AbstractAsyncStub<BalanceServiceStub> {
    private BalanceServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BalanceServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BalanceServiceStub(channel, callOptions);
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
  public static final class BalanceServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<BalanceServiceBlockingStub> {
    private BalanceServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BalanceServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BalanceServiceBlockingStub(channel, callOptions);
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
  public static final class BalanceServiceFutureStub extends io.grpc.stub.AbstractFutureStub<BalanceServiceFutureStub> {
    private BalanceServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BalanceServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BalanceServiceFutureStub(channel, callOptions);
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
    private final BalanceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BalanceServiceImplBase serviceImpl, int methodId) {
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

  private static abstract class BalanceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BalanceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.tutorial.communication.grpc.generated.BankService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BalanceService");
    }
  }

  private static final class BalanceServiceFileDescriptorSupplier
      extends BalanceServiceBaseDescriptorSupplier {
    BalanceServiceFileDescriptorSupplier() {}
  }

  private static final class BalanceServiceMethodDescriptorSupplier
      extends BalanceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BalanceServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (BalanceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BalanceServiceFileDescriptorSupplier())
              .addMethod(getCheckBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
