import org.scalatest._
import scales._
import scales.Scales._

class ScalesTest extends FlatSpec with Matchers {

  "Scales major, minot and other mods" should "be correct and have the right display" in {
    val A_Major = majorScale("A").take(8)
    A_Major.toList.map(_.toString) should be(List("A2","B2","C#2","D2","E2","F#2","G#2","A3"))

    val A_Minor = minorScale("A").take(8)
    A_Minor.toList.map(_.toString) should be(List("A2","B2","C2","D2","E2","F2","G2","A3"))

    val C_Dorian = modalScale("C", Modes.Dorian).take(8)
    C_Dorian.toList.map(_.toString) should be(List("C2", "D2", "D#2", "F2", "G2", "A3", "A#3", "C3"))
  }

  "Chords" should "be generated for major, minor and dim" in {
    majorChord("A").map(_.toString) should be(List("A", "C#", "E"))
    minorChord("C").map(_.toString) should be(List("C", "D#", "G"))
    minorDimChord("C").map(_.toString) should be(List("C", "D#", "F#"))
    minor7Chord("C").map(_.toString) should be(List("C", "D#", "G", "A#"))
  }

  "Variations" should "find alternative scales from a scale" in {
    variants("D", Modes.Dorian) should be(List("D Dorian", "E Phrygian", "F Lydian", "G Mixolydian", "A Aeolian", "B Locrian", "C Ionian"))
  }

  "Chords sequence for a scale" should "be correct and have the right display" in {
    val chordsMinC = chordSequenceDisplay(chordsForMinorScale("C"))
    chordsMinC(0) should be("C min",List("C", "D#", "G"))
    chordsMinC(6) should be("A# maj",List("A#", "D", "F"))

    val chordsMajC = chordSequenceDisplay(chordsForMajorScale("C"))
    chordsMajC(0) should be("C maj",List("C", "E", "G"))
    chordsMajC(6) should be("B5 dim",List("B", "D", "F"))

    val chordsDorianC = chordSequenceDisplay(chordsForModalScale("C", Modes.Dorian))
    chordsDorianC(0) should be("C min",List("C", "D#", "G"))
    chordsDorianC(6) should be("A# maj",List("A#", "D", "F"))
  }

  private def chordSequenceDisplay(chordSequence: List[(String, List[Note])]) = chordSequence.map{case (name, notes) => (name, notes.map(_.toString))}
}