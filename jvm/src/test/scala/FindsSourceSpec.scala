package edu.holycross.shot.beazley
import org.scalatest.FlatSpec


//Painter,Beazley Number, Musuem ID, Shape, Find-spot, Comments, Geography


class FindsSourceSpec extends FlatSpec {

  "The FindsSource object"  should "be able to instantiate a single Find from a CSV String" in pending /*{
    val oneEntry = "Sophilos,6, Berlin 1683, Amphora, Athens, Row A: two sphinxes. Row B: Two eagles with serpents in their beaks., 579885"

    val fnd = FindsSource.fromShortList(oneEntry)
    fnd match {
      case f: Find => {
        assert(f.painter == "Sophilos")

        val regularizedShape = "amphora"
        assert(f.shape == regularizedShape)
        assert(f.site == "Athens 579885")
        val expectedGeo = Point(23.72551565874,37.97652566656,579885)
        assert (f.pt == Some(expectedGeo))
      }
      case _ => fail("Should have created a Find object.")
    }
  }*/

  it should "create a Find object even when geography is unknown" in pending /*{
    val oneEntry = "Sophilos,1, Athens 991, Neck-amphora; slender; long-necked: loutrophoros, Vourva, Three rows of animals on the body. Middle row: litany of animals. Upper: two rows of animals, ?"
    val fnd = FindsSource.fromShortList(oneEntry)
    fnd match {
      case f: Find => {
        assert(f.painter == "Sophilos")
        val regularizedShape = "amphora"
        assert(f.shape == regularizedShape)
        assert(f.site == "Vourva")
        assert (f.pt == None)
      }
      case _ => fail("Should have created a Find object.")
    }
  }*/


  it should "be able to create a Finds collection from a csv file" in pending/* {
    val src = "jvm/src/test/resources/sophilos.csv"
    val findsCollection = FindsSource.fromShortList(src)
    findsCollection match {
      case fc: Finds => {
        assert(fc.size == 40)
      }
      case _ => fail("Should have created  Finds collection.")
    }
  }*/



}
