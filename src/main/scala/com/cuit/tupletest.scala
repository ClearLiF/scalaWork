package com.cuit

import scala.util.matching.UnanchoredRegex

object tupletest {
   def main(args: Array[String]) {
    val tuple = ("BigData",2015,45.0)
     println(tuple._1)
     println(tuple._2)
     val strings = List("bingin", "dwqdqw")
     println(strings.head)
     var lisy ="strings"::strings
     var maps:Map[String,String] = Map()
     maps+=("3123"->"12313")
     for (elem <- maps) {
       println(elem._1)
       println(elem._2)
     }
     var s="test"
     var s2="test 123456"
     var s3=" 123456test1"
     val pattern = "?"+s+".*"
     //var part = pattern.r
     val str = "Scala is Scalable and cool"

     println(s2.matches(s+".*") )
     println(s3.matches(".*"+s) )

  }
}
