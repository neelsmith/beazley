package edu.holycross.shot.beazley


import scala.scalajs.js
import js.annotation.JSExport


@JSExport case class Find(painter: String, shape: String, site: String, pt: Option[Point]) {

  def toKml: String = {
    raw"""<Placemark>
    <name>${site}</name>
    <description><p>${painter}, ${shape}.  Found at ${site}</p>
    </description>
    <styleUrl>#mint_style</styleUrl>
    <Point>
      <coordinates>${pt},0</coordinates>
    </Point>
    </Placemark>"""
  }
}


// Factory
/*
object Find {
  def apply(csv: String) = {
    //Painter,Beazley Number, Musuem ID, Shape, Find-spot, Comments, Geography
    val cols = csv.split(",")
    if (cols.size < 5) {
      throw new Exception("Find: too few columns in " + csv)
    } else {
      val painter = cols(0)
      val shape = cols(3)
      val site = cols(4)
      val pt = ptFromString(cols(6))
      Find(painter,shape,site,pt)
    }
  }


  // we don't need no steekin json parser
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
        println("No reprPoint found for " + id)
        None
      }
    } catch {
      case e: Throwable => None
    }
  }
}
*/
