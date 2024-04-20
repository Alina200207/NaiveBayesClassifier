package Classifier

import scala.collection.immutable.HashMap


object tryParse extends App {
  private val separator = ";"
  val examples: HashMap[String, String] = HashMap[String, String]()
  val path: os.Path = os.pwd / "examples.csv"
  println(path)
  val content = os.read.lines.stream(arg = path, charSet = "UTF-8")
  for (line <- content)
    println(line)


  def parseCSVLine(line: String): (String, String) = {
    if (line.isEmpty)
      ("", "")
    else {
      ("", "")
    }
  }
}
