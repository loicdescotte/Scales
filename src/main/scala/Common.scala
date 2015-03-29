package scale

object Common {

  def loopListToStream[A](list: List[A]) = Stream.iterate((list.head, list.tail)){ case(head, tail) =>
    if(tail.isEmpty) (list.head, list.tail)
    else (tail.head, tail.tail)
  }.map(_._1)

}
