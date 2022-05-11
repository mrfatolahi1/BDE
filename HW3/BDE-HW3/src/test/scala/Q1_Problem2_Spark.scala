import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

object Q1_Problem2_Spark {
  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate()
    val sparkContext: SparkContext = sparkSession.sparkContext

//    var lines = sparkContext.textFile("src/test/scala/data/records.json")
//      .flatMap(x => x.split("\n"))
//      .map(x => (Integer.parseInt(x.split(" ")(1).substring(1, x.split(" ")(1).length - 2)),
//        (x.split(" ")(0).substring(1, x.split(" ")(0).length - 1), x)))
//      .reduceByKey((a,b) => b.red)
////      .treeReduce()


    sparkContext.textFile("src/test/scala/data/records.json")
      .flatMap(x => x.split("\n"))
      .map(x => (Integer.parseInt(x.split(" ")(1).substring(1, x.split(" ")(1).length - 2)), x))
//      .reduce((a, b))

  }
}
