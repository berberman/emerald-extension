package cn.berberman.emerald.util

import cn.berberman.emerald.events.server.ServerNetworkEvent
import java.io.IOException
import java.net.Proxy
import java.net.ProxySelector
import java.net.SocketAddress
import java.net.URI

object EmeraldProxySelector : ProxySelector() {

	private lateinit var defaultProxySelector: ProxySelector

	override fun select(uri: URI): MutableList<Proxy>? {
		return ServerNetworkEvent(uri).also(EmeraldUtil.pluginManager::callEvent).let {
			if (it.isCancelled) null else defaultProxySelector.select(it.uri)
		}
	}

	override fun connectFailed(uri: URI?, sa: SocketAddress?, ioe: IOException?) {
		defaultProxySelector.connectFailed(uri, sa, ioe)
	}

	internal fun init(proxySelector: ProxySelector) {
		if (getDefault() == this) return
		defaultProxySelector = proxySelector
		setDefault(this)
	}
}