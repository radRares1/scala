package lectures.part1

import scala.util.Try

object DarkSugar extends App {

  //syntax sugar #1: methods with single param

  def singleArgMethod(arg: Int): String = s"$arg little duccs"

  val desc = singleArgMethod {
    // write some code
    21
  }

  val aTry = Try { //java try{..}
    //do stuff
    //aplly with the arg in the {}
  }

  List(1, 2, 3).map { x =>
    x + 1
  }

  //syntax sugar #2: single abstract method
  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val aFunkyInstance: Action = (x: Int) => x + 1

  //example: Runnables
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("yea")
  })

  val aSweetThr = new Thread(() => println("sweet"))

  abstract class AnAbstractType {
    def implemented: Int = 23

    def f(a: Int): Unit
  }

  val anAbstrInstance: AnAbstractType = (a: Int) => println("yo")

  //syntax sugar #3: the :: and #:: methods are special$$

  val prependedList = 2 :: List(3, 4)
  // 2.::(List(3,4)
  // List(3,4).::(2)
  //?!?!?!?!?

  //scala specs: last character decides the asosiativity of a method
  1 :: 2 :: 3 :: List(4, 5)
  List(4, 5).::(3).::(2).::(1)

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this //this should be some implementation
  }

  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  //syntax sugar #4: multi-word method naming

  class TeenGirl(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }

  val lilly = new TeenGirl("lilly")

  lilly `and then said` "yo u cool"

  //syntax sugar #5: infix types
  class Composite[A, B]

  val composite: Int Composite String = ???

  class -->[A, B]

  val towards: Int --> String = ???

  //syntax sugar #6: update() very spshul

  val anArray = Array(1, 2, 3)
  anArray(2) = 7 // rewritten to anArray.update(2,7)
  //used in mutable cols

  //syntax sugar #7: setters for mutable containers

  class Mutable {
    private var internalMember: Int = 0 //private for oo enc
    def member = internalMember // getter
    def member_=(value: Int): Unit =
      internalMember = value // "setter"
  }

  val aMutContainer = new Mutable
  aMutContainer.member = 42 // rewritten as aMutableContainer.member_=(42)


}
