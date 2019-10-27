import com.nhaarman.expect.expect
import org.junit.jupiter.api.*
import java.io.File

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RomanTest {
    private val romanStrings = ArrayList<String>()

    // Read all valid roman strings from a file
    @BeforeAll
    fun readRomanFile() {
        File("conf/roman.txt").forEachLine {
            val list = it.split(' ','\t')
            romanStrings += list
        }
    }

    // Start testing with valid roman numbers
    @Test
    fun `valid roman strings`() {
        for (str in romanStrings) {
            expect(isRomanNumber(str)).toBe(true) {
                " ${str} is not a roman number "
            }
        }
    }

    // Now we want to cover as much as possible none valid numbers
    @Test
    fun `general`() {
        expect(isRomanNumber("")).toBe(false)
        expect(isRomanNumber("ABCD")).toBe(false)
        expect(isRomanNumber("I I I")).toBe(false)
    }

    @Test
    fun `can not have more then 3 in a row`() {
        expect(isRomanNumber("IIII")).toBe(false)
        expect(isRomanNumber("VIIII")).toBe(false)
        expect(isRomanNumber("XXXX")).toBe(false)
        expect(isRomanNumber("LXXXX")).toBe(false)
        expect(isRomanNumber("CCCC")).toBe(false)
    }

    @TestFactory
    fun `can not have more then one V or L`() =
        listOf(
                "IVV" to false,
                "VIV" to false,
                "VVI" to false,
                "XVV" to false,
                "LL" to false,
                "LIXL" to false,
                "LIL" to false
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("$input is expected to be not a valid roman number") {
                expect(isRomanNumber(input)).toBe(expected)
            }
        }

    @TestFactory
    fun `lesser number will follow bigger number`() =
        listOf(
                "VX" to false,
                "IL" to false,
                "VL" to false,
                "IC" to false,
                "VC" to false,
                "LC" to false
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("$input is expected to be not a valid roman number") {
                expect(isRomanNumber(input)).toBe(expected)
            }
        }

    @TestFactory
    fun `Only a single lesser numeral, X or I, can precede a bigger numeral`() =
            listOf(
                    "IIV" to false,
                    "XIIX" to false,
                    "LIIV" to false,
                    "CIIV" to false,
                    "IIX" to false,
                    "IXIX" to false,
                    "XIIX" to false,
                    "LIIX" to false,
                    "CIIX" to false,
                    "XXL" to false,
                    "LXXL" to false,
                    "CXXL" to false,
                    "XXC" to false,
                    "LXXC" to false,
                    "CXXC" to false
            ).map { (input, expected) ->
                DynamicTest.dynamicTest("$input is expected to be not a valid roman number") {
                    expect(isRomanNumber(input)).toBe(expected)
                }
            }

    @TestFactory
    fun `non valid greater then 100`()  = listOf(
            "CLC" to false,
            "CLVX" to false
    ).map { (input, expected) -> DynamicTest.dynamicTest("$input is not a valid roman number") {
        expect(isRomanNumber(input)).toBe(expected)
    } }

}