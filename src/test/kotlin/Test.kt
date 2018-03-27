import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import java.net.URL

class Test {
	@Test
	fun test() = runBlocking {
		HttpUtil.sendGet(URL("https://api.mojang.com/users/profiles/minecraft/berberman"))
		Unit
	}
}