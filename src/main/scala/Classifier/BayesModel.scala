package Classifier

import scala.collection.immutable.HashMap

class BayesModel(wordInClassCount: Map[String, Map[String, Int]],
                 uniqueWordsCount: Int,
                 docsInClassCount: Map[String, Int],
                 sumCountWordsInClass: Map[String, Int]
                ) {
  def wordLogarithmicProbability(cl: String, word: String): Double = {
    math.log((wordInClassCount(cl).getOrElse(word, 0) + 1) / (uniqueWordsCount + sumCountWordsInClass(cl)))
  }

  def classLogarithmicProbability(cl: String): Double = {
    math.log(docsInClassCount(cl) / docsInClassCount.values.sum)
  }

  def getClasses: Set[String] = sumCountWordsInClass.keySet
}
