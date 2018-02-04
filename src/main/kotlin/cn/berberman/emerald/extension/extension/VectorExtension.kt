package cn.berberman.emerald.extension.extension

import org.bukkit.util.Vector

operator fun Vector.plus(vector: Vector): Vector = add(vector)
operator fun Vector.minus(vector: Vector): Vector = subtract(vector)
operator fun Vector.times(vector: Vector): Vector = multiply(vector)
operator fun Vector.times(double: Double): Vector = multiply(double)
operator fun Vector.div(vector: Vector): Vector = divide(vector)
operator fun Vector.get(axis3D: Axis3D) = when (axis3D) {
	Axis3D.X -> x
	Axis3D.Y -> y
	Axis3D.Z -> z
}

operator fun Vector.set(axis3D: Axis3D, double: Double) = when (axis3D) {
	Axis3D.X -> x = double
	Axis3D.Y -> y = double
	Axis3D.Z -> z = double
}