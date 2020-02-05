package edu.holycross.shot.beazley



import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map
import scala.scalajs.js
import js.annotation.JSExport

/** A collection of [[Find]]s.
*
* @param finds Vector of [[Find]]s in this collection.
*/
@JSExport case class Finds(finds: Vector[Find])  {
  val preface = """<?xml version="1.0" encoding="UTF-8"?>
  <kml xmlns="http://www.opengis.net/kml/2.2">
    <Document>
    <Style id="group1">
      <IconStyle>
        <scale>0.5</scale>
        <Icon>
          <href>http://maps.google.com/mapfiles/kml/shapes/placemark_circle_highlight.png</href>
        </Icon>
      </IconStyle>
    </Style>
    <Style id="group2">
      <IconStyle>
        <Icon>
          <scale>1.0</scale>
            <href>http://maps.google.com/mapfiles/kml/shapes/placemark_circle_highlight.png</href>
        </Icon>
      </IconStyle>
    </Style>
    <Style id="group3">
      <IconStyle>
        <Icon>
          <scale>8.0</scale>
            <href>http://maps.google.com/mapfiles/kml/shapes/placemark_circle_highlight.png</href>
        </Icon>
      </IconStyle>
    </Style>
    <Style id="group4">
      <IconStyle>
        <Icon>
          <scale>16.0</scale>
            <href>http://maps.google.com/mapfiles/kml/shapes/placemark_circle_highlight.png</href>
        </Icon>
      </IconStyle>
    </Style>
    <Style id="group5">
      <IconStyle>
        <Icon>
          <scale>32.0</scale>
            <href>http://maps.google.com/mapfiles/kml/shapes/placemark_circle_highlight.png</href>
        </Icon>
      </IconStyle>
    </Style>
    <Style id="group6">
      <IconStyle>
        <Icon>
          <scale>64.0</scale>
            <href>http://maps.google.com/mapfiles/kml/shapes/placemark_circle_highlight.png</href>
        </Icon>
      </IconStyle>
    </Style>
  """

  /** Conclusion to KML document.
  */
  val trail = "</Document></kml>"

  /** Number of finds in the collection.
  */
  def size: Int = {
    finds.size
  }

  /** Create a new Finds collection containing only
  * hoards with known geographic location.
  */
  def located: Finds = {
    Finds(finds.filter(_.pt !=  None))
  }


/*
  def byShape: scala.collection.immutable.Map[String,Finds] = {
    val shapes = finds.groupBy(_.shape)
    shapes.map{ case (k,v) => (k,Finds(v)) }
  }
  */




  def bySite: scala.collection.immutable.Map[String,Finds] = {
    val sites = located.finds.groupBy(_.pleiades)
    sites.map{ case (k,v) => (k,Finds(v)) }

  }

  /** Set of mints represented in this collection
  * of hoards.

  def mintSet: Set[String] = {
    hoards.flatMap(_.mints).toSet
  }
*/

  /** Create KML representation of the full set of finds.
  */
  def toKml: String = {
    preface + finds.map(_.kml).mkString("\n") + trail
  }
}
