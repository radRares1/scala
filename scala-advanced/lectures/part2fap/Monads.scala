package lectures.part2fap

object Monads extends App{

  trait Attempt[+A]{
    def flatMap[B](f: A => Attempt[B]): Attempt[B]

  }

  object Attempt {
    def apply[A](a: => A):Attempt[A] = {
      try{
        Success(a)
      }catch{
        case e:Throwable => Fail(e)
      }
    }
  }

  case class Success[+A](value: A) extends Attempt[A] {
    override def flatMap[B](f: A => Attempt[B]): Attempt[B] = {
      try{
        f(value)
      }catch{
        case e: Throwable => Fail(e)
      }
    }
  }

  case class Fail(e:Throwable) extends  Attempt[Nothing] {
    override def flatMap[B](f: Nothing => Attempt[B]): Attempt[B] =  this
  }

  /*
  left-identity

  unit.flatMap(f) = f(x)
  Attempt(x).flatMap(f) = f(x) // Success case
  Success(x).flatMap(f) = f(x)

  right-identity

  attempt.flatMap(unit) = attempt
  Success(x).flatMap(x => Attempt(x)) = Attempt(x) = Success(x)
  Fail(_).flatMap(...) = Fail(e)

  associativity

  attempt.flatMap(f).flatMap(g) == attempt.flatMap(x => f(x).flatMap(g))
  Fail(e).flatMap(f).flatMap(g) = Fail(e)
  Fail(e).flatMap(x => f(x).flatMap(g)) = Fail(e)

  Success(v).flatMap(f).flatMap(g) =
  f(v).flatMap(g) OR Fail(e)

  Succes(v).flatMap(x => f(x).flatMap(g)) =
  f(v).flatMap(g) OR Fail(e)

  */

  val attempt = Attempt{
    throw new RuntimeException("monad???")
  }
  println(attempt)

  /*
    1) implement a Lazy[T] monad = computation which will only be executed when it's needed
    unity/apply
    flatMap

    2) Monads = unit + flatMap
       Monads = unit+ map +flatten

       Monad[T] {

          def flatMap[B](f: T => Monad[B]): Monad[B] = ...

          def map[B](f: T => B): Monad[B] = flatMap(x => unit(f(x)) // Monad[B]
          def flatten(m: Monad[Monad[T]]): Monad[T] = m.flatMap((x: Monad[T]) => x)

          List(1,2,3).map(_ * 2) = List(1,2,3).flatMap(x => List(x * 2))
          List(List(1,2), List(3,4)).flatten =  List(List(1,2), List(3,4)).flatMap(x => x) = List(1,2,3,4)
       }
   */

  //1 - Lazy monad

  class Lazy[+A](value: => A) {
    //call by need
    private lazy val internalVal = value
    def use:A = internalVal
    def flatMap[B](f: (=> A) => Lazy[B]): Lazy[B] = f(internalVal)
  }
  object Lazy{
    def apply[A](value: => A): Lazy[A] = new Lazy(value) //unit
  }

  val lazyInst = Lazy {
    println("bruno mars")
    42
  }
  val flatMappedInstance = lazyInst.flatMap((x => Lazy{
    10*x
  }))

  val flatMappedInstance2 = lazyInst.flatMap((x => Lazy{
    10*x
  }))
  flatMappedInstance.use
  flatMappedInstance2.use
  /*
    left-identity
    unit.flatMap(f) = f(v)
    Lazy(v).flatMap(f) = f(v)

    right-identity
    l.flatMap(unit) = l
    Lazy(v).flatMap(x => Lazy(v) = Lazy(v)

    associativity : l.flatMap(f).flatMap(g) = l.flatMap(x => f(x).flatMap(g))
    Lazy(v).flatMap(f).flatMap(g) = f(v).flatMap(g)
    Lazy(v).flatMap(x => f(x).flatMap(g)) = f(v).flatMap(g)
   */

  // 2: map and flatten in terms of flatMap

}
