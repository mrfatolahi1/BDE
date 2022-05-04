object Q1_Problem2 {
  def main(args: Array[String]): Unit = {
    val source = scala.io.Source.fromFile("/home/mohammadreza/Documents/records.json")
    val lines = source.getLines()
      .flatMap(x => x.split("\n"))
      .map(x => (Integer.parseInt(x.split(" ")(1).substring(1, x.split(" ")(1).length - 2)),
        x.split(" ")(0).substring(1, x.split(" ")(0).length - 1), x))
    val linesList = lines.toList
    val ordersList = linesList.take(8)
    for (order <- ordersList){
      for (line <- linesList){
        if (order._2 != line._2 && order._1 == line._1){
          println(order._3.substring(0, order._3.length-1)+", "+line._3.substring(1, line._3.length))
        }
      }
    }
  }
}
