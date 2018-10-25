import java.util.*

/**
 * 存储边信息的数据类
 */
data class Edg(
        var v1: Int,//边的顶点1
        var v2: Int //边的顶点2
)

/**
 * 存储顶点的颜色
 *  0 - 未上色
 *  1 - 红色
 * -1 - 黑色
 */
var vsc = IntArray(100) { 0 }
var vs = 0//存储顶点的数量
var es = 0//存储边的数量
var ess = ArrayList<Edg>()//存储边的信息

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    print("请输入顶点数：")
    vs = sc.nextInt()//读入顶点个数
    print("请输入边数：")
    es = sc.nextInt()//读入边个数
    println("请分别输入每条边：")
    for (i in 0 until es) {
        ess.add(Edg(sc.nextInt(), sc.nextInt()))//分别读入每条边的信息
    }

    var flag = true
    for (i in 1..vs) {
        if (vsc[i] == 0 && !bfs(i)) {//遍历每个连通分支，分别使用广度优先搜索来进行染色并获取染色结果
            flag = false
            break
        }
    }

    println(if (flag) "是二部图" else "不是二部图")
}

/**
 * 使用广度优先搜索来进行染色
 * 染色成功则返回true，失败返回false
 */
fun bfs(now: Int): Boolean {
    vsc[now] = 1//先将第一个节点染色为红色
    val q = LinkedList<Int>()//队列，用于存储染色的顺序
    q.push(now)//将第一个节点放入队尾

    while (q.isNotEmpty()) {//循环，直到清空队列
        val from = q.pop()//取出队首
        ess.forEach {
            //遍历所有的边
            if (it.v2 == from) {//如果在v2则交换v1、v2位置（为了方便下面算法）
                val t = it.v1;it.v1 = it.v2;it.v2 = t
            }
            if (it.v1 == from) {
                if (vsc[it.v2] == 0) {//如果另一个顶点还没有染色
                    q.push(it.v2)//将另一个顶点放入队尾
                    vsc[it.v2] = -vsc[from]//给另一个顶点染上不同的颜色
                }else if(vsc[it.v2] == vsc[from]){//如果另一个节点已经被染色且颜色相同
                    return false//颜色相同，不是二分图
                }
            }
        }
    }
    return true
}