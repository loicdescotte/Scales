## Scales

Music scales (notes + pitch) and chords generator using Scala streams

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
To find a minor scale, use `minorScale` method instead of `majorScale`

Find a chord :

```
scala> majorChord("A")
res0: List[scales.Note] = List(A, C#, E)

scala> minor7Chord("C")
res1: List[scales.Note] = List(C, D#, G, A#)
```

Find a chord sequence for a scale :

```
scala> chordsForMinorScale("C").take(7).foreach(println)
(C min,List(C, D#, G))
(D5 dim,List(D, F, G#))
(D# maj,List(D#, G, A#))
(F min,List(F, G#, C))
(G min,List(G, A#, D))
(G# maj,List(G#, C, D#))
(A# maj,List(A#, D, F))
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

scala> chordsForModalScale("C", Modes.Dorian).take(7).foreach(println)
(C min,List(C, D#, G))
(D min,List(D, F, A))
(D# maj,List(D#, G, A#))
(F maj,List(F, A, C))
(G min,List(G, A#, D))
(A5 dim,List(A, C, D#))
(A# maj,List(A#, D, F))
```

And get modal variations for a scale :

```
variants("D", Modes.Dorian)
res1: List[String] = List(D Dorian, E Phrygian, F Lydian, G Mixolydian, A Aeolian, B Locrian, C Ionian)
```

Note : Default pitch is 2 but can be changed in the Scales class constructor