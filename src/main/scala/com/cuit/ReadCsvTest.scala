package com.cuit

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

import scala.collection.mutable.ArrayBuffer

object ReadCsvTest {

  def main(args: Array[String]): Unit = {
    getMovieInfo("Popcorn (2007)")
  }
  def getMovieInfo(movieName: String):String = {
    val spark = SparkSession
      .builder()
     // .config("spark.jars","D:\\IDEAWorkSpace2\\scalaWork\\target\\scalaWork-1.0-SNAPSHOT.jar")
     // .config("spark.jars","D:\\IDEAWorkSpace2\\scalaWork\\target\\scalaWork-1.0-SNAPSHOT.jar")
      .master("spark://master:7077")
     // .master("spark://master:7077")
      .appName("ReadCsvTest3")
     .config("spark.sql.codegen.wholeStage", false)
      .config("spark.network.timeout",120)
      .config("spark.dynamicAllocation.enabled",false)
      .config("spark.driver.host","hostip") //指定driver 的hosts-name
     // .config("spark.driver.extraClassPath","/root/scalaWork-1.0-SNAPSHOT.jar")
      .config("spark.driver.port","9894")//指定driver的服务端口
      .getOrCreate
//    val sc: SparkContext = spark.sparkContext
//    sc.setLogLevel("ERROR")
    val schema = StructType(
      List(
        StructField("movieId", IntegerType, true),
        StructField("title", StringType, true),
        StructField("genres", StringType, true)
      )
    )
    println("init spark")
    val df = spark.read.format("csv")
      .option("header", true)
      .option("delimiter", ",")
      .schema(schema)
      .load("hdfs://master:9000/spark/movies.csv").cache()
    println(s"read text file, df type = ${df.getClass.toString} rdd type = ${df.rdd.getClass.toString}")
    //The Prime Gig (2000)
    println("请输入：")
    //    var movieName = StdIn.readLine()
    val filter_rdd = df.rdd.filter(i => i.getAs[String](1).equals(movieName)||
            i.getAs[String](1).contains(movieName))
    var temp=null
    var list =Set[String]()
    var maps:Map[String,String] = Map()
    var n:Int=0
    val count = filter_rdd.count()
    var unit: Unit = filter_rdd.foreach(i => {

      maps+=(i.get(1).toString
        .replace(","," ")
        .replace("("," ")
        .replace(")"," ")
        .replace("'"," ")
        ->i.get(2).toString
        .replace("'"," ")
        .replace(","," ")
        .replace("("," ")
        .replace(")"," "))
      n=n+1
      if(count==n){
        ScalaJdbcConnectSelect.insert(maps)

      }
      list
    })

    println("这里")


    //    df.createOrReplaceTempView("people")
    //    spark.sql("select * from people where age > 14").show()

    //    spark.stop()
    temp
    return  temp
  }
}
