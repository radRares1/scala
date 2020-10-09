package lectures.l1

object Functions extends App {

  def funct(a:String, b:Int):String =
    {
      a + " " + b
    }

  println(funct("hello",3))

  def noParamsFunct():Int = 42

  println(noParamsFunct())
  println(noParamsFunct)

  def aRepeatedFunct(aString:String,n:Int):String = {
    if(n==1) aString
    else aString + aRepeatedFunct(aString,n-1)
  }

  println(aRepeatedFunct("yes",2))

  //WHEN IN NEED OF LOOPS, CHOOSE RECURSION

  def sideEffFunc(aString:String):Unit = println(aString)

  def aBigFunc(n:Int):Int={
    def another(a:Int):Int = a+1

    another(3)
  }

  /*
  1. say hi to kids (name,age)=>"Hi my name is $name and I am $ age years old
  2. factorial
  3. a fb func
  4. test if a no is prime
   */

  def henlo(name:String,age:Int):Unit=println(s"Hi my name is $name, I am $age y/o")

  def factorial(n:Int):Int={
    if(n<=0) 1
    else n*factorial(n-1)
  }

  def fib(n:Int):Int = {
    if(n<=2) 1
    else fib(n-1) + fib(n-2)
  }

  //any number n is prime iff (n-1)! + 1 %n == 0
  def isPrime(n:Int):Boolean = factorial(n - 1) ==  1 % n

  //or
//
//  def isPrime(n:Int):Boolean ={
//
//    def isPrimeUntil(t: Int) :Boolean=
//      if(t<=1) true
//      else n%t!=0 &&isPrimeUntil(t-1)
//
//    isPrimeUntil(n/2)
//
//  }


  println(henlo("anba",4))
  println(factorial(3))
  println(fib(8))
  println(isPrime(37*13))
}
