package edu.holycross.shot.beazley


import scala.scalajs.js
import js.annotation.JSExport


@JSExport case class Find(painter: String, shape: String, site: String, pt: Option[Point]) {



  /** Extract pleiades ID from site
  *
  */
  def pleiades: String = {
    val re = "[^0-9]".r
    re.replaceAllIn(site,"")
  }


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
    val shape = nameForString(cols(1).trim)
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
