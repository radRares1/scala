package lectures.part2fap

object CurriesPFA extends App {

  //curried functions
  val superAdder: Int => Int => Int =
    x => y => x + y

  val add3 = superAdder(3)

  println(add3(5))
  println(superAdder(3)(5))

  def curriedAdder(x: Int)(y: Int): Int = x + y //curried method

  val add4: Int => Int = curriedAdder(4)

  //lifting = ETA-EXPANSION
  //function!=method
  def inc(x: Int) = x + 1

  List(1, 2, 3).map(x => inc(x)) //ETA-expansion

  //Partial functions applications

  val add5 = curriedAdder(5) _ // Int => Int

  //Exercise
  val simpleAddFunction = (x: Int, y: Int) => x + y

  def simpleAddMethod(x: Int, y: Int) = x + y

  def curriedAddMethod(x: Int)(y: Int) = x + y

  //add7: Int: Int = y => 7+y

  val add7 = (x: Int) => simpleAddFunction(7, x)
  val add7_2 = simpleAddFunction.curried(7)
  val add7_6 = simpleAddMethod(7, _: Int)

  val add7_3 = curriedAddMethod(7) _ //PAF
  val add7_4 = curriedAddMethod(7)(_) //PAF = alternative syntax

  val add7_5 = simpleAddMethod(7, _: Int) //alt syntax for turning methods into function vals
  //y => simpleAddMethod(7,y)

  //underscores stronk
  def concatenator(a: String, b: String, c: String): String = a + b + c

  val insertName = concatenator("Hello im", _: String, ", sup")
  // x:String => concatenator(hello, x sup)
  println(insertName(" rrj"))

  val fillTheBlanx = concatenator("hi", _: String, _: String) //(x,y) => concatenator("hi",x,y)
  println(fillTheBlanx(" eu", " nu ma las"))

  //Exercise
  /*
    1.
    process a list of no and return their string rep with
    dif formats
    use the %4.2f, %8.6f, %14.12f with curriend formatter function
    "4.2f".format(Math.Pi)
   */
  def curriedFormatter(s: String)(number: Double): String = s.format(number)

  val numbers = List(Math.PI, Math.E, 1, 9.8, 1.3e-12)

  val simpleFormat = curriedFormatter("%4.2f") _
  val seriousFormat = curriedFormatter("%8.6f") _
  val preciseFormat = curriedFormatter("%14.12f") _

  println(numbers.map(curriedFormatter("%14.12f"))) //compiler does eta for us <3


  /*
    2.
    dif between
    -functions vs methods
    -params: by-name vs 0-lambda
   */
  def byName(n: => Int) = n + 1

  def byFunction(f: () => Int) = f() + 1

  def method: Int = 42

  def parenMethod(): Int = 42

  /*
    calling byName and byFunction
    -int
    -method
    -parenMethod
    -lambda
    -PAF
   */
  byName(23)
  byName(method)
  byName(parenMethod)//ok but beware <=> byName(parenMethod())
  byName(parenMethod())
  //byName(() => 42) //not ok
  byName((() => 42)())
//  byName(parenMethod _)//not ok

  //byFunction(45)//not ok
  //byFunction(method)//not ok!!!!! does not do eta exp
  byFunction(() => 46)
  byFunction(parenMethod _)

}
