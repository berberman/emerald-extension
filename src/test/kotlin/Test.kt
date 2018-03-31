import cn.berberman.emerald.util.onlinemode.ProfileData
import com.google.gson.Gson
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert
import org.junit.Test

class Test {
	@Test
	fun testGet() = runBlocking {
		HttpUtil.get("https://api.mojang.com/users/profiles/minecraft/berberman") {
			println(it.entity.content.bufferedReader().readText())
		}
		Unit
	}

	@Test
	fun testPlayerUUID() = runBlocking {
		val player = "berberman"
		val map = mutableMapOf<String, String>()
		HttpUtil.get("https://api.mojang.com/users/profiles/minecraft/$player") {
			it.entity?.let { Gson().fromJson(it.content.bufferedReader(), ProfileData::class.java) }
		}.let { map[player] = it?.uuid ?: "" }
		Assert.assertEquals(player,map.entries.first().key)
		Assert.assertEquals("b33ddc9f185e49ad8043c20593785a18",map.entries.first().value)
	}


}