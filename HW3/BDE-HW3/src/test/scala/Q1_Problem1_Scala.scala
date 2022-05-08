import scala.collection.mutable.ListBuffer

object Q1_Problem1_Scala {
  def main(args: Array[String]): Unit = {
    val source = scala.io.Source.fromFile("src/test/scala/data/books.json")
    val lines = source.getLines()
      .flatMap(x => x.split("\n"))
      .map(m => (m.split(" ")(0).substring(2, m.split(" ")(0).length-2), m.split("\", \"")(1)))
      .map(m => (m._1, m._2.replace(",", "").replace(",", "")))

    var words: ListBuffer[String] = ListBuffer()
    val linesList = lines.toList
    for (line <- linesList){
//      for (m <- line){
//        println(m)
//      }
      line._2.split(" ").map(x => words.append(x))
    }
//    println(words)
//    println(words.length)

    val words2: List[String] = words.toSet.toList
//    println(words2)
//    println(words2.length)


    for (word <- words2){
      val docs: ListBuffer[String] = ListBuffer()
      for (line <- linesList){
        if (line._2.contains(word)){
          docs.append(line._1)
        }
      }
      val output = (word, docs)
      println(output)
    }

  }
}
