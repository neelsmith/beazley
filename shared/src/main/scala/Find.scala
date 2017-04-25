package edu.holycross.shot.beazley


import scala.scalajs.js
import js.annotation.JSExport


@JSExport case class Find(painter: String, shape: String, site: String, pt: Option[Point]) {

  def toCsv: String = {
    pt match {
      case None => {
        s"${painter},${shape},${site},,,"
      }
      case _ => {
        val geo = pt.get
        s"${painter},${shape},${site},${geo.x},${geo.y},${geo.pleiadesId}"
      }
    }
  }

  def toKml: String = {
    pt match {
      case None => ""
      case p: Option[Point] =>  raw"""<Placemark>
    <name>${site}</name>
    <description><p>${painter}, ${shape}.  Found at ${site}</p>
    </description>
    <styleUrl>#mint_style</styleUrl>
    <Point>
      <coordinates>${pt.get},0</coordinates>
    </Point>
    </Placemark>"""
    }
  }
}


// Factory for making from csv
@JSExport  object Find {

    def apply(csv: String): Find = {
      val cols = csv.split(",")
      val painter = cols(0).trim
      val shape = cols(1).trim
      val site = cols(2).trim
      val lon = cols(3).trim
      val lat = cols(4).trim
      val pleiades = cols(5).trim
      if ((lon.isEmpty) || (lat.isEmpty) || (pleiades.isEmpty) ) {
        Find(painter,shape,site,None)
      } else {
        Find(painter,shape,site,Some(Point(lon.toDouble,lat.toDouble,pleiades.toInt)))
      }
    }
}
