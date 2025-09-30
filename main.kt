class PhanSo(var tu: Int, var mau: Int) {
    // Nhập phân số
    fun nhap() {
        do {
            print("Nhập tử số: ")
            tu = readln().toInt()
            print("Nhập mẫu số: ")
            mau = readln().toInt()
            if (mau == 0) {
                println("Mẫu số không được bằng 0, nhập lại!")
            }
        } while (mau == 0)
    }
    // Hiển thi phân số
    fun xuat() {
        println("$tu/$mau")
    }
    // Tối giản phân số
    fun toiGian() {
        val gcd = ucln(Math.abs(tu), Math.abs(mau))
        tu /= gcd
        mau /= gcd
        // Đưa mẫu số về dương
        if (mau < 0) {
            tu = -tu
            mau = -mau
        }
    }

    // So sánh phân số
    fun soSanh(ps: PhanSo): Int {
        val a = tu * ps.mau
        val b = ps.tu * mau
        return when {
            a < b -> -1
            a == b -> 0
            else -> 1
        }
    }
    // Tính tổng với một phân số khác
    fun cong(ps: PhanSo): PhanSo {
        val tuMoi = tu * ps.mau + ps.tu * mau
        val mauMoi = mau * ps.mau
        val kq = PhanSo(tuMoi, mauMoi)
        kq.toiGian()
        return kq
    }
    // Hàm tính ước chung lớn nhất
    private fun ucln(a: Int, b: Int): Int {
        return if (b == 0) a else ucln(b, a % b)
    }
}
fun main() {
    print("Nhập số lượng phân số: ")
    val n = readln().toInt()
    val arr = Array(n) { PhanSo(1, 1) }
    // Nhập mảng phân số
    for (i in arr.indices) {
        println("Nhập phân số thứ ${i + 1}:")
        arr[i].nhap()
    }
    println("\nMảng phân số vừa nhập:")
    arr.forEach { it.xuat() }
    println("\nMảng phân số sau khi tối giản:")
    arr.forEach { it.toiGian(); it.xuat() }
    // Tính tổng
    var tong = PhanSo(0, 1)
    for (ps in arr) {
        tong = tong.cong(ps)
    }
    println("\nTổng các phân số:")
    tong.xuat()
    // Tìm phan so lớn nhất
    var max = arr[0]
    for (ps in arr) {
        if (ps.soSanh(max) == 1) {
            max = ps
        }
    }
    print("\nPhân số lớn nhất: ")
    max.xuat()
    // Sắp xếp theo thu tu giảm dần
    arr.sortWith { a, b -> b.soSanh(a) }
    println("\nMảng phân số sau khi sắp xếp giảm dần:")
    arr.forEach { it.xuat() }
}
