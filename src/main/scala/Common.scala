package scale

import scala.annotation.tailrec

object Common {

  //@tailrec 
  def loopListToStream[A](list: List[A]): Stream[A] = list.toStream #::: loopListToStream(list)

}
