import collection.mutable.Stack
import org.scalatest._
import scales._
import scales.Scales._

class ScalesTest extends FlatSpec with Matchers {

  "Scales" should "be generated for major, minor and other modes" in {
    val C_Dorian = modalScale("C", Modes.Dorian).take(8)
    C_Dorian.toList.map(_.toString) should be (List("C2", "D2", "D#2", "F2", "G2", "A3", "A#3", "C3"))
  }

}