import scala.annotation.tailrec

object Q1 {
  trait ListExamlesProblem {
    def deleteForComprehension[T](e: T, list: List[T]): List[T] = {
      return for (element <- list if element != e) yield element
    }

    def deleteWithLibraryFunctions[T](e: T, list: List[T]): List[T] = {
      return list.filterNot(elm => elm == e)
    }

    def deleteUsingExplicitRecursionAndPatternMatching[T](e: T, list: List[T]): List[T] = {
      @tailrec
      def delete(list: List[T], acc: List[T] = List[T]()): List[T] = {
        list match {
          case head +: tail => delete(tail, if(head != e) head+:acc else acc)
          case _ => acc.reverse
        }
      }
      return delete(list)
    }
  }
}