/*
 * Copyright © 2012 - 2016 Lightbend, Inc. All rights reserved.
 */

package com.lightbend.training.scalatrain

import play.api.libs.json.{JsValue, Json}

import scala.util.{Failure, Success, Try}

object Time {

  def fromMinutes(minutes: Int): Time =
    Time(minutes / 60, minutes % 60)

  //pulledSolution
  def fromJson2(json: JsValue): Option[Time] = {
    val tryTime = for {
      hours <- Try((json \ "hours").as[Int])
      minutes <- Try((json \ "minutes").as[Int]).recover({ case _: Exception => 0 })
    } yield Time(hours, minutes)
    tryTime.toOption
  }

  //similar solution
  def fromJson(json: JsValue): Option[Time] = {
    val tryTime = for {
      hours <- Try((json \ "hours").as[Int])
      minutes <- Try((json \ "minutes").as[Int])match {
        case Success(value) => Success(value)
        case Failure(_) => Success(0)
      }
    } yield Time(hours, minutes)
    tryTime.toOption
  }
  //TODO this won't work idk why do ask!!!!
  def fromJson3(js: JsValue):Option[Time]= {
        var hours = Try((js \ "hours").as[Int])

        var minutes = Try((js \ "minutes").as[Int]) match {
          case Success(v) => Success(v)
          case Failure(_) => Success(0)
        }
        Option(Time(hours.get, minutes.get))
      }

  /*
  TODO
      1)why does the above code work and the below does not
      2)also I did not find why you can't just do class.toOption instead of Option(class)
      update: class.toOption works with Success
      <def toOption: Option[T] = if (isSuccess) Some(get) else None>
      but shouldn't Option(Try(Time(hours.get, minutes.getOrElse(0))).get) be None if the try fails?
      since yield still returns a value and a val = Time(hours.get, minutes.get) is still a value
      i have no clue why it wouldn't work ;-;
      3) how does match works with try cause it won't let me assign the value


    def fromJson(js: JsValue):Option[Time]= {
      val hours = Try(js \ "hours").as[Int])
      val minutes = Try((js \ "minutes").as[Int])
      Option(Try(Time(hours.get, minutes.getOrElse(0))).get)
    }
    */


}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time]{
  require(hours >= 0 && hours <= 23, "hours must be within 0 and 23")
  require(minutes >= 0 && minutes <= 59, "minutes must be within 0 and 59")

  val asMinutes: Int =
    hours * 60 + minutes

  def minus(that: Time): Int =
    this.asMinutes - that.asMinutes

  def -(that: Time): Int =
    minus(that)

  override lazy val toString: String ={
    f"$hours%02d:$minutes%02d"
  }

  override def compare(that: Time): Int = {
    this.asMinutes - that.asMinutes
  }

  def toJson:JsValue={
    Json.obj(
      "hours" -> this.hours,
      "minutes" -> this.minutes
    )
  }
}
