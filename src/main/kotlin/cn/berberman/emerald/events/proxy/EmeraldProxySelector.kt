package cn.berberman.emerald.events.proxy

import cn.berberman.emerald.events.ServerNetWorkEvent
import cn.berberman.emerald.util.EmeraldUtil
import java.io.IOException
import java.net.Proxy
import java.net.ProxySelector
import java.net.SocketAddress
import java.net.URI

object EmeraldProxySelector : ProxySelector() {

	private lateinit var defaultProxySelector: ProxySelector
	override fun select(uri: URI): MutableList<Proxy>? {
		return ServerNetWorkEvent(uri).also(EmeraldUtil.pluginManager::callEvent).takeIf { it.isCancelled }?.let {
			null
		} ?: defaultProxySelector.select(uri)
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