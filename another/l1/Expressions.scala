package lectures.l1

object Expressions extends App {

  val x = 1 + 2 //Expression
  println(x)

  println(2+3*4)
  // + - * / & | ^ >> << >>>
  println(1 == x)
  println(!(1==x))

  var aVar = 2
  aVar+=3//-=,*=,/-..side effects
  println(aVar)

  //instr(do) vs expr(value ||\&&type)

  //if expr
  val aCond = true
  val aCondedVal = if(aCond) 5 else 3 //if expr
  println(aCondedVal)
  println(if(aCond) 5 else 3)

  var i = 0
  val a = while(i<3) {
    println(i)
    i+=1
  }
  //do not use any while/for LOOPS
  //everything is an expr

  val quirkyVal = (aVar=3) //unit===void
  print(quirkyVal)

  //side effs : prints, whiles, reassigns

  //blocks
  val block = {
    val y=2
    val z=y+1
    if (z>2) "hello" else "bye"
  }



}
