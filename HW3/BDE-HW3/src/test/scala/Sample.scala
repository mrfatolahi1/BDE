object Sample {
  def main(args: Array[String]): Unit = {
    //    val list: List[Int] = List(0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9)
    //    println(for (element <- list if element != 5) yield element)

    //    for (i <- list.indices){
    //      if (i == 5){
    //        list.patch();
    //      }
    //      println(i)
    //    }

//    val source = scala.io.Source.fromFile("/home/mohammadreza/Documents/ALL.txt")
//    val EEE = source.getLines().flatMap(x => x.split(" ")).map(x => (x, 1))
//    val WWW = source.getLines().flatMap(x => x.split(" ")).map(x => (x, 1)).reduceByKey((a,b) => ("WWW", a._2+b._2))
//    println(WWW)
//    while (EEE.hasNext){
//      println(EEE.next())
//    }

    //    val stream = 1 #:: 2 #:: 8 #:: Stream.empty
    //    println(stream)
    //    println(stream.map{_+5})

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