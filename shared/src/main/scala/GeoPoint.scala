package edu.holycross.shot.beazley


import scala.scalajs.js
import js.annotation.JSExport

@JSExport case class Point(x: Double, y: Double, pleiadesId: Integer) {

  override def toString = {
    x.toString + "," + y.toString
  }
}
