package lectures.l1

object StringOps extends App{

  val str:String = "Hello, I am learning Scala"
  println(str.charAt(2))

  println(str.substring(7,11))

  println(str.split(" ").toList)

  println(str.startsWith("Hello"))
  println(str.replace(" ","/"))
  println(str.toUpperCase)
  println(str.length)

  val aNoStr = "45"
  val no = aNoStr.toInt
  println('a' +: aNoStr :+ 'z')
  println(aNoStr.reverse)
  println(str.take(2))

  //scala-specific:String interpolatiors

  //s-interpolators

  val name = "David"
  val age = 12
  val greeting = s"Hello, I'm $name and I am $age y/o "

  val greeting2 = s"Hello, I'm $name and I am ${age+1-2} y/o "
  println(greeting2)

  //f-interpolators
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.3f mici/minute"
  println(myth)

  //raw-interpolator

  println(raw"This is a \n newline")
  val esc = "This is a \n newline"
  println(raw"$esc")

}
