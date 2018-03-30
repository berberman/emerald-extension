import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

class Test {
	@Test
	fun testGet() = runBlocking {
		HttpUtil.get("https://api.mojang.com/users/profiles/minecraft/berberman") {
			println(it.entity.content.bufferedReader().readText())
		}
		Unit
	}

}