package com.lightbend.training.scalatrain

sealed abstract class TrainInfo()
{
  def number:Int
}

case class InterCityExpress(no:Int, hasWifi:Boolean=false) extends TrainInfo
{
  override def number: Int = no
}

case class RegionalExpress(no:Int) extends TrainInfo
{
  override def number: Int = no
}

case class BavarianRegional(no:Int) extends TrainInfo
{
  override def number: Int = no
}