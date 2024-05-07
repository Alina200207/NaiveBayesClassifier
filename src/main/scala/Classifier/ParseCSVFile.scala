package Classifier

import scala.io.Source
import scala.util.Try

object ParseCSVFile {
  //private val regularExpression = """(([\w\d\.\?\!\:\-,А-Яа-яё\(\)]*?(\s?\"[^\"]*?\")?\s?)+?)\s?;\s?([\wА-Яа-я)]+$)""".r
  private val regularExpression = """(.+?);(.+)$""".r

  def getClassifiedDocuments: Array[DocumentInfo] = {
    Try {
      val exampleFile = Source.fromResource("examples.csv")
      val documents = exampleFile.getLines()
      for {
        documentLine <- documents
        regularExpression(document, documentClass) = documentLine
      } yield DocumentInfo(document, documentClass)
    }.fold(_ => {
      println("Ошибка чтения документа для обучения.")
      sys.exit(1)
    }, docs => docs.toArray)
  }
}
