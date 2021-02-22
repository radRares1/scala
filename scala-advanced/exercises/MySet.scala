package exercises

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean) {

  /*
    implement a functional set
   */

  def apply(element: A): Boolean =
    contains(element)

  def contains(elem: A): Boolean

  def +(elem: A): MySet[A]

  def ++(set: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B]

  def filter(predicate: A => Boolean): MySet[A]

  def foreach(f: A => Unit): Unit

  def -(elem: A): MySet[A]

  def --(anotherSet: MySet[A]): MySet[A]

  def &(anotherSet: MySet[A]): MySet[A]

  /*
    implement unary_1 => negation of a set
   */
  def unary_! : MySet[A]
}

//all elements of type A which statisy a prop
class PropertyBasedSet[A](property: A => Boolean) extends MySet[A] {
  //{x in A | property(x)}
  override def contains(elem: A): Boolean = property(elem)

  //{x in A | prop(x)} + elem => {x in A | prop(x) || x==elem}
  override def +(elem: A): MySet[A] = {
    new PropertyBasedSet[A](x => property(x) || x == elem)
  }


  override def ++(set: MySet[A]): MySet[A] =
    new PropertyBasedSet[A](x => property(x) || set(x))

  override def map[B](f: A => B): MySet[B] = fail

  override def flatMap[B](f: A => MySet[B]): MySet[B] = fail

  override def filter(predicate: A => Boolean): MySet[A] =
    new PropertyBasedSet[A](x => property(x) && predicate(x))

  override def foreach(f: A => Unit): Unit = fail

  override def -(elem: A): MySet[A] = filter(x => x != elem)

  override def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet)

  override def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)

  override def unary_! : MySet[A] = new PropertyBasedSet[A](x => !property(x))

  def fail = throw new IllegalArgumentException("whoops this is weird")
}

class EmptySet[A] extends MySet[A] {
  override def contains(elem: A): Boolean = false

  override def +(elem: A): MySet[A] = new NonEmptySet[A](elem, this)

  override def ++(set: MySet[A]): MySet[A] = set

  override def map[B](f: A => B): MySet[B] = new EmptySet[B]

  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]

  override def filter(predicate: A => Boolean): MySet[A] = this

  override def foreach(f: A => Unit): Unit = ()


  override def -(elem: A): MySet[A] = this

  override def --(anotherSet: MySet[A]): MySet[A] = this

  override def &(anotherSet: MySet[A]): MySet[A] = this

  override def unary_! : MySet[A] = {

    new PropertyBasedSet[A](_ => true)

  }

}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A] {
  override def contains(elem: A): Boolean =
    elem == head || tail.contains(elem)

  override def +(elem: A): MySet[A] =
    if (this.contains(elem)) this
    else new NonEmptySet[A](elem, this)

  /*
    [1 2 3] ++ [4 5]
    [2 3] ++ [4 5] + 1
    [3] ++ [4 5] + 1 + 2
    [] ++ [4 5] + 1 + 2+ 3
    [4 5] + 1 + 2 + 3
    [4 5 1 2 3]
   */
  override def ++(set: MySet[A]): MySet[A] =
    tail ++ set + head

  override def map[B](f: A => B): MySet[B] = (tail map f) + f(head)

  override def flatMap[B](f: A => MySet[B]): MySet[B] =
    (tail flatMap f) ++ f(head)

  override def filter(predicate: A => Boolean): MySet[A] = {
    val filteredTail = tail filter predicate
    if (predicate(head)) filteredTail + head
    else filteredTail
  }

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail foreach f
  }

  override def -(elem: A): MySet[A] = {
    if (head == elem) tail
    else tail - elem + head
  }

  override def --(anotherSet: MySet[A]): MySet[A] = {
    filter(x => !anotherSet(x))
  }

  override def &(anotherSet: MySet[A]): MySet[A] = {
    filter(anotherSet) // intersection and filter are the same thing
  }

  //new operator
  def unary_! : MySet[A] = {
    new PropertyBasedSet[A](x => !this.contains(x))
  }


  /*
   Exercise:
   -remove an element
   -intersection with another set
   -difference with another set
  */


}

object MySet {
  /*
    val s = MySet(1,2,3) = buildSet(seq(1,2,3),[])
    = buildSet(seq(2,3),[] + 1)
    = buildSet(seq(3),[1] + 2)
    = buildSet(seq(),[1,2] + 3)
    = [1,2,3]
   */
  def apply[A](values: A*): MySet[A] = {
    @tailrec
    def buildSet(valueSeq: Seq[A], acc: MySet[A]): MySet[A] =
      if (valueSeq.isEmpty) acc
      else buildSet(valueSeq.tail, acc + valueSeq.head)

    buildSet(values, new EmptySet[A])
  }
}

object MySetTest extends App {
  val s = MySet(1, 2, 3, 4)
  s + 5 ++ MySet(-1, -2) + 3 flatMap (x => MySet(x, 10 * x)) filter (x => x % 2 == 0) foreach println

  val negative = !s //s.unary_!, all the nats ne to 1 2 3 4
  println(negative(2))
  println(negative(5))
  val negativeEven = negative.filter(_ % 2 ==0)
  println(negativeEven(5))
  val negativeEven5 = negativeEven + 5
  println(negativeEven5(5))
}

