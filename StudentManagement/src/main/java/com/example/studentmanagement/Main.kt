package com.example.studentmanagement

// ======================================================
// 1. KHAI BÁO LỚP SINH VIÊN
// ======================================================

data class Student(
    val id: String,
    val fullName: String,
    val age: Int,
    val major: String,
    val gpa: Double
)


// ======================================================
// 2. CÁC HÀM NHẬP DỮ LIỆU
// ======================================================

// Nhập chuỗi, không cho phép bỏ trống
fun inputText(message: String): String {
    while (true) {
        print(message)

        val value = readln().trim()

        if (value.isNotEmpty()) {
            return value
        }

        println("Du lieu khong duoc de trong!")
    }
}


// Nhập tuổi
fun inputAge(): Int {
    while (true) {
        print("Nhap tuoi: ")

        val age = readln().trim().toIntOrNull()

        if (age != null && age in 1..120) {
            return age
        }

        println("Tuoi khong hop le! Vui long nhap lai.")
    }
}


// Nhập GPA
fun inputGpa(): Double {
    while (true) {
        print("Nhap GPA (0 - 10): ")

        val gpa = readln()
            .trim()
            .replace(",", ".")
            .toDoubleOrNull()

        if (gpa != null && gpa in 0.0..10.0) {
            return gpa
        }

        println("GPA phai nam trong khoang tu 0 den 10!")
    }
}


// ======================================================
// 3. HIỂN THỊ SINH VIÊN
// ======================================================

fun displayStudent(student: Student) {
    println(
        "ID: ${student.id} | " +
                "Name: ${student.fullName} | " +
                "Age: ${student.age} | " +
                "Major: ${student.major} | " +
                "GPA: ${"%.2f".format(student.gpa)}"
    )
}


// Hiển thị toàn bộ danh sách
fun displayAllStudents(students: List<Student>) {

    if (students.isEmpty()) {
        println("Danh sach sinh vien dang trong.")
        return
    }

    println()
    println("================ DANH SACH SINH VIEN ================")

    students.forEachIndexed { index, student ->

        print("${index + 1}. ")

        displayStudent(student)
    }

    println("======================================================")
}


// ======================================================
// 4. ADD STUDENT
// ======================================================

fun addStudent(students: MutableList<Student>) {

    println()
    println("=============== THEM SINH VIEN ===============")

    var id: String

    while (true) {

        id = inputText("Nhap Student ID: ")

        val existed = students.any {
            it.id.equals(id, ignoreCase = true)
        }

        if (!existed) {
            break
        }

        println("Student ID da ton tai. Vui long nhap ID khac!")
    }

    val fullName = inputText("Nhap ho va ten: ")

    val age = inputAge()

    val major = inputText("Nhap nganh: ")

    val gpa = inputGpa()

    val newStudent = Student(
        id = id,
        fullName = fullName,
        age = age,
        major = major,
        gpa = gpa
    )

    students.add(newStudent)

    println("Them sinh vien thanh cong!")
}


// ======================================================
// 5. SEARCH STUDENT BY ID
// ======================================================

fun searchStudentById(students: List<Student>) {

    println()
    println("=============== TIM SINH VIEN ===============")

    val id = inputText("Nhap Student ID can tim: ")

    val student = students.firstOrNull {
        it.id.equals(id, ignoreCase = true)
    }

    if (student != null) {

        println("Tim thay sinh vien:")

        displayStudent(student)

    } else {

        println("Khong tim thay sinh vien co ID: $id")
    }
}


// ======================================================
// 6. TÍNH GPA TRUNG BÌNH CỦA TẤT CẢ SINH VIÊN
// ======================================================

fun calculateAverageGpa(students: List<Student>) {

    if (students.isEmpty()) {
        println("Danh sach sinh vien dang trong.")
        return
    }

    val average = students
        .map { it.gpa }
        .average()

    println(
        "GPA trung binh cua tat ca sinh vien: " +
                "${"%.2f".format(average)}"
    )
}


// ======================================================
// 7. TÌM SINH VIÊN GPA CAO NHẤT
// ======================================================

fun findHighestGpa(students: List<Student>) {

    val student = students.maxByOrNull {
        it.gpa
    }

    if (student == null) {

        println("Danh sach sinh vien dang trong.")

    } else {

        println("Sinh vien co GPA cao nhat:")

        displayStudent(student)
    }
}


// ======================================================
// 8. REMOVE STUDENT
// ======================================================

fun removeStudent(students: MutableList<Student>) {

    println()
    println("=============== XOA SINH VIEN ===============")

    val id = inputText("Nhap Student ID can xoa: ")

    val student = students.firstOrNull {
        it.id.equals(id, ignoreCase = true)
    }

    if (student != null) {

        students.remove(student)

        println("Xoa sinh vien thanh cong!")

    } else {

        println("Khong tim thay sinh vien co ID: $id")
    }
}


// ======================================================
// YÊU CẦU 1
// ĐẾM SỐ SINH VIÊN GPA >= 8.0
// ======================================================

fun countStudentsGpaAtLeast8(students: List<Student>) {

    val count = students.count {
        it.gpa >= 8.0
    }

    println("So sinh vien co GPA >= 8.0: $count")
}


// ======================================================
// YÊU CẦU 2
// ĐẾM SỐ SINH VIÊN GPA < 5.0
// ======================================================

fun countStudentsGpaBelow5(students: List<Student>) {

    val count = students.count {
        it.gpa < 5.0
    }

    println("So sinh vien co GPA < 5.0: $count")
}


// ======================================================
// YÊU CẦU 3
// TÍNH GPA TRUNG BÌNH THEO NGÀNH
// ======================================================

fun calculateAverageGpaByMajor(students: List<Student>) {

    println()
    println("========== GPA TRUNG BINH THEO NGANH ==========")

    val major = inputText("Nhap nganh can tinh: ")

    val studentsByMajor = students.filter {
        it.major.equals(major, ignoreCase = true)
    }

    if (studentsByMajor.isEmpty()) {

        println("Khong co sinh vien thuoc nganh: $major")

        return
    }

    val average = studentsByMajor
        .map { it.gpa }
        .average()

    println(
        "GPA trung binh cua nganh $major: " +
                "${"%.2f".format(average)}"
    )
}


// ======================================================
// YÊU CẦU 5
// TÌM SINH VIÊN LỚN TUỔI NHẤT
// ======================================================

fun findOldestStudent(students: List<Student>) {

    val student = students.maxByOrNull {
        it.age
    }

    if (student == null) {

        println("Danh sach sinh vien dang trong.")

    } else {

        println("Sinh vien lon tuoi nhat:")

        displayStudent(student)
    }
}


// ======================================================
// YÊU CẦU 6
// TÌM SINH VIÊN GPA TRONG KHOẢNG 7.0 -> 8.5
// ======================================================

fun findStudentsGpaFrom7To85(students: List<Student>) {

    val result = students.filter {
        it.gpa in 7.0..8.5
    }

    if (result.isEmpty()) {

        println("Khong co sinh vien co GPA tu 7.0 den 8.5.")

    } else {

        println("Sinh vien co GPA tu 7.0 den 8.5:")

        displayAllStudents(result)
    }
}


// ======================================================
// YÊU CẦU 7
// TÌM TẤT CẢ SINH VIÊN THUỘC MỘT NGÀNH
// ======================================================

fun findStudentsByMajor(students: List<Student>) {

    val major = inputText("Nhap nganh can tim: ")

    val result = students.filter {
        it.major.equals(major, ignoreCase = true)
    }

    if (result.isEmpty()) {

        println("Khong co sinh vien thuoc nganh: $major")

    } else {

        println("Danh sach sinh vien thuoc nganh $major:")

        displayAllStudents(result)
    }
}


// ======================================================
// YÊU CẦU 8
// TÌM SINH VIÊN THEO MỘT PHẦN TÊN
// ======================================================

fun searchStudentByPartialName(students: List<Student>) {

    val keyword = inputText("Nhap mot phan ten can tim: ")

    val result = students.filter {
        it.fullName.contains(
            keyword,
            ignoreCase = true
        )
    }

    if (result.isEmpty()) {

        println("Khong tim thay sinh vien co ten chua: $keyword")

    } else {

        println("Ket qua tim kiem:")

        displayAllStudents(result)
    }
}


// ======================================================
// YÊU CẦU 9
// SẮP XẾP GPA GIẢM DẦN
// ======================================================

fun sortStudentsByGpaDescending(students: List<Student>) {

    val result = students.sortedByDescending {
        it.gpa
    }

    println("Danh sach sinh vien sap xep GPA giam dan:")

    displayAllStudents(result)
}


// ======================================================
// YÊU CẦU 10
// HIỂN THỊ TOP 3 GPA CAO NHẤT
// ======================================================

fun displayTop3HighestGpa(students: List<Student>) {

    if (students.isEmpty()) {
        println("Danh sach sinh vien dang trong.")
        return
    }

    val result = students
        .sortedByDescending { it.gpa }
        .take(3)

    println("TOP 3 SINH VIEN CO GPA CAO NHAT:")

    displayAllStudents(result)
}


// ======================================================
// YÊU CẦU 11
// SẮP XẾP SINH VIÊN THEO TUỔI
// ======================================================

fun sortStudentsByAge(students: List<Student>) {

    val result = students.sortedBy {
        it.age
    }

    println("Danh sach sinh vien sap xep theo tuoi tang dan:")

    displayAllStudents(result)
}


// ======================================================
// HÀM LẤY TÊN CUỐI
// VD: Nguyen Van An -> An
// ======================================================

fun getFirstName(fullName: String): String {

    return fullName
        .trim()
        .substringAfterLast(" ")
        .lowercase()
}


// ======================================================
// YÊU CẦU 12
// SẮP XẾP SINH VIÊN THEO TÊN
// ======================================================

fun sortStudentsByName(students: List<Student>) {

    val result = students.sortedWith(
        compareBy<Student> {
            getFirstName(it.fullName)
        }.thenBy {
            it.fullName.lowercase()
        }
    )

    println("Danh sach sinh vien sap xep theo ten A-Z:")

    displayAllStudents(result)
}


// ======================================================
// HIỂN THỊ MENU
// ======================================================

fun displayMenu() {

    println()
    println("======================================================")
    println("============= STUDENT MANAGEMENT =====================")
    println("======================================================")

    println("1.  Add student")
    println("2.  Display all students")
    println("3.  Search student by ID")
    println("4.  Calculate average GPA")
    println("5.  Find student with highest GPA")
    println("6.  Remove student")

    println("------------------------------------------------------")

    println("7.  Count students with GPA >= 8.0")
    println("8.  Count students with GPA < 5.0")
    println("9.  Calculate average GPA by major")
    println("10. Find oldest student")
    println("11. Find students with GPA from 7.0 to 8.5")
    println("12. Find all students by major")
    println("13. Search students by partial name")
    println("14. Sort students by GPA descending")
    println("15. Display top 3 students with highest GPA")
    println("16. Sort students by age")
    println("17. Sort students by name")

    println("------------------------------------------------------")

    println("0. Exit")

    println("======================================================")
}


// ======================================================
// HÀM MAIN
// ======================================================

fun main() {

    val students = mutableListOf(

        Student(
            id = "ST101",
            fullName = "Hoang Minh Duc",
            age = 20,
            major = "Cyber Security",
            gpa = 8.1
        ),

        Student(
            id = "ST102",
            fullName = "Nguyen Gia Linh",
            age = 21,
            major = "Data Science",
            gpa = 7.3
        ),

        Student(
            id = "ST103",
            fullName = "Tran Quoc Bao",
            age = 19,
            major = "Software Engineering",
            gpa = 4.9
        ),

        Student(
            id = "ST104",
            fullName = "Le Thanh Ha",
            age = 22,
            major = "Artificial Intelligence",
            gpa = 9.2
        ),

        Student(
            id = "ST105",
            fullName = "Pham Minh Anh",
            age = 23,
            major = "Information Technology",
            gpa = 6.7
        )
    )



    // ==================================================
    // VÒNG LẶP MENU
    // ==================================================

    while (true) {

        displayMenu()

        print("Choose: ")

        when (readln().trim()) {

            "1" -> {
                addStudent(students)
            }

            "2" -> {
                displayAllStudents(students)
            }

            "3" -> {
                searchStudentById(students)
            }

            "4" -> {
                calculateAverageGpa(students)
            }

            "5" -> {
                findHighestGpa(students)
            }

            "6" -> {
                removeStudent(students)
            }

            "7" -> {
                countStudentsGpaAtLeast8(students)
            }

            "8" -> {
                countStudentsGpaBelow5(students)
            }

            "9" -> {
                calculateAverageGpaByMajor(students)
            }

            "10" -> {
                findOldestStudent(students)
            }

            "11" -> {
                findStudentsGpaFrom7To85(students)
            }

            "12" -> {
                findStudentsByMajor(students)
            }

            "13" -> {
                searchStudentByPartialName(students)
            }

            "14" -> {
                sortStudentsByGpaDescending(students)
            }

            "15" -> {
                displayTop3HighestGpa(students)
            }

            "16" -> {
                sortStudentsByAge(students)
            }

            "17" -> {
                sortStudentsByName(students)
            }

            "0" -> {

                println()
                println("Da thoat chuong trinh.")
                println("Cam on ban da su dung Student Management!")

                break
            }

            else -> {

                println()
                println("Lua chon khong hop le!")
                println("Vui long chon tu 0 den 17.")
            }
        }
    }
}