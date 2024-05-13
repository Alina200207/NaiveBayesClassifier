package Classifier

object Utils {

  implicit class ExpandString(val str: String) extends AnyVal {
    def words: Array[String] = str.split("\\w|[А-Яа-я]+")
  }
}
