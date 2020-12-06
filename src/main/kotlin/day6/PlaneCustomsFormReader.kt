package day6

import day5.Seat
import java.io.File

class PlaneCustomsFormReader(fileName: String) {
    private val customForms = mutableListOf<CustomsForm>()
    val sumOfYesAnswers: Int
        get() = customForms.fold(0, {sum, form -> sum + form.numberOfDifferedYesAnswers})

    init {
        var currentGroup = ArrayList<String>()

        File(fileName).forEachLine {
            when(it) {
                "" -> {
                    customForms.add(CustomsForm(currentGroup))
                    currentGroup = ArrayList()
                }
                else -> {
                    currentGroup.add(it)
                }
            }
        }
        customForms.add(CustomsForm(currentGroup))
    }

}