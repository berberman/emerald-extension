import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

class Test {
	@Test
	fun test() = runBlocking {
		HttpUtil.get("https://api.mojang.com/users/profiles/minecraft/berberman") {}
		Unit
	}
}