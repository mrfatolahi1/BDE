object Q1_Problem3_Scala {
  def main(args: Array[String]): Unit = {
    val source = scala.io.Source.fromFile("src/test/scala/data/friends.json")
    val namesList = source.getLines()
      .map(x => x.substring(2, x.length-2).split("\", \"")(0)).toList
    val namesSet = namesList.toSet
    for (name <- namesSet){
      println(name, namesList.count(x => x.equals(name)))
    }
  }
}
