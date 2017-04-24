package edu.holycross.shot.beazley


import scala.scalajs.js
import js.annotation.JSExport

@JSExport case class Find(painter: String, shape: String, site: String, pt: Point) {

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
