import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import sun.net.www.http.HttpClient
import java.net.URL

class Test {
	@Test
	fun test() = runBlocking {
		HttpUtil.originGet(URL("https://api.mojang.com/users/profiles/minecraft/berberman"))
		Unit
	}
}