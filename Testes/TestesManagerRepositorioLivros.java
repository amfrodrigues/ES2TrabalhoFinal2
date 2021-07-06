import com.es2.trabalhofinal.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestesManagerRepositorioLivros {
    IManagerRepositorioLivros managerRepositorioLivros;
    IRepositorioLivros repositorio;


    @BeforeAll
    static void setupBeforeAll() {
    }

    @AfterAll
    static void tearDownAfterAll() {
    }

    @BeforeEach
    void setupAfterEach() {
        managerRepositorioLivros = new ManagerRepositorioLivros();
        repositorio = new RepositorioLivros();
    }

    @AfterEach
    void tearDownAfterEach() {
        managerRepositorioLivros = null;
        repositorio = null;
    }

    @Test
    void testManagerRepositorioLivrosComListaRepositorioLivrosNula() {
        assertThrows(ExceptionManagerRepositorioLivrosListaRepositoriosNula.class, () -> new ManagerRepositorioLivros(null));
    }


    @Test
    void testManagerRepositorioLivrosAdicionarLivroComListaRepositorioLivrosVazia() {
        assertThrows(ExceptionListaRepositoriosLivrosVazia.class, () -> managerRepositorioLivros.addLivro(null));
    }

    @Test
    void testManagerRepositorioLivrosGetivroPorIndiceComListaRepositorioLivrosVazia() {
        assertThrows(ExceptionListaRepositoriosLivrosVazia.class, () -> managerRepositorioLivros.getLivroPorIndice(0, 0));
    }

    @Test
    void testManagerRepositorioLivrosGetLivroComIndiceRepositorioLivrosInvalido() throws ExceptionRepositorioLivroNulo, ExceptionManagerRepositoriosLivrosComRepositorioJaExiste {
        managerRepositorioLivros.addRepositorio(repositorio);
        assertThrows(ExceptionIndiceRepositorioLivroInvalido.class, () -> managerRepositorioLivros.getLivroPorIndice(-1, 0));
        assertThrows(ExceptionIndiceRepositorioLivroInvalido.class, () -> managerRepositorioLivros.getLivroPorIndice(1, 0));
    }

    @Test
    void testManagerRepositorioLivrosGetIndiceLivroComListaRepositorioLivrosVazia() {
        assertThrows(ExceptionListaRepositoriosLivrosVazia.class, () -> managerRepositorioLivros.getIndiceLivro(0, "titulo"));
    }

    @Test
    void testManagerRepositorioLivrosGetIndiceLivroComIndiceRepositorioLivrosInvalido() throws ExceptionRepositorioLivroNulo, ExceptionManagerRepositoriosLivrosComRepositorioJaExiste {
        managerRepositorioLivros.addRepositorio(repositorio);
        assertThrows(ExceptionIndiceRepositorioLivroInvalido.class, () -> managerRepositorioLivros.getIndiceLivro(-1, "titulo"));
        assertThrows(ExceptionIndiceRepositorioLivroInvalido.class, () -> managerRepositorioLivros.getIndiceLivro(1, "titulo"));
    }

    @Test
    void testManagerRepositorioLivrosGetTodosLivrosComListaRepositorioLivrosVazia() {
        assertThrows(ExceptionListaRepositoriosLivrosVazia.class, () -> managerRepositorioLivros.getListaTodosLivros(0));
    }

    @Test
    void testManagerRepositorioLivrosGetTodosLivrosComIndiceRepositorioLivrosInvalido() throws ExceptionRepositorioLivroNulo, ExceptionManagerRepositoriosLivrosComRepositorioJaExiste {
        managerRepositorioLivros.addRepositorio(repositorio);
        assertThrows(ExceptionIndiceRepositorioLivroInvalido.class, () -> managerRepositorioLivros.getListaTodosLivros(-1));
        assertThrows(ExceptionIndiceRepositorioLivroInvalido.class, () -> managerRepositorioLivros.getListaTodosLivros(1));
    }

    @Test
    void testManagerRepositorioLivrosAdicionarRepositorioComRepositorioNull() {
        assertThrows(ExceptionRepositorioLivroNulo.class, () -> managerRepositorioLivros.addRepositorio(null));
    }

    @Test
    void testManagerRepositorioLivrosAdicionarRepositorioComRepositorioJaExistente() throws ExceptionRepositorioLivroNulo, ExceptionManagerRepositoriosLivrosComRepositorioJaExiste {
        managerRepositorioLivros.addRepositorio(repositorio);
        assertThrows(ExceptionManagerRepositoriosLivrosComRepositorioJaExiste.class, () -> managerRepositorioLivros.addRepositorio(repositorio));
    }
}
