package lectures.l1

object DefaultArgs extends App{


    @scala.annotation.tailrec
    def factTail(x: Int, accumulator: BigInt=1): BigInt =
      if (x <= 1) accumulator
      else factTail(x - 1, x * accumulator)

  val fact10 = factTail(1)

  def savePic(format:String="jpg", width:Int=1920, height:Int=1080):Unit = println("saved")

  savePic(width=800,height = 600)
}
