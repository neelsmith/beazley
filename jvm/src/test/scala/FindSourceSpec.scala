package edu.holycross.shot.beazley
import org.scalatest.FlatSpec


//Painter,Beazley Number, Musuem ID, Shape, Find-spot, Comments, Geography


class FindSourceSpec extends FlatSpec {

  "The FindSource object"  should "be able to instantiate a single Find from a CSV String" in {
    val oneLine = "Sophilos,6, Berlin 1683, Amphora, Athens, Row A: two sphinxes. Row B: Two eagles with serpents in their beaks., 579885"

    val fnd = FindSource.fromCsv(oneLine)
    fnd match {
      case f: Find => {
        assert(f.painter == "Sophilos")
        assert(f.shape == "Amphora")
        assert(f.site == "Athens")    
        val expectedGeo = Point(23.72551565874,37.97652566656,579885)
        assert (f.pt == Some(expectedGeo))
      }
      case _ => fail("Should have created a Find object.")
    }
  }

}
