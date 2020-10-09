package lectures.l1

object ValuesVarsTypes extends App{

  val x = 42
  println(x)

  //vals are immutable duh
  //x = 2

  //COMPILES CAN INFER TYPES

  val aString: String = "henlow"

  val aBool: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 3421
  val aLong: Long = 321546466312312312L
  val aFlot: Float = 2.0f
  val aDouble: Double = 2.73

  //variables

  var aVar: Int = 4

  //are mutable

  aVar = 5 //used for side effects



}
