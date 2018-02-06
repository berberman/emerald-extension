package cn.berberman.emerald.extension

import org.bukkit.util.Vector

/**
 * Adds a vector to this one
 *
 * @param vector The other vector
 * @return the same vector
 */
operator fun Vector.plus(vector: Vector): Vector = add(vector)

/**
 * Subtracts a vector from this one.
 *
 * @param vector The other vector
 * @return the same vector
 */
operator fun Vector.minus(vector: Vector): Vector = subtract(vector)

/**
 * Multiplies the vector by another.
 *
 * @param vector The other vector
 * @return the same vector
 */
operator fun Vector.times(vector: Vector): Vector = multiply(vector)

/**
 * Performs scalar multiplication, multiplying all components with a
 * scalar.
 *
 * @param double The factor
 * @return the same vector
 */
operator fun Vector.times(double: Double): Vector = multiply(double)

/**
 * Divides the vector by another.
 *
 * @param vector The other vector
 * @return the same vector
 */
operator fun Vector.div(vector: Vector): Vector = divide(vector)

/**
 * Get a one-dimension value from this vector.
 * @param axis3D which axis
 * @return the value
 */
operator fun Vector.get(axis3D: Axis3D) = when (axis3D) {
	Axis3D.X -> x
	Axis3D.Y -> y
	Axis3D.Z -> z
}

/**
 * Set a one-dimension value to this vector.
 * @param axis3D which axis
 * @param double value to set
 */
operator fun Vector.set(axis3D: Axis3D, double: Double) = when (axis3D) {
	Axis3D.X -> x = double
	Axis3D.Y -> y = double
	Axis3D.Z -> z = double
}