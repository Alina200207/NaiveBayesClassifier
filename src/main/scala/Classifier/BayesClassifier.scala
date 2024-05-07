package Classifier

class BayesClassifier(model: BayesModel) {
  private def tokenize(text: String): Array[String] = text.split(' ')

  private def calculateProbability(cl: String, text: String): Double = model.classLogarithmicProbability(cl) + tokenize(text).map(model.wordLogarithmicProbability(cl, _)).sum

  def classify(text: String): (String, Double) = {
    val classLogarithmicProbability = model.getClasses.toSeq.map(cl => (cl, calculateProbability(cl, text)))
    val sumLogarithmicProbability = classLogarithmicProbability.map(pair => math.exp(pair._2)).sum
    val (classification, logarithmicProbability) = classLogarithmicProbability.maxBy(_._2)
    val probability = math.exp(logarithmicProbability) / sumLogarithmicProbability
    (classification, probability)
  }
}
