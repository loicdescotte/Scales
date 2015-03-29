## Scales

Usage :

```scala
import scale.Scale._

scala> majorScale("C").take(10).foreach(println)
C2
D2
E2
F2
G2
A3
B3
C3
D3
E3

scala> majorChord("A")
res0: List[String] = List(A2, C#2, E2)

```