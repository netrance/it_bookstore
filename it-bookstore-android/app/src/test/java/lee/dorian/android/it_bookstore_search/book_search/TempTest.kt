package lee.dorian.android.it_bookstore_search.book_search

import org.junit.Assert
import org.junit.Test

data class Member(
    private val id: String,
    private val name: String,
    private val point: Int
)

class TempTest {

    @Test
    fun testPerson() {
        val john = Member("smartjohn", "John", 10000)

        Member::class.java.declaredFields.first { field ->
            field.name == "id"
        }.apply {
            isAccessible = true

            val id = get(john)
            Assert.assertEquals("smartjohn", id)
        }
    }
}