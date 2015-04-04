package scales

object Common {

  def loopListToStream[A](list: List[A]): Stream[A] = list.toStream #::: loopListToStream(list)

}
