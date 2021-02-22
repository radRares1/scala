package lectures.part1

import scala.annotation.tailrec

object Recap extends App {

  val cond: Boolean = false
  val condVal = if (cond) 42 else 65;
  // instructions vs expr

  //complier infers types for us
  val codeBlock = {
    if (cond) 54
    56
  }

  //unit -- they do side effects, equivalent to void
  val unit = println("scalanhenlo")

  //functions
  def afunct(x: Int): Int = x + 1

  //recursion stack and tail
  @tailrec def factorial(n: Int, acc: Int): Int =
    if (n <= 0) acc
    else factorial(n - 1, n * acc)

  //oop
  class Animal

  class Dog extends Animal

  val aDog: Animal = new Dog //subtyping polymorphism
  trait Carnivor {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivor {
    override def eat(a: Animal): Unit = println("crunch")
  }

  //method notations
  val croc = new Crocodile
  croc.eat(aDog)
  croc eat aDog

  //anonymous classes

  val aCarnivore = new Carnivor {
    override def eat(a: Animal): Unit = println("rwr")
  }

  //generics
  abstract class MyList[+A] //variance and variance problem in this cours
  //singletons and companions
  object MyList

  //case classes
  case class Person(name: String, age: Int)

  //exceptions and try/catch/finally

  val throwsException = throw new RuntimeException // Nothing
  val maybeFails = try {
    throw new RuntimeException
  } catch {
    case e: Exception => "Got'em"
  } finally {
    println("logz")
  }

  //packaging and imports

  // functional prog

  val incrementor = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }

  incrementor(1)

  val anonInc = (x: Int) => x + 1
  List(1, 2, 3).map(anonInc) // higher order funct
  //map, flatmap, filter
  //for-comprehension
  val pairs = for {
    num <- List(1, 2, 3) // if cond
    char <- List('a', 'b', 'c')
  } yield num + "-" + char

  //scala collections: Seq, Arrays, List, Vectors, Maps, Tuples
  val map = Map(
    "Rrj" -> 485,
    "Yes" -> 123
  )

  // "collections" : Option, Try
  val option = Some(2)

  //pattern matching
  val x = 2
  val order = x match {
    case 1 => "first"
    case 2 => "second"
    case _ => "other"
  }

  val bob = Person("Bob", 22)
  val greeting = bob match {
    case Person(n, _) => s"hi i'm $n"
  }

  //all the patterns in the basic course


}
