package day6

class CustomsForm(groupAnswers: List<String>) {
    private val questionsAnsweredYes = mutableSetOf<Char>()
    val numberOfDifferedYesAnswers: Int
        get() = this.questionsAnsweredYes.size

    init {
        for(answers in groupAnswers) {
            for(answer in answers) {
                questionsAnsweredYes.add(answer)
            }
        }
    }
}