import com.es2.trabalhofinal.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestesEmprestimoLivro {
    IEmprestimoLivro emprestimoLivro;

    @BeforeAll
    static void setupBeforeAll() {
    }

    @AfterAll
    static void tearDownAfterAll() {
    }

    @BeforeEach
    void setupAfterEach() {
        try {
            emprestimoLivro = new EmprestimoLivro(1, 1, 2);
        } catch (ExceptionIndiceLivroEmprestadoInvalido | ExceptionIndiceRepositorioLivroInvalido | ExceptionEmprestimoLivroDuraçaoInvalida exceptionIndiceLivroEmprestadoInvalido) {
            exceptionIndiceLivroEmprestadoInvalido.printStackTrace();
        }
    }

    @AfterEach
    void tearDownAfterEach() {
        emprestimoLivro = null;
    }

    @Test
    void testEmprestimoLivroIndexLivroInvalido() {
        assertThrows(ExceptionIndiceLivroEmprestadoInvalido.class, () -> new EmprestimoLivro(-1, 1, 1));
    }

    @Test
    void testEmprestimoLivroIndexRepositorioInvalido() {
        assertThrows(ExceptionIndiceRepositorioLivroInvalido.class, () -> new EmprestimoLivro(1, -1, 1));
    }

    @Test
    void testEmprestimoLivroDuracaoInvalido() {
        assertThrows(ExceptionEmprestimoLivroDuraçaoInvalida.class, () -> new EmprestimoLivro(1, 1, 0));
    }

    @Test
    void testEmprestimoLivroValido() throws ExceptionIndiceLivroEmprestadoInvalido, ExceptionIndiceRepositorioLivroInvalido, ExceptionEmprestimoLivroDuraçaoInvalida {
        IEmprestimoLivro emprestimoLivro = new EmprestimoLivro(0, 0, 1);
        assertEquals(0, emprestimoLivro.getIndexLivroEmprestimo());
        assertEquals(0, emprestimoLivro.getIndexRepositorioEmprestimo());
        assertEquals(LocalDate.now().minusDays(4), emprestimoLivro.getDataInicialEmprestimo());
    }

    @Test
    void testEmprestimoLivroLimiteExtenderEmprestimo3x() throws ExceptionEmprestimoLivroLimiteExtenderExcedido {
        emprestimoLivro.extenderEmprestimo();
        emprestimoLivro.extenderEmprestimo();
        assertThrows(ExceptionEmprestimoLivroLimiteExtenderExcedido.class, () -> emprestimoLivro.extenderEmprestimo());
    }

    @Test
    void testEmprestimoLivroDataEmprestimoExcedida() {
        assertThrows(ExceptionEmprestimoLivroDataEmprestimoExcedida.class, () -> emprestimoLivro.checkEmprestimoValido());
    }

    @Test
    void testEmprestimoLivroExtenderDataEmprestimo() throws ExceptionEmprestimoLivroLimiteExtenderExcedido, ExceptionEmprestimoLivroDataEmprestimoExcedida {
        assertThrows(ExceptionEmprestimoLivroDataEmprestimoExcedida.class, () -> emprestimoLivro.checkEmprestimoValido());
        emprestimoLivro.extenderEmprestimo();
        assertTrue(emprestimoLivro.checkEmprestimoValido());
    }
}
