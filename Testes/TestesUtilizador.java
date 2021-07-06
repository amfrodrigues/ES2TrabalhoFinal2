import com.es2.trabalhofinal.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TestesUtilizador {
    IUtilizador utilizador;
    IEmprestimoLivro emprestimo1;

    @BeforeAll
    static void setupBeforeAll() {
    }

    @AfterAll
    static void tearDownAfterAll() {
    }

    @BeforeEach
    void setupAfterEach() {
        try {
            utilizador = new Utilizador("nome", "password");
        } catch (ExceptionUtilizadorNomeNulo | ExceptionUtilizadorPasswordNula exceptionUtilizadorNomeNulo) {
            exceptionUtilizadorNomeNulo.printStackTrace();
        }
        try {
            emprestimo1 = new EmprestimoLivro(0, 1, 2);
        } catch (ExceptionIndiceLivroEmprestadoInvalido | ExceptionIndiceRepositorioLivroInvalido | ExceptionEmprestimoLivroDuraçaoInvalida exceptionIndiceLivroEmprestadoInvalido) {
            exceptionIndiceLivroEmprestadoInvalido.printStackTrace();
        }
    }

    @AfterEach
    void tearDownAfterEach() {
        utilizador = null;
    }

    @Test
    void testUtilizadorNomeNulo() {
        assertThrows(ExceptionUtilizadorNomeNulo.class, () -> new Utilizador(null, "valido"));
    }

    @Test
    void testUtilizadorPasswordNula() {
        assertThrows(ExceptionUtilizadorPasswordNula.class, () -> new Utilizador("valido", null));
    }

    //test white box testa-se condiçao se nome corresponde e se password corresponde e o utilizador esta ativo
    @Test
    void testUtilizadorCheckLogin() {
        assertTrue(utilizador.checkLogin("nome", "password"));
        assertFalse(utilizador.checkLogin("invalido", "password"));
        assertFalse(utilizador.checkLogin("nome", "invalido"));
        utilizador.desativarUtilizador();
        assertFalse(utilizador.checkLogin("nome", "password"));
    }

    @Test
    void testUtilizadorAdicionarEmprestimoLivroComEmprestimoNulo() {
        assertThrows(ExceptionEmprestimoLivroNulo.class, () -> utilizador.adicionarEmprestimoLivro(null));
    }

    @Test
    void testUtilizadorAdicionarEmprestimoLivroComEmprestimoJaExistente() throws ExceptionUtilizadorLivroJaEmprestado, ExceptionEmprestimoLivroNulo {
        utilizador.adicionarEmprestimoLivro(emprestimo1);
        assertThrows(ExceptionUtilizadorLivroJaEmprestado.class, () -> utilizador.adicionarEmprestimoLivro(emprestimo1));
    }

    @Test
    void testUtilizadorGetLivroEmprestimoComListaEmprestimoVazia() {
        assertThrows(ExceptionListaLivrosEmprestadosVazia.class, () -> utilizador.getLivroEmprestimo(0));
    }

    //valores limite
    @Test
    void testUtilizadorGetLivroEmprestimoComIndiceLivroInvalido() throws ExceptionUtilizadorLivroJaEmprestado, ExceptionEmprestimoLivroNulo {
        utilizador.adicionarEmprestimoLivro(emprestimo1);
        assertThrows(ExceptionUtilizadorIndexEmprestimoLivroInvalido.class, () -> utilizador.getLivroEmprestimo(1));
        assertThrows(ExceptionUtilizadorIndexEmprestimoLivroInvalido.class, () -> utilizador.getLivroEmprestimo(-1));
    }

    @Test
    void testUtilizadorAdicionarEmprestimoEGetEmprestimoValido() throws ExceptionUtilizadorLivroJaEmprestado, ExceptionEmprestimoLivroNulo, ExceptionListaLivrosEmprestadosVazia, ExceptionUtilizadorIndexEmprestimoLivroInvalido {
        utilizador.adicionarEmprestimoLivro(emprestimo1);
        assertEquals(emprestimo1, utilizador.getLivroEmprestimo(0));
    }
}
