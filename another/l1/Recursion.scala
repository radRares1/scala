package lectures.l1

import scala.annotation.tailrec

object Recursion extends App{

  def factorial(n:Int):Int={
    if(n<=1) 1
    else {
      println("factorial of "+n+" nah i need ("+(n-1)+")! first")

      val result = n*factorial(n-1)
      println("factorial of "+n)
        result
    }
  }
  //factorial(50000)

  def fact2(n:Int):BigInt={
    @scala.annotation.tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL RECURSION = use recursive call as the LAST expression

    factHelper(n, 1)
  }

  //println(fact2(50000))

  //when in need of loops use tail rec

  /*
  1.concatenate a string n times
  2.tail recursive prime func
  3.fib func tail rec
   */

  def concatS(a:String, n:Int):String ={
    @tailrec
    def concatHelper(a:String,n:Int,result:String):String =
      if(n<1) result
      else concatHelper(a,n-1,a+result)

    concatHelper(a,n,"")

  }

  println(concatS("idk",5))

  def primeTail(n:Int):Boolean={

    @scala.annotation.tailrec
    def primeHelp(x:Int, check:Boolean):Boolean=
      if(!check) false
      else if(x<=1) true
      else primeHelp(x-1, n % x != 0 && check)

    primeHelp(n/2,check = true)
  }

  println(primeTail(13*37))

  def fib(n:Int):Int = {
    if(n<=2) 1
    else fib(n-1) + fib(n-2)
  }

  def fibTail(n:Int):Int={

    @scala.annotation.tailrec
    def fibHelp(x:Int, last:Int, nextToLast:Int):Int=
      if(x >= n)last
      else fibHelp(x+1,last+nextToLast,last)


    if(n<=2)1
    fibHelp(2,1,1)
  }

  println(fibTail(8))
}
