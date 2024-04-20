package Classifier

import scala.collection.immutable.HashMap

class BayesModel(wordInClassCount: HashMap[String, HashMap[String, Int]],
                 uniqueWordsCount: Int,
                 docsInClassCount: HashMap[String, Int],
                 sumCountWordsInClass: HashMap[String, Int]
                ) {
  def wordLogarithmicProbability(cl: String, word: String): Double = {
    math.log((wordInClassCount(cl).getOrElse(word, 0) + 1) / (uniqueWordsCount + sumCountWordsInClass(cl)))
  }

  def classLogarithmicProbability(cl: String): Double = {
    math.log(docsInClassCount(cl) / docsInClassCount.values.sum)
  }

  def getClasses(): Set[String] = sumCountWordsInClass.keySet
}
