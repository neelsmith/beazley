package edu.holycross.shot.beazley

import scala.scalajs.js
import js.annotation.JSExport

/** Minimal information about find spot of an attributed vase.
*
* @param painter Attributed painter.
* @param shape Shape of piece.
* @param site Name of site.
* @param pt Lat-lon coordinates if known.
*/
@JSExport case class Find(
  painter: String,
  shape: Option[Shape],
  site: String,
  pt: Option[Point]) {



  /** Extract pleiades ID from site
  *
  */
  def pleiades: String = {
    val re = "[^0-9]".r
    re.replaceAllIn(site,"")
  }


  /** Representation of Find as delimited text file.*/
  def delimitedText(delimiter: String = "#"): String = {
    pt match {
      case None => {
        s"${painter}${delimiter}${shape}${delimiter}${site}${delimiter}${delimiter}${delimiter}"
      }
      case _ => {
        val geo = pt.get
        s"${painter}${delimiter}${shape}${delimiter}${site}${delimiter}${geo.x}${delimiter}${geo.y}${delimiter}${geo.pleiadesId}"
      }
    }
  }

  /** Representation of find as KML string usable in Google maps. */
  def kml: String = {
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


/** Factory for making [[Find]] objects.*/
@JSExport object Find {

  /** Create [[Find]] from  delimited text representation.
  *
  * @param cex Delimited text representation of a Find.
  */
  def apply(cex: String, delimiter: String = "#"): Find = {
    val cols = cex.split(delimiter)
    val painter = cols(0).trim
    val shape = shapeForString(cols(1).trim)
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


  def shapeForString(shape: String) : Option[Shape] = {
    shape.toLowerCase match {
      case shp if shp.contains("krater") => Some(Krater)
      case _ => None
    }
  }

  /** Regularize shape names.
  * @param shape String value for shape.
  */
  def nameForString(shape: String) : String = {
    shape.toLowerCase match {
      case k if k.contains("krater") => "krater"
      case a if a.contains("amphora") => "amphora"
      case h if h.contains("hydria") => "hydria"
      case p if p.contains("pelik") => "pelike"
      case o if o.contains("oinoch") => "oinochoe"
      case l if l.contains("lekyth") => {
        if (l.contains("white")){"WG lekythos"} else {"lekythos"}
      }
      case ps if ps.contains("psyk") => "psykter"
      case sk if sk.contains("skyph") => "skyphos"
      case st if st.contains("stamn") => "stamnos"
      case d if d.contains("dino") => "dinos"
      case lek if lek.contains("lekan") => "lekanis"
      case lout if lout.contains("louter") => "louterion"
      case loutro if loutro.contains("loutroph") => "loutrophoros"
      case leb if leb.contains("lebes") => "lebes"
      case tr if tr.contains("tripod-kothon") => "tripod-kothon"

      case u: String => "Fragment or unidentified"
    }


  }

}
