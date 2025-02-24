import org.example.Calculator
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

public class CalculatorTest {

    private lateinit var calculator: Calculator

    private val standardOut = System.out

    private val outputStreamCaptor = ByteArrayOutputStream()


    // Redirect system output to capture stream for possible output verification
    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
        calculator = Calculator()
    }

    // Restore original system output
    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
        outputStreamCaptor.reset()
    }

    @Nested
    @DisplayName("Usando la calculadora")
    inner class CalculatorOperationsTests {

        @Test
        @DisplayName("Deberia sumar dos numeros correctamente")
        fun `should add two numbers correctly`() {
            assertEquals(10.0, calculator.add(3.0, 7.0))
        }

        @Test
        @DisplayName("Deberia restar dos numeros correctamente")
        fun `should subtract two numbers correctly`() {
            assertEquals(20.0, calculator.subtract(50.0, 30.0))
        }

        @Test
        @DisplayName("Deberia multiplicar dos numeros correctamente")
        fun `should multiply two numbers correctly`() {
            assertEquals(48.0, calculator.multiply(6.0, 8.0))
        }

        @Test
        @DisplayName("Deberia dividir dos numeros correctamente")
        fun `should divide two numbers correctly`() {
            assertEquals(7.0, calculator.divide(14.0, 2.0))
        }

        @Test
        @DisplayName("Deberia tirar una excepcion cuando es dividido por 0")
        fun `should throw exception on division by zero`() {
            val exception = assertThrows<IllegalArgumentException> {
                calculator.divide(19.0, 0.0)
            }
            assertEquals("No se puede dividir por 0", exception.message)
        }

    }
}
