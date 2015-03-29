## Scales

Music scales and chords (note + pitch) generator using Scala streams

Usage :

```scala
import scale.Scale._

scala> majorScale("A").take(10).foreach(println)
A2
B2
C#2
D2
E2
F#2
G#2
A3
B3
C#3

scala> majorChord("A")
res0: List[String] = List(A2, C#2, E2)

```
