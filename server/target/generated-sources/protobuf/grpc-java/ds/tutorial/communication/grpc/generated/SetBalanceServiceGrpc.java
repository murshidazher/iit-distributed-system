package ds.tutorial.communication.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: BankService.proto")
public final class SetBalanceServiceGrpc {

  private SetBalanceServiceGrpc() {}

  public static final String SERVICE_NAME = "ds.tutorial.communication.grpc.generated.SetBalanceService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.tutorial.communication.grpc.generated.SetBalanceRequest,
      ds.tutorial.communication.grpc.generated.SetBalanceResponse> getSetBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setBalance",
      requestType = ds.tutorial.communication.grpc.generated.SetBalanceRequest.class,
      responseType = ds.tutorial.communication.grpc.generated.SetBalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.tutorial.communication.grpc.generated.SetBalanceRequest,
      ds.tutorial.communication.grpc.generated.SetBalanceResponse> getSetBalanceMethod() {
    io.grpc.MethodDescriptor<ds.tutorial.communication.grpc.generated.SetBalanceRequest, ds.tutorial.communication.grpc.generated.SetBalanceResponse> getSetBalanceMethod;
    if ((getSetBalanceMethod = SetBalanceServiceGrpc.getSetBalanceMethod) == null) {
      synchronized (SetBalanceServiceGrpc.class) {
        if ((getSetBalanceMethod = SetBalanceServiceGrpc.getSetBalanceMethod) == null) {
          SetBalanceServiceGrpc.getSetBalanceMethod = getSetBalanceMethod =
              io.grpc.MethodDescriptor.<ds.tutorial.communication.grpc.generated.SetBalanceRequest, ds.tutorial.communication.grpc.generated.SetBalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.tutorial.communication.grpc.generated.SetBalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.tutorial.communication.grpc.generated.SetBalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SetBalanceServiceMethodDescriptorSupplier("setBalance"))
              .build();
        }
      }
    }
    return getSetBalanceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SetBalanceServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SetBalanceServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SetBalanceServiceStub>() {
        @java.lang.Override
        public SetBalanceServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SetBalanceServiceStub(channel, callOptions);
        }
      };
    return SetBalanceServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SetBalanceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SetBalanceServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SetBalanceServiceBlockingStub>() {
        @java.lang.Override
        public SetBalanceServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SetBalanceServiceBlockingStub(channel, callOptions);
        }
      };
    return SetBalanceServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SetBalanceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SetBalanceServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SetBalanceServiceFutureStub>() {
        @java.lang.Override
        public SetBalanceServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SetBalanceServiceFutureStub(channel, callOptions);
        }
      };
    return SetBalanceServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SetBalanceServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void setBalance(ds.tutorial.communication.grpc.generated.SetBalanceRequest request,
        io.grpc.stub.StreamObserver<ds.tutorial.communication.grpc.generated.SetBalanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetBalanceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.tutorial.communication.grpc.generated.SetBalanceRequest,
                ds.tutorial.communication.grpc.generated.SetBalanceResponse>(
                  this, METHODID_SET_BALANCE)))
          .build();
    }
  }

  /**
   */
  public static final class SetBalanceServiceStub extends io.grpc.stub.AbstractAsyncStub<SetBalanceServiceStub> {
    private SetBalanceServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SetBalanceServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SetBalanceServiceStub(channel, callOptions);
    }

    /**
     */
    public void setBalance(ds.tutorial.communication.grpc.generated.SetBalanceRequest request,
        io.grpc.stub.StreamObserver<ds.tutorial.communication.grpc.generated.SetBalanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetBalanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SetBalanceServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SetBalanceServiceBlockingStub> {
    private SetBalanceServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SetBalanceServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SetBalanceServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.tutorial.communication.grpc.generated.SetBalanceResponse setBalance(ds.tutorial.communication.grpc.generated.SetBalanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetBalanceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SetBalanceServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SetBalanceServiceFutureStub> {
    private SetBalanceServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SetBalanceServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SetBalanceServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.tutorial.communication.grpc.generated.SetBalanceResponse> setBalance(
        ds.tutorial.communication.grpc.generated.SetBalanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetBalanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_BALANCE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SetBalanceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SetBalanceServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_BALANCE:
          serviceImpl.setBalance((ds.tutorial.communication.grpc.generated.SetBalanceRequest) request,
              (io.grpc.stub.StreamObserver<ds.tutorial.communication.grpc.generated.SetBalanceResponse>) responseObserver);
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

  private static abstract class SetBalanceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SetBalanceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.tutorial.communication.grpc.generated.BankService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SetBalanceService");
    }
  }

  private static final class SetBalanceServiceFileDescriptorSupplier
      extends SetBalanceServiceBaseDescriptorSupplier {
    SetBalanceServiceFileDescriptorSupplier() {}
  }

  private static final class SetBalanceServiceMethodDescriptorSupplier
      extends SetBalanceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SetBalanceServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SetBalanceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SetBalanceServiceFileDescriptorSupplier())
              .addMethod(getSetBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
