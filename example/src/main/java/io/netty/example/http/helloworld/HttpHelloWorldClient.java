package io.netty.example.http.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @author : mark.wang
 */
public class HttpHelloWorldClient {
    static final int PORT = 8080;

    public static void main(String[] args) {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                //设置Channel参数
                .option(ChannelOption.TCP_NODELAY, true)
                .attr(AttributeKey.valueOf("ChannelName"), "ClientChannel")
                .handler(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("handlerAdded");
                    }

                    @Override
                    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("channelRegistered");
                    }

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("channelActive");
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", PORT);
        Channel channel = channelFuture.syncUninterruptibly().channel();
        channel.closeFuture().awaitUninterruptibly();

    }
}
