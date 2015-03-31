package scale

import Common._

sealed trait Note
case object A extends Note
case object A_sharp extends Note
case object B extends Note
case object C extends Note
case object C_sharp extends Note
case object D extends Note
case object D_sharp extends Note
case object E extends Note
case object F extends Note
case object F_sharp extends Note
case object G extends Note
case object G_sharp extends Note

object Note {
  def fromString(note: String): Option[Note] = {
    note match {
      case "A" => Some(A)
      case "A#" => Some(A_sharp)
      case "B" => Some(B)
      case "C" => Some(C)
      case "C#" => Some(C_sharp)
      case "D" => Some(D)
      case "D#" => Some(D_sharp)
      case "E" => Some(E)
      case "F" => Some(F)
      case "F#" => Some(F_sharp)
      case "G" => Some(G)
      case "G#" => Some(G_sharp)
      case _ => None
    }
  }
}

object Scale {

  implicit def stringToNote(str: String): Note = {
    Note.fromString(str).get
  }

  val defaultStartPitch = 2

  val notes = List(A, A_sharp, B, C, C_sharp, D, D_sharp, E, F, F_sharp, G, G_sharp)

  val noteStream : Stream[Note] = loopListToStream(notes)

  val stepsForMinorScale: Stream[Int] = loopListToStream(List(2, 1, 2, 2, 1, 2, 2))

  def scale(startDegree: Int, startPitch: Int, startingNote: Note): Stream[(Note, Int)] = {
    Stream.iterate((startDegree, startPitch, startingNote)) { case (degree, pitch, note) =>
      val noteStreamIndex = noteStream.indexWhere(_ == note)
      val step = stepsForMinorScale(degree)
      val nextPitch = {
        if(step == 1 && note == G_sharp) pitch +1
        else if (step == 2 && (note == G_sharp || note == G)) pitch +1
        else pitch
      }
      (degree+1, nextPitch, noteStream(noteStreamIndex + step))
    } map {
      case (d, pitch, note) => (note, pitch)
    }
  }

  def minorScale(note: Note) = scale(0, defaultStartPitch, note)

  def locrianScale(note: Note) = scale(1, defaultStartPitch, note)

  def majorScale(note: Note) = scale(2, defaultStartPitch, note)

  def dorianScale(note: Note) = scale(3, defaultStartPitch, note)

  def phrygianScale(note: Note) = scale(4, defaultStartPitch, note)

  def lydianScale(note: Note) = scale(5, defaultStartPitch, note)

  def mixolydianScale(note: Note) = scale(6, defaultStartPitch, note)
  
  // index are 0 based so the tone is 0, the 3rd is 2 etc
  def majorChord(note: Note) = {
    val scale = majorScale(note)
    List(scale(0), scale(2), scale(4))
  }

  def minorChord(note: Note) = {
    val scale = minorScale(note)
    List(scale(0), scale(2), scale(4))
  }

  def major7Chord(note: Note) = {
    val scale = majorScale(note)
    List(scale(0), scale(2), scale(4), scale(6))
  }

  def minor7Chord(note: Note) = {
    val scale = minorScale(note)
    List(scale(0), scale(2), scale(4), scale(6))
  }


}