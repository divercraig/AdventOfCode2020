package util

fun printMapOfPoints(points: Map<Point2D, Char>, emptyPad: String = " ") {
    var maxX=0
    var maxY=0

    for(point in points.keys) {
        if(point.x > maxX) {
            maxX = point.x
        }

        if(point.y > maxY) {
            maxY = point.y
        }
    }
    for(y in 0..maxY) {
        var string = ""
        for(x in 0..maxX) {
            val point = Point2D(x,y)
            if(points.keys.contains(point)) {
                string += points[point]
            } else {
                string += emptyPad
            }
        }
        if(string.isNotEmpty() || emptyPad == " ") {
            println(string)
        }
    }
}