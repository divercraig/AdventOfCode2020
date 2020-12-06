package day6

class CustomsForm(groupAnswers: List<String>) {
    private val questionsAnsweredYes = mutableSetOf<Char>()
    private var questionsEveryoneSaidYesTo = setOf<Char>()

    val numberOfDifferedYesAnswers: Int
        get() = this.questionsAnsweredYes.size

    val numberOfQuestionsEveryoneSaidYesTo: Int
        get() = this.questionsEveryoneSaidYesTo.size

    init {
        var completedFirstPerson = false
        for(answers in groupAnswers) {
            val currentAnswerSet = mutableSetOf<Char>()

            for(answer in answers) {
                questionsAnsweredYes.add(answer)
                currentAnswerSet.add(answer)
            }

            if(!completedFirstPerson) {
                questionsEveryoneSaidYesTo = currentAnswerSet
                completedFirstPerson = true
            } else {
                questionsEveryoneSaidYesTo = questionsEveryoneSaidYesTo.intersect(currentAnswerSet)
            }
        }
    }
}