package lectures.part2fap

object PartialFunctions extends App {

  val aFunc = (x: Int) => x + 1

  val aFusFunct = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 21
    else if (x == 5) 0
    else throw new FunctionNotOkException

  class FunctionNotOkException extends RuntimeException

  val aNicerFusFunct = (x: Int) => x match {
    case 1 => 42
    case 2 => 21
    case 5 => 0
  } //{1,2,5} => Int

  val partialFunct: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 21
    case 5 => 0
  } //partial function value

  println(partialFunct(2))
  //println(partialFunct(3))
  println(partialFunct.isDefinedAt(23))
  //lift
  val lifted = partialFunct.lift // int => option[int]
  println(lifted(2))
  println(lifted(56))

  val pfChain = partialFunct.orElse[Int, Int] {
    case 45 => 67
  }
  println(pfChain(2))
  println(pfChain(45))

  //pf extendes to normal

  val func: Int => Int = {
    case 1 => 49
  }

  //hof accept partial functions as well
  val aMappedList = List(1, 2, 3).map {
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }
  println(aMappedList)

  /*
  note: a partial function can only have one parameter type
  1 - construct a pf instance
  2 - bot
   */

  val myPartial = new PartialFunction[Int, Int] {
    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 2

    override def apply(v1: Int): Int = v1 match {
      case 1 => 42
      case 2 => 78
    }

  }


  val chatBot: PartialFunction[String, String] = {
    case "hello" => "im a bot"
    case "bye" => "cya nerd"
    case "call mom" => "<3"
  }

  scala.io.Source.stdin.getLines().map(chatBot).foreach(println)


}
