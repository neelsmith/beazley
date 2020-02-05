package edu.holycross.shot.beazley

import scala.io.Source
import sys.process._


import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map


/** Factory for generating a Vector of [[Find]]s.
*/
object FindsSource {

  /** Create a Vector of [[Find]]s from a file in .csv format
  *
  * @param fName Name of csv file.
  */
  def fromFile(fName : String, delimiter: String = "#") : Finds = {
    val lns = Source.fromFile(fName).getLines.toVector
    val edges = for (ln <- lns.tail) yield {
      //fromShortCsv(ln)
    }
    //Finds(edges)
    Finds(Vector.empty[Find])
  }


  /** Parse a delimited-text representation of minimal properties to
  * a [[Find]].
  *
  * @param cex One line of delimited-text data.
  * @param delimiter String value used as column delimiter.
  */
  def findFromShortList(cex: String, delimiter: String = "#") : Find ={
    //Painter,Beazley Number, Musuem ID, Shape, Find-spot, Comments, Geography
    val doubled = delimiter + delimiter
    val cols = cex.replaceAll(doubled,"${delimiter} ${delimiter}").split(delimiter)


    if (cols.size < 5) {
      throw new Exception(s"Find: too few columns (${cols.size}) in " + cex)

    } else {
      val painter = cols(0).trim
      val shape = Find.shapeForString(cols(3).trim)
      val site = PleiadesData(cols(4).trim)
      Find(painter,shape,site + " " + cols(6).trim,site.pointOption)

/*      if (cols.size > 6) {




        val pt = PleiadesData(cols(6).trim) //pointOption(cols(6).trim)
        pt match {
          case None =>  Find(painter,shape,site,None)
          case _ => Find(painter,shape,site + " " + cols(6).trim,pt.ptOption)
        }

      } else {
        Find(painter,shape,site,None)
      }
*/
    }
  }








}
