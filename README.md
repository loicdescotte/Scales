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
C min List(C2, D#2, G2)
D5 dim List(D2, F2, G#2)
D# maj List(D#2, G2, A#3)
F min List(F2, G#2, C3)
G min List(G2, A#3, D3)
G# maj List(G#2, C3, D#3)
A# maj List(A#2, D2, F2)

scala> chordsForMajorScale("C").take(7).foreach(println)
C maj List(C2, E2, G2)
D min List(D2, F2, A3)
E min List(E2, G2, B3)
F maj List(F2, A3, C3)
G maj List(G2, B3, D3)
A min List(A2, C2, E2)
B5 dim List(B2, D2, F2)

scala> chordsForModaleScale("C", Modes.Dorian).take(7).foreach(println)
C min List(C2, D#2, G2)
D min List(D2, F2, A3)
D# maj List(D#2, G2, A#3)
F maj List(F2, A3, C3)
G min List(G2, A#3, D3)
A5 dim List(A2, C2, D#2)
A# maj List(A#2, D2, F2)
```

Note : Default pitch is 2 but can be changed in the Scales class constructor