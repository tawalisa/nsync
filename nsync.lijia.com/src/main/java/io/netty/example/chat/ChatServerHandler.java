package io.netty.example.chat;

import java.util.Date;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends ChannelInboundHandlerAdapter {

	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	System.out.println("server channel active");
    	channels.add(ctx.channel());
//		super.channelActive(ctx);
	}
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		channels.remove(ctx.channel());
		System.out.println("server channel inactive");
//		super.channelInactive(ctx);
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("server read completed");
		super.channelReadComplete(ctx);
	}
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		
		Msg m = (Msg) msg;
		System.out.println("server receive msg:"+m);
		m.setServerDate(new Date());
		for(Channel channel:channels) {
			channel.write(m);
			channel.flush();
		}
//    	ctx.write(msg); // (1)
//        ctx.flush(); // (2)
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
    
}