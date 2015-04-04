## Scales

Music scales and chords (note + pitch) generator using Scala streams

### Usage

Find a scale :

```scala
import scales.Scales._

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
scala> modalScale("C", Modes.Dorian).take(10).foreach(println)
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

And get modal variations for a scale :

```
variants("D", Modes.Dorian)
res1: List[String] = List(D Dorian, E Phrygian, F Lydian, G Mixolydian, A Aeolian, B Locrian, C Ionian)
```

Find a chord sequence for a scale :

```
scala> chordsForMinorScale("C").take(7).foreach(println)
(C2 minor,List(C2, D2, D#2))
(D2 minor dim,List(D2, D#2, F2))
(D#2 major,List(D#2, F2, G2))
(F2 minor,List(F2, G2, G#2))
(G2 minor,List(G2, G#2, A#3))
(G#2 major,List(G#2, A#3, C3))
(A#3 major,List(A#3, C3, D3))

scala> chordsForMajorScale("C").take(7).foreach(println)
(C2 major,List(C2, D2, E2))
(D2 minor,List(D2, E2, F2))
(E2 minor,List(E2, F2, G2))
(F2 major,List(F2, G2, A3))
(G2 major,List(G2, A3, B3))
(A3 minor,List(A3, B3, C3))
(B3 minor dim,List(B3, C3, D3))

scala> chordsForModaleScale("C", Modes.Dorian).take(7).foreach(println)
(C2 minor,List(C2, D2, D#2))
(D2 minor,List(D2, D#2, F2))
(D#2 major,List(D#2, F2, G2))
(F2 major,List(F2, G2, A3))
(G2 minor,List(G2, A3, A#3))
(A3 minor dim,List(A3, A#3, C3))
(A#3 major,List(A#3, C3, D3))
```

Note : Default pitch is 2 but can be changed in the Scales class constructor