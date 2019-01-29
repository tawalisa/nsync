package io.netty.example.chat;

import java.util.Date;
import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ChatClient {
    public static void main(String[] args) throws Exception {
    	
    	
    	args = new String[]{"localhost","8080"};
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String username="client1";
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ObjectEncoder(),                            
                            new ObjectDecoder(ClassResolvers.cacheDisabled(getClass().getClassLoader())),
//                            new MsgEncoder(),
                            new ChatClientHandler());
                }
            });
            
            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)
            Channel channel = f.channel();
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            
            while(!"exit".equals(input)) {
            	System.out.println("===="+input);
//            	channel.writeAndFlush(input);
            	channel.writeAndFlush(new Msg(username,input,new Date(),null,null));
            	input = scanner.nextLine();
            }
            scanner.close();
            // close .
            f.channel().close();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}