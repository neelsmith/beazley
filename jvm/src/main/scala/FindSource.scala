package edu.holycross.shot.beazley

import scala.io.Source


import scala.xml._

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map


/** Factory for generating a Vector of [[Find]]s.
*/
object FindSource {

  /** Create a Vector of [[Find]]s from a file in .csv format
  *
  * @param fName Name of csv file.
  */
  def fromFile(fName : String) : Finds = {
    Finds(Source.fromFile(fName).getLines.mkString("\n"))
  }

}
