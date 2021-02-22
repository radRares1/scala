package lectures.part1

object AdvancedPatternMatching extends App {

  val numbers = List(1)

  val desc = numbers match {
    case x :: Nil => println(s"the only elements is $x")
    case _ =>
  }
  /*
    -constants
    -wildcards
    -case classes
    -tuples
    -some special magic like above
   */
  class Person(val name: String, val age: Int)

  object PersonPattern{
    def unapply(person: Person):Option[(String, Int) ]= {
      if(person.age < 21) None
      else Some((person.name,person.age))
    }

    def unapply(age: Int): Option[(String)] =
      Some(if(age<21) "minor" else "major")

  }

  val bob = new Person("Bob",25)
  val greeting = bob match {
    case PersonPattern(n,a) => s"hi im $n and im $a"
  }
  println(greeting)

  val legalStatus = bob.age match {
    case PersonPattern(status) => s"My status is $status"
  }
  println(legalStatus)
  /*
   ex)
   pattern match for
   */
  val n:Int = 4
  val mathProp = n match {
    case x if x < 10 => "single digit"
    case x if x%2 == 0 => "even"
    case _ => "empty"
  }

  object even{
    def unapply(arg: Int):Boolean = arg%2==0
  }

  object singleDigit{
    def unapply(arg: Int):Boolean =arg<10
  }

  val solvedMathProp = n match {
    case even() => s"$n is even"
    case singleDigit() => s"$n single digit"
    case _ => "nothing matched"
  }

  println(solvedMathProp)

  //infix patterns
  case class Or[A,B](a:A, b:B)
  val either = Or(2,"two")
  val humanDesc = either match {
    case number Or string => s"$number is written as $string"
  }
  println(humanDesc)

  //decomposing seqs
  val vararg = numbers match {
    case List(1, _*) => "starts with 1"
  }

  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }
  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head:A, override val tail:MyList[A]) extends MyList[A]

  object MyList{
    def unapplySeq[A](list:MyList[A]):Option[Seq[A]] = {
      if(list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
    }
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3,Empty)))
  val decomposed = myList match {
    case MyList(1,2,_*) => "starting with 1 and 2"
    case _ => "idk"
  }
  println(decomposed)

  //custom return types for unnaply
  //isEmpty: Boolean, get:something

  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object PersonWrapper {
    def unapply(person:Person): Wrapper[String] = new Wrapper[String] {
      def isEmpty = false
      def get = person.name
    }
  }

  println(bob match {
    case PersonWrapper(n) => s"name is $n"
    case _ => "idk"
  })

}
