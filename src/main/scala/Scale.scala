package scale

import Common._

object Scale {
  
  val defaultStartPitch = 2

  val notes = List("A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#")

  val noteStream : Stream[String] = loopListToStream(notes)

  val stepsForMinorScale: Stream[Int] = loopListToStream(List(2, 1, 2, 2, 1, 2, 2))

  def scale(startDegree: Int, startPitch: Int, note: String) = {
    val scaleStream = Stream.iterate((startDegree, startPitch, note)) { case (degree, pitch, note) =>
      val noteStreamIndex = noteStream.indexWhere(_ == note)
      val step = stepsForMinorScale(degree)
      val nextPitch = {
        if(step == 1 && note == "G#") pitch +1
        else if (step == 2 && (note == "G#" || note == "G")) pitch +1
        else pitch
      }
      (degree+1, nextPitch, noteStream(noteStreamIndex + step))
    }
    scaleStream.map{case (d, h, n) => n + h}
  }

  def minorScale(note: String) = scale(0, defaultStartPitch, note)

  def locrianScale(note: String) = scale(1, defaultStartPitch, note)

  def majorScale(note: String) = scale(2, defaultStartPitch, note)

  def dorianScale(note: String) = scale(3, defaultStartPitch, note)

  def phrygianScale(note: String) = scale(4, defaultStartPitch, note)

  def lydianScale(note: String) = scale(5, defaultStartPitch, note)

  def mixolydianScale(note: String) = scale(6, defaultStartPitch, note)
  
  // index are 0 based so the tone is 0, the 3rd is 2 etc
  def majorChord(note: String) = {
    val scale = majorScale(note)
    List(scale(0), scale(2), scale(4))
  }

  def minorChord(note: String) = {
    val scale = minorScale(note)
    List(scale(0), scale(2), scale(4))
  }

  def major7Chord(note: String) = {
    val scale = majorScale(note)
    List(scale(0), scale(2), scale(4), scale(6))
  }

  def minor7Chord(note: String) = {
    val scale = minorScale(note)
    List(scale(0), scale(2), scale(4), scale(6))
  }


}