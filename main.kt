data class PhanSo(var tuSo: Int, var mauSo: Int){
    init {
        if (mauSo < 0) {
            tuSo = -tuSo
            mauSo = -mauSo
        }
    }

    fun inPhanSo(){
        println("$tuSo / $mauSo")
    }

    fun toiGianPhanSo(){
        val ucln = ucln(tuSo, mauSo)
        tuSo /= ucln
        mauSo /= ucln
        if (mauSo < 0) {
            tuSo = -tuSo
            mauSo = -mauSo
        }
    }

    fun soSanh(a: PhanSo) : Int{
        val compare = this.tuSo * a.mauSo - a.tuSo * this.mauSo
        return when {
            compare > 0 -> 1
            compare < 0 -> -1
            else -> 0
        }
    }

    fun cong(a: PhanSo) : PhanSo{
        var bcnn = bcnn(this.mauSo, a.mauSo)
        var tu1 = bcnn / this.mauSo * this.tuSo
        var tu2 = bcnn / a.mauSo * a.tuSo
        return PhanSo(tu1 + tu2, bcnn)
    }
}

fun ucln(so1: Int, so2: Int) : Int{
    var a = so1
    var b = so2
    while (a != b){
        if ( a > b) a -= b
        else b -= a
    }
    return a
}

fun bcnn(so1: Int, so2: Int) : Int{
    var a = so1
    var b = so2
    return a * b / ucln(a, b)
}
fun main() {
    println("Nhập số lượng phân số: ")
    val n = readln().toInt()
    var mangPhanSo = Array(n) { i ->
        println("Nhập phân số thứ ${i+1}")
        println("Nhập tử số: ")
        var tuSo = readln().toInt()
        while (tuSo == 0){
            println("Nhập lại mẫu số: ")
            tuSo = readln().toInt()
        }
        println("Nhập mẫu số: ")
        var mauSo = readln().toInt()
        while (mauSo == 0){
            println("Nhập lại mẫu số: ")
            mauSo = readln().toInt()
        }
        PhanSo(tuSo, mauSo)
    }

    println("In mảng phân số: ")
    mangPhanSo.forEach { it.inPhanSo() }

    println("In mảng phân số sau khi tối giản: ")
    mangPhanSo.forEach { it.toiGianPhanSo() }
    mangPhanSo.forEach { it.inPhanSo() }

    var tong = PhanSo(0 ,1)
    mangPhanSo.forEach { tong = tong.cong(it) }
    println("Tổng các phân số: ")
    tong.inPhanSo()

    var mangSapXep = mangPhanSo.sortedWith { ps1, ps2 -> ps2.soSanh(ps1) }
    println("Phân số lớn nhất: ")
    mangSapXep[0].inPhanSo()

    println("In mảng phân số đã sắp xếp giảm dần: ")
    mangSapXep.forEach { it.inPhanSo() }
}