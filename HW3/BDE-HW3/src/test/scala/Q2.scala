import org.apache.spark.sql.SparkSession

object Q2 {
  def main(args:Array[String]): Unit ={

    val spark = SparkSession.builder
      .appName("SparkSessionExample")
      .master("local[*]")
      .getOrCreate
    val initDF = spark.read.format("csv").option("header", "true").load("/media/mohammadreza/ForWatch/data/facebook-recruiting-iii-keyword-extraction/Train.csv")
    val RDD=initDF.toDF("id","title","body","tags").rdd
    val tf=RDD.map(x => ((x(0),x(1)),1)).reduceByKey(_+_)
    val DF=RDD.map(x => {
      val set = scala.collection.mutable.Set[Any]()
      set += x(0)
      (x(0),set)
    }).reduceByKey(_++_).map(p=>(p._1,p._2.size))
    val idf = DF.map(x => (x._1,Math.log((DF.count() + 1) / (x._2 + 1))))
    tf
      .map(x => (x._1._2, (x._1._1, x._2)))
      .join(idf)
      .map(x => ((x._1, x._2._1._1), x._2._2 * x._2._1._2))
      .filter(x => x._1._2 != null)
      .filter(x => {x._1._2.toString.length >= 3})
      .sortBy(-_._2)
      .take(100)
      .foreach(println)

  }
}
