package edu.holycross.shot.beazley

import scala.io.Source
import sys.process._


import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map


/** Factory for generating a Vector of [[Find]]s.
*/
object FindSource {

  /** Create a Vector of [[Find]]s from a file in .csv format
  *
  * @param fName Name of csv file.
  */
  def findsFromFile(fName : String) = { //: Finds = {
    val lns = Source.fromFile(fName).getLines.toVector
    val edges = for (ln <- lns.drop(1)) yield {
      fromCsv(ln)
    }
    Finds(edges)
  }


  /** Create a single [[Find]] object from  csv string.
  * @param csv One row of data in csv format.
  */
  def fromCsv(csv: String) = {
    //Painter,Beazley Number, Musuem ID, Shape, Find-spot, Comments, Geography
    val cols = csv.split(",")
    if (cols.size < 5) {
      throw new Exception("Find: too few columns in " + csv)
    } else {
      val painter = cols(0).trim
      val shape = cols(3).trim
      val site = cols(4).trim
      val pt = ptFromPleiades(cols(6).trim)
      Find(painter,shape,site,pt)
    }
  }

  /** Create optional [[Point]] object from a pleaiades identifier.
  *
  * @param pleiadesId Numeric identifier used in Pleiades project URIs.
  */
  def ptFromPleiades(pleiadesId: String) : Option[Point] = {
    try {
      val n = pleiadesId.toInt
      coordsForId(n)
    } catch {
      case e: Throwable => None
    }
  }


  /** Create Point object by looking up representative point
  * value in Pleiades JSON representation of a place.
  * We don't need no steekin json parser to get this: since pleiades
  * output is machine generated, we can reliably rip it apart as raw string.
  *
  * @param pleiadesNum Numeric identifier used in Pleiades project URIs.
  */
  def coordsForId(pleiadesNum: Integer): Option[Point] = {
    try {
      val fetch = "curl https://pleiades.stoa.org/places/" + pleiadesNum + "/json"
      val res = fetch.!!
      if (res.contains("reprP")) {
        val filt =res.split("reprP")
        val pt2 = filt(1).split("\n").toVector
        val s = pt2(1) + pt2(2)
        val v = s.replaceAll("[ ]+","").split(",").toVector
        Some(Point(v(0).toDouble, v(1).toDouble, pleiadesNum))
      } else {
        println("No reprPoint found for " + pleiadesNum)
        None
        }
      } catch {
        case e: Throwable => None
      }
    }


}
