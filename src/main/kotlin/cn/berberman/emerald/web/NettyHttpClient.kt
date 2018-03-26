package cn.berberman.emerald.web

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.http.*
import java.net.URI

fun emptyHttpRequest(uri: URI, method: HttpMethod, callback: (msg: Any) -> Unit) {
	val workerGroup = NioEventLoopGroup()
	val bootstrap = Bootstrap()
	bootstrap.group(workerGroup)
			.channel(NioSocketChannel::class.java)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.handler(object : ChannelInitializer<SocketChannel>() {
				override fun initChannel(ch: SocketChannel) {
					ch.pipeline().apply {
						addLast(HttpRequestEncoder())
						addLast(HttpResponseDecoder())
						addLast(object : ChannelInboundHandlerAdapter() {
							override fun channelRead(ctx: ChannelHandlerContext, msg: Any) =
									callback(msg)
						})
					}
				}
			})

	val channel = bootstrap.connect(uri.host, uri.port).sync().channel()
	val request = DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
			method, uri.toASCIIString())
	request.headers()[HttpHeaderNames.HOST] = "127.0.0.1"
	request.headers()[HttpHeaderNames.CONNECTION] = HttpHeaderValues.KEEP_ALIVE
	request.headers()[HttpHeaderNames.CONTENT_LENGTH] = request.content().readableBytes()

	channel.writeAndFlush(request)

	channel.closeFuture().sync()

	workerGroup.shutdownGracefully()
}
