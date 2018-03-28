import java.net.URL
import kotlin.coroutines.experimental.suspendCoroutine

object HttpUtil {

	suspend fun originGet(url: URL): String =
			suspendCoroutine {
				try {
					url.openConnection().apply {
						setRequestProperty("accept", "*/*")
						setRequestProperty("connection", "Keep-Alive")
						setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
						connect()
					}.getInputStream().bufferedReader().readText().let(it::resume)
				} catch (e: Exception) {
					it.resumeWithException(e)
				}
			}


}