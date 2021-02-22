package lectures.part2fap

object LazyEval extends App {

  //lazy delays the eval of values
  lazy val x: Int = {
    println("hello")
    42
  }
  println(x)
  println(x)

  def sideEffectCond: Boolean = {
    println("Boo")
    true
  }

  def simpleCond: Boolean = false

  lazy val lazyCond = sideEffectCond
  println(if (simpleCond && lazyCond) "yes" else "no")

  //in conjuction with call by name
  def byNameMethod(n: => Int): Int = {
    lazy val t = n //only evaluated once
    t + t + t + 1
  }

  def retriveMagicValue = {
    Thread.sleep(1000)
    42
  }

  println(byNameMethod(retriveMagicValue))
  //use lazy vals

  //CALL BY NEED

  //filtering iwth rlazy vals

  def lessThan30(i: Int): Boolean = {
    println(s"$i is less than 30?")
    i < 30
  }

  def graterThan20(i: Int): Boolean = {
    println(s"$i is less than 20?")
    i > 20
  }

  val numbers = List(1, 25, 40, 5, 23)
  val lt30 = numbers.filter(lessThan30) //List(1,25,5,23)
  val gt20 = lt30.filter(graterThan20)

  val lt30lazy = numbers.withFilter(lessThan30)
  val gt20lazy = lt30lazy.withFilter(graterThan20)
  println("------")
  gt20lazy.foreach(println)

  for{
    a <- List(1,2,3) if a%2 == 0 // use lazy vals
  }yield a+ 1
  List(1,2,3).withFilter( _ % 2 == 0).map(_ + 1) //List[Int]

  /*
      Exercise: implement a lazly evaluated, singly linked stream of elements

      naturals = MyStream.from(1)(x => x + 1) = stream of natural numbers (potentially infinite!)
      naturals.take(100) //lazly evaluated stream of the forst 100 naturals(finite stream)
      naturals.take(100).foreach(println)
      nautrals.foreach(println) // will crash (infinite)
      naturals.map(_ * 2) // stream of all even numbers (infinite)

   */
  abstract class MyStream[+A] {
    def isEmpty: Boolean
    def head: A
    def tail: MyStream[A]

    def #::[B >: A](element:B):MyStream[B] //prepend operator
    def ++[B >: A](anotherStream:B):MyStream[B] // concatenate two streams

    def foreach(f: A => Unit):Unit
    def map[B](f: A => B): MyStream[B]
    def flatMap[B](f: A => MyStream[B]): MyStream[B]
    def filter(predicate: A => Boolean): MyStream[A]
    def take(n:Int):MyStream[A] // takes the first n elements out of this stream
    def takeAsList(n:Int):List[A]

  }

  object MyStream {
    def from[A](start:A)(generator:A =>A): MyStream[A] = ???
  }

}
