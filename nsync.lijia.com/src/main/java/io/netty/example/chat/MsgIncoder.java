package io.netty.example.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class MsgIncoder extends SimpleChannelInboundHandler<Msg> {

//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		System.out.println("changel read:::"+msg);
//		super.channelRead(ctx, msg);
//	}



	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Msg msg) throws Exception {
		System.out.println("$$" + msg);
	}

	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
    }

}