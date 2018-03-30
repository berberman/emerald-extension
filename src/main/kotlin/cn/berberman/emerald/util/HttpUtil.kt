import org.apache.http.Header
import org.apache.http.HttpEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import java.net.URL
import kotlin.coroutines.experimental.suspendCoroutine

object HttpUtil {

	@Deprecated("magic", ReplaceWith("get"))
	suspend fun originGet(url: URL): String =
			suspendCoroutine {
				try {
					url.openConnection().apply {
						setRequestProperty("Accept", "*/*")
						setRequestProperty("Connection", "Keep-Alive")
						setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
						connect()
					}.getInputStream().bufferedReader().readText().let(it::resume)
				} catch (e: Exception) {
					it.resumeWithException(e)
				}
			}

	suspend fun <R> get(uri: String, header: Header? = null, block: (result: CloseableHttpResponse) -> R): R =
			suspendCoroutine {
				val client = HttpClients.createDefault()
				try {
					HttpGet(uri).apply {
						addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
						header?.let(this::addHeader)
					}.let(client::execute).let(block::invoke).let(it::resume)
				} catch (e: Exception) {
					it.resumeWithException(e)
				} finally {
					client.close()
				}
			}

	suspend fun <R> post(uri: String, entity: HttpEntity? = null, header: Header? = null, block: (result: CloseableHttpResponse) -> R): R =
			suspendCoroutine {
				val client = HttpClients.createDefault()
				try {
					HttpPost(uri).apply {
						addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
						header?.let(this::addHeader)
						entity?.let { this.entity = it }
					}.let(client::execute).let(block::invoke).let(it::resume)
				} catch (e: Exception) {
					it.resumeWithException(e)
				} finally {
					client.close()
				}
			}

}