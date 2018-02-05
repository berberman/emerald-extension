package cn.berberman.emerald.dsl.event

/**
 * A object holds events we want's to register.
 * @see DSLEventScope
 * @author berberman
 */
internal object EventHolder {
	private val events = mutableListOf<PackingEvent<*>>()
	/**
	 * Add event to register list.
	 * @param packingEvent a event to register
	 */
	fun add(packingEvent: PackingEvent<*>) = events.add(packingEvent)

	internal fun register() =
			events.forEach(PackingEvent<*>::register)

}