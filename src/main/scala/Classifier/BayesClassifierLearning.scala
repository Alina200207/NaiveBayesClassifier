package Classifier

import Classifier.Utils.ExpandString

class BayesClassifierLearning {

  private val classifiedDocuments = ParseCSVFile.getClassifiedDocuments

  private def model =  {
    //val docsInClassCount: Map[String, Int] = classifiedDocuments.groupBy(_.documentClass).map{case (documentClass: String, documents: Array[DocumentInfo]) => documentClass -> documents.length}
    val docsInClassCount: Map[String, Int] = classifiedDocuments.groupMapReduce(_.documentClass)(_ => 1)(_ + _)
    val uniqueWordsCount: Int = classifiedDocuments.flatMap(_.document.words).toSet.size
    val sumCountWordsInClass: Map[String, Int] = classifiedDocuments.groupMapReduce(_.documentClass)(_.document.words.length)(_ + _)
    val wordInClassCount: Map[String, Map[String, Int]] = classifiedDocuments.groupMap(_.documentClass)(_.document.words).map(pair => pair._1 -> pair._2.flatten.groupMapReduce(identity)(_ => 1)(_ + _))
    new BayesModel(wordInClassCount, uniqueWordsCount, docsInClassCount, sumCountWordsInClass)
  }

  def classifier: BayesClassifier = {
    new BayesClassifier(model)
  }

}
