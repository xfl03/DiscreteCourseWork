/**
 * 邻接矩阵
 * 1 2 0 0
 * 0 0 1 0
 * 1 0 0 1
 * 0 0 1 0
 */
val matrix = arrayOf(
        "1200",
        "0010",
        "1001",
        "0010"
)

/**
 * 矩阵乘法
 */
fun multiply(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> {
    val arr = Array(a.size) { IntArray(b[0].size) }
    for (i in a.indices) {
        for (j in 0 until b[0].size) {
            for (k in 0 until a[0].size) {
                arr[i][j] += a[i][k] * b[k][j]
            }
        }
    }
    return arr
}

/**
 * 输出矩阵
 */
fun printMatrix(a: Array<IntArray>, mode: Int = 0) {
    val n = a.size
    for (i in 0 until n) {
        for (j in 0 until n) {
            print(if (mode == 0) "${a[i][j]} " else "${if (a[i][j] != 0) 1 else 0} ")
        }
        println()
    }
}

/**
 * 矩阵内元素和
 */
fun sumMatrix(a: Array<IntArray>): Int {
    val n = a.size
    var t = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            t += a[i][j]
        }
    }
    return t
}

/**
 * 矩阵加法
 */
fun add(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> {
    val n = a.size
    val arr = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            arr[i][j] = a[i][j] + b[i][j]
        }
    }
    return arr
}

/**
 * 主函数
 */
fun main(args: Array<String>) {
    val n = matrix.size
    //创建二维数组存储矩阵
    val arr = Array(n) { IntArray(n) }
    //初始化矩阵，将文本形式的矩阵转换成数组形式
    (0 until n).forEach { i ->
        var t = matrix[i].toInt()
        (0 until n).forEach {
            arr[i][n - 1 - it] = t % 10
            t /= 10
        }
    }
    //输出邻接矩阵
    println("邻接矩阵")
    printMatrix(arr)
    println()

    //计算并存储矩阵的n次方
    val arr2 = multiply(arr, arr)
    val arr3 = multiply(arr, arr2)
    val arr4 = multiply(arr, arr3)
    println("邻接矩阵4次方")
    printMatrix(arr4)
    println("长度为4的通路")
    println(sumMatrix(arr4))
    println()

    //合并矩阵
    val arrs = add(arr, add(arr2, arr3))
    println("可达矩阵")
    printMatrix(arrs, 1)
}