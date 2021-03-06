package scales

import Common._

sealed trait Note
case object A extends Note
case object A_sharp extends Note {override def toString = "A#"}
case object B extends Note
case object C extends Note
case object C_sharp extends Note {override def toString = "C#"}
case object D extends Note
case object D_sharp extends Note {override def toString = "D#"}
case object E extends Note
case object F extends Note
case object F_sharp extends Note {override def toString = "F#"}
case object G extends Note
case object G_sharp extends Note {override def toString = "G#"}

case class NoteWithPitch(note: Note, pitch: Int){
  override def toString = s"$note$pitch"
}

object Note {
  def fromString(note: String): Option[Note] = {
    note match {
      case "A" => Some(A)
      case "A#" => Some(A_sharp)
      case "Bb" => Some(A_sharp)
      case "B" => Some(B)
      case "C" => Some(C)
      case "C#" => Some(C_sharp)
      case "Db" => Some(C_sharp)
      case "D" => Some(D)
      case "D#" => Some(D_sharp)
      case "Eb" => Some(D_sharp)
      case "E" => Some(E)
      case "F" => Some(F)
      case "F#" => Some(F_sharp)
      case "Gb" => Some(G)
      case "G" => Some(G)
      case "G#" => Some(G_sharp)
      case "Ab" => Some(G_sharp)
      case _ => None
    }
  }
}

//degree starting from 0 / minor scale
case class Mode(degree: Int) {
  def name = degree % 7 match {
    case 0 => "Aeolian"
    case 1 => "Locrian"
    case 2 => "Ionian"
    case 3 => "Dorian"
    case 4 => "Phrygian"
    case 5 => "Lydian"
    case 6 => "Mixolydian"
  }
}

class Scales(defaultStartPitch: Int) {

  object Modes {
    object Aeolian extends Mode(0)
    object Locrian extends Mode(1)
    object Ionian extends Mode(2)
    object Dorian extends Mode(3)
    object Phrygian extends Mode(4)
    object Lydian extends Mode(5)
    object Mixolydian extends Mode(6)
  }

  implicit def stringToNote(str: String): Note = {
    Note.fromString(str).get
  }

  import Modes._

  val notes = List(A, A_sharp, B, C, C_sharp, D, D_sharp, E, F, F_sharp, G, G_sharp)

  val modes = List(Aeolian, Locrian, Ionian, Dorian, Phrygian, Lydian, Mixolydian)

  val modesStream = loopListToStream(modes)

  val noteStream : Stream[Note] = loopListToStream(notes)

  val stepsForMinorScale: Stream[Int] = loopListToStream(List(2, 1, 2, 2, 1, 2, 2))

  private def scaleGen(startDegree: Int, startPitch: Int, startingNote: Note) = {
    val scaleStream = Stream.iterate((startDegree, startPitch, startingNote)) { case (degree, pitch, note) =>
      val noteStreamIndex = noteStream.indexWhere(_ == note)
      val step = stepsForMinorScale(degree)
      val nextPitch = {
        if(step == 1 && note == G_sharp) pitch +1
        else if (step == 2 && (note == G_sharp || note == G)) pitch +1
        else pitch
      }
      (degree+1, nextPitch, noteStream(noteStreamIndex + step))
    }

    scaleStream map {
      case (degree, pitch, note) => NoteWithPitch(note, pitch)
    }
  }

  def modalScale(note: Note, mode: Mode) = scaleGen(mode.degree, defaultStartPitch, note)
  def majorScale(note: Note) = modalScale(note, Ionian)
  def minorScale(note: Note) = modalScale(note, Aeolian)

  def chordsForModalScale(note: Note, mode: Mode) = {
    val scaleNotes = modalScale(note, mode).take(7).toList
    scaleNotes.zipWithIndex.map { case (np, i) =>
      //step from tonal to 2nd + step from 2nd to 3rd
      val stepToThird = stepsForMinorScale(mode.degree + i) + stepsForMinorScale(mode.degree + i+1)
      val stepToFifth = stepToThird + stepsForMinorScale(mode.degree + i+2) + stepsForMinorScale(mode.degree + i+3)
      val isMinor = stepToThird == 3
      val isDim = stepToFifth < 7
      if(isDim) (s"${np.note}5 dim", minorDimChord(np.note))
      else if(isMinor) (s"${np.note} min", minorChord(np.note))
      else (s"${np.note} maj", majorChord(np.note))
    }
  }
  def chordsForMajorScale(note: Note) = chordsForModalScale(note, Ionian)
  def chordsForMinorScale(note: Note) = chordsForModalScale(note, Aeolian)

  def variants(note: Note, mode: Mode) = {
    val scale = modalScale(note, mode)
    // a scale contains 7 unique degrees
    scale.take(7).toList.zipWithIndex.map{ case (np, i) =>
      s"${np.note} ${Mode(mode.degree+i).name}"
    }
  }
  
  // index are 0 based so the tone is 0, the 3rd is 2 etc
  def majorChord(note: Note) = {
    val scale = majorScale(note)
    List(scale(0), scale(2), scale(4)).map(_.note)
  }

  def minorChord(note: Note) = {
    val scale = minorScale(note)
    List(scale(0), scale(2), scale(4)).map(_.note)
  }

  def minorDimChord(note: Note) = {
    val scale = minorScale(note)
    val dimFifth = {
      val index = noteStream.indexWhere(_ == scale(4).note)
      noteStream(index + 11)
    }
    val pitch = if (scale(4).note == A) scale(4).pitch - 1 else scale(4).pitch
    List(scale(0), scale(2), NoteWithPitch(dimFifth, pitch)).map(_.note)
  }

  def major7Chord(note: Note) = {
    val scale = majorScale(note)
    List(scale(0), scale(2), scale(4), scale(6)).map(_.note)
  }

  def minor7Chord(note: Note) = {
    val scale = minorScale(note)
    List(scale(0), scale(2), scale(4), scale(6)).map(_.note)
  }

}

//default start pitch = 2
object Scales extends Scales(2)