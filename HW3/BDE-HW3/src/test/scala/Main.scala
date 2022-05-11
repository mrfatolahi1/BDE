import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

object Main {

  def main(args:Array[String]): Unit ={

    val sparkSession: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExample")
      .getOrCreate()
    val sparkContext: SparkContext = sparkSession.sparkContext

    val a = sparkContext.textFile("src/test/scala/data/books.json")/*.map(f=>{
      val lists=f.split(",")
      val temp=lists(0)
      val key=temp.substring(2,temp.length-1)
      val sentence=f.substring(temp.length+3,f.length-2)
      val ff=key+" "+sentence
      ff
    })*/

    for (f <- a.collect()){
      println(f)
    }

    a.map(_.split(" "))
      .flatMap(x => x.drop(1).map(y => {
        val set = scala.collection.mutable.Set[String]()
        set+=x(0)
        (y, set)
      }))
      .reduceByKey(_++_)
      .foreach(println)
  }
}
