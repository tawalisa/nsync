package io.netty.example.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class MsgEncoder extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object obj, ChannelPromise promise) throws Exception {
		System.out.println("msg encode" + obj+":"+obj.getClass());
//		ctx.channel().write(obj);
		
		super.write(ctx, obj, promise);
//		Msg msg = (Msg) obj;
//		ctx.writeAndFlush(msg);
	}

}
