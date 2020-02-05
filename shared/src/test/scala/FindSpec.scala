package edu.holycross.shot.beazley
import org.scalatest.FlatSpec


//Painter,Beazley Number, Musuem ID, Shape, Find-spot, Comments, Geography


class FindSpec extends FlatSpec {

  "A Find"  should  "support instantiation from a csv string" in pending /*{
    val csv = "Sophilos,Amphora,Athens,23.72551565874,37.97652566656,579885"
    val find = Find(csv)
    find match {
      case f: Find => assert(true)
      case _ => fail("Should have created a Find object")
    }
  }*/
  it should "offer serialization to csv" in pending /*{
    val csv = "Sophilos,amphora,Athens,23.72551565874,37.97652566656,579885"
    val find = Find(csv)
    assert(find.delimitedText() == csv)
  }*/

}
