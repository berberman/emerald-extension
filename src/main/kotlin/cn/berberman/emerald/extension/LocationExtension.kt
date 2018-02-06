package cn.berberman.emerald.extension

import org.bukkit.Location
import org.bukkit.util.Vector

/**
 * Adds the location by another.
 *
 * @param location The other location
 * @return the same location
 * @throws IllegalArgumentException for differing worlds
 */
operator fun Location.plus(location: Location): Location = add(location)

/**
 * Subtracts the location by another.
 *
 * @param location The other location
 * @return the same location
 * @throws IllegalArgumentException for differing worlds
 */
operator fun Location.minus(location: Location): Location = subtract(location)

/**
 * Performs scalar multiplication, multiplying all components with a
 * scalar. Not world-aware.
 *
 * @param double The factor
 * @return the same location
 */
operator fun Location.times(double: Double): Location = multiply(double)

/**
 * Get a one-dimension value from this location.
 * @param axis3D which axis
 * @return the value
 */
operator fun Location.get(axis3D: Axis3D) = when (axis3D) {
	Axis3D.X -> x
	Axis3D.Y -> y
	Axis3D.Z -> z
}

/**
 * Set a one-dimension value to this location.
 * @param axis3D which axis
 * @param double value to set
 */
operator fun Location.set(axis3D: Axis3D, double: Double) = when (axis3D) {
	Axis3D.X -> x = double
	Axis3D.Y -> y = double
	Axis3D.Z -> z = double
}

