package netty.lijia.com;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class Myweb implements Runnable {
    //Myweb
 
    private int port;
    @Override
    public void run() {
        System.out.println("--------进入web---------");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel e) throws Exception {
                    System.out.println("chhhh"+e.id());
//                    e.pipeline().addLast("http-codec", new HttpServerCodec());
//                    e.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
//                    e.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                    e.pipeline().addLast(new SimpleServerHandler());
                }
            });
//            b.childHandler(new ChannelFilter());
            System.out.println("平台监听开启....");
            Channel ch = b.bind(8888).sync().channel();
            ch.closeFuture().sync();
 
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //优雅的退出程序
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
