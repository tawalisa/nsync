package netty.lijia.com;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Bluetooth implements Runnable {
	// 蓝牙

	private int port;

	@Override
	public void run() {
		System.out.println("--------进入蓝牙---------");
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					System.out.println("chhhh" + ch.id());
					// 注册handler
					/*
					 * ch.pipeline().addLast("http-codec", new HttpServerCodec());
					 * ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
					 * ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
					 */
					ch.pipeline().addLast(new SimpleServerHandler());
				}
			});
//            b.childHandler(new ChannelFilter());
			System.out.println("平台监听开启....");
			Channel ch = b.bind(5500).sync().channel();
			ch.closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 优雅的退出程序
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
}
