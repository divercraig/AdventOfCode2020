package day23

class Cup(val label: Int): Comparable<Cup> {
    lateinit var next: Cup

    override fun compareTo(other: Cup): Int {
        return label.compareTo(other.label)
    }
}