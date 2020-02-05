package edu.holycross.shot.beazley

import scala.io._

case class PleiadesData(pleiadesNum : Integer) {

  /** Base URL for Pleiades REST interface.*/
  val url: String = "https://pleiades.stoa.org/places"


  /** Pleiades JSON data for a Pleiades ID.*/
  def json : String = {
    val url = "https://pleiades.stoa.org/places/" + pleiadesNum + "/json"
    Source.fromURL(url).getLines.toVector.mkString("\n")
  }


  /** Optional Point object from Pleiades data.  Will be Some(Point)
  * if Pleiades has a parseable "reprPoint" property, or None otherwise. */
  def pointOption: Option[Point] = {
    try {
      val res = json
      if (res.contains("reprP")) {
        val filt = res.split("reprP")
        val pt2 = filt(1).split("\n").toVector
        val s = pt2(1) + pt2(2)
        val v = s.replaceAll("[ ]+","").split(",").toVector
        Some(Point(v(0).toDouble,v(1).toDouble, pleiadesNum))

      } else {
        println("No reprPoint found for " + pleiadesNum)
        None
      }
    } catch {
      case e: Throwable => None
    }
  }
}

object PleiadesData {

  /** */
  def apply(pleiadesId: String): PleiadesData = {
    try {
      val n = pleiadesId.toInt
      PleiadesData(n)

    } catch {
      case e: Throwable => {
        throw new Exception("Unable to parse PleiadesData for ID " + pleiadesId)
      }
    }
  }
}
