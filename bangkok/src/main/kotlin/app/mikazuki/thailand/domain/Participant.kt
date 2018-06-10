package app.mikazuki.thailand.domain

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "participants")
data class Participant(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        val partyId: Long,
        val firstName: String,
        val lastName: String,
        val email: String,
        val gender: Int,
        val side: Boolean,
        val attendance: Boolean,
        val postalCode: String?,
        val message: String?,
        val address: String?,
        val phone: String?,
        val allergy: String?,
        val hash: String) {


    fun toCsvModel(): ParticipantForCsv {
        return ParticipantForCsv(
                出欠 = if (attendance) "出席" else "欠席",
                姓 = lastName,
                名 = firstName,
                メールアドレス = email,
                性別 = if (gender == 1) "男性" else "女性",
                招待元 = if (side) "新郎" else "新婦",
                郵便番号 = postalCode,
                住所 = address,
                電話番号 = phone,
                アレルギー = allergy,
                メッセージ = message)
    }

    @JsonPropertyOrder(value = ["出欠", "姓", "名", "メールアドレス", "性別", "招待元", "郵便番号", "住所", "電話番号", "アレルギー", "メッセージ"])
    inner class ParticipantForCsv(
            val 出欠: String,
            val 姓: String,
            val 名: String,
            val メールアドレス: String,
            val 性別: String,
            val 招待元: String,
            val 郵便番号: String?,
            val 住所: String?,
            val 電話番号: String?,
            val アレルギー: String?,
            val メッセージ: String?)
}

