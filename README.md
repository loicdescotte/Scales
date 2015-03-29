## Scales

Music scales and chords (note + pitch) generator using Scala streams

### Usage

Find a scale :

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
```

Find a chord :

```
scala> majorChord("A")
res0: List[String] = List(A2, C#2, E2)
```

You can also use modes :

```scala
scala> dorianScale("C").take(10).foreach(println)
C2
D2
D#2
F2
G2
A3
A#3
C3
D3
D#3

```