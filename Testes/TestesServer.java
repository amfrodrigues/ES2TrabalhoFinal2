import com.es2.trabalhofinal.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TestesServer {
    IServer server;

    @BeforeAll
    static void setupBeforeAll() {
    }

    @AfterAll
    static void tearDownAfterAll() {
    }

    @BeforeEach
    void setupAfterEach() {
        IManagerRepositorioLivros manager = new ManagerRepositorioLivros();
        try {
            server = new Server(manager);
        } catch (ExceptionManagerRepositorioLivrosNulo exceptionManagerRepositorioLivrosNulo) {
            exceptionManagerRepositorioLivrosNulo.printStackTrace();
        }
    }

    @AfterEach
    void tearDownAfterEach() {
    }

    @Test
    void testServerComManagerRepositorioLivroNulo() {
        assertThrows(ExceptionManagerRepositorioLivrosNulo.class, () -> new Server(null));
    }

    @Test
    void testServerAdicionarUtilizadorComUtilizadorJaExistente() throws ExceptionUtilizadorPasswordNula, ExceptionUtilizadorNomeNulo, ExceptionUtilizadorJaUsado {
        IUtilizador utilizador1 = new Utilizador("nome", "password");
        server.adicionarUtilizador(utilizador1);
        assertThrows(ExceptionUtilizadorJaUsado.class, () -> server.adicionarUtilizador(utilizador1));
    }

    @Test
    void testServerLoginUtilizadorComListaUtilizadoresVazia() {
        assertThrows(ExceptionListaUtilizadoresVazia.class, () -> server.loginUtilizador("nome", "password"));
    }


    @Test
    void testServerCancelarUtilizadorComListaUtilizadoresVazia() {
        assertThrows(ExceptionListaUtilizadoresVazia.class, () -> server.cancelarUtilizador(0));
    }

    @Test
    void testServerCancelarUtilizadorComIndiceInvalido() throws ExceptionUtilizadorPasswordNula, ExceptionUtilizadorNomeNulo, ExceptionUtilizadorJaUsado {
        IUtilizador utilizador1 = new Utilizador("nome", "password");
        server.adicionarUtilizador(utilizador1);
        assertThrows(ExceptionIndexUtilizadorInvalido.class, () -> server.cancelarUtilizador(-1));
        assertThrows(ExceptionIndexUtilizadorInvalido.class, () -> server.cancelarUtilizador(1));
    }

    @Test
    void testServerAdicionarEmprestimoLivroComTermosNaoAceites() {
        assertThrows(ExceptionTermosResponsabilidadeNaoAceites.class, () -> server.adicionarEmprestimoLivro(null, 0, 0, false));
    }

    @Test
    void testServerGetAllEmprestimosUtilizadorComIndiceUtilizarInvalido() throws ExceptionUtilizadorPasswordNula, ExceptionUtilizadorNomeNulo, ExceptionUtilizadorJaUsado {
        IUtilizador utilizador1 = new Utilizador("nome", "password");
        server.adicionarUtilizador(utilizador1);
        assertThrows(ExceptionIndexUtilizadorInvalido.class, () -> server.getAllEmprestimosUtilizador(-1));
        assertThrows(ExceptionIndexUtilizadorInvalido.class, () -> server.getAllEmprestimosUtilizador(1));
    }

    @Test
    void testServerGetLivroEmprestimoUtilizador() throws ExceptionUtilizadorPasswordNula, ExceptionUtilizadorNomeNulo, ExceptionUtilizadorJaUsado {
        IUtilizador utilizador1 = new Utilizador("nome", "password");
        server.adicionarUtilizador(utilizador1);
        assertThrows(ExceptionIndexUtilizadorInvalido.class, () -> server.getLivroEmprestimoUtilizador(-1, 0));
        assertThrows(ExceptionIndexUtilizadorInvalido.class, () -> server.getLivroEmprestimoUtilizador(1, 0));
    }

    @Test
    void testServerExtenderEmprestimoLivroComIndexUtilizadorInvalido() throws ExceptionUtilizadorPasswordNula, ExceptionUtilizadorNomeNulo, ExceptionUtilizadorJaUsado {
        IUtilizador utilizador1 = new Utilizador("nome", "password");
        server.adicionarUtilizador(utilizador1);
        assertThrows(ExceptionIndexUtilizadorInvalido.class, () -> server.extenderEmprestimoLivro(-1, 0));
        assertThrows(ExceptionIndexUtilizadorInvalido.class, () -> server.extenderEmprestimoLivro(1, 0));
    }

    // testes de integraçao global
    @Test
    void testServerAdicionarUtilizadorELoginUtilizadorValido() throws ExceptionUtilizadorPasswordNula, ExceptionUtilizadorNomeNulo, ExceptionUtilizadorJaUsado, ExceptionListaUtilizadoresVazia {
        IUtilizador utilizador1 = new Utilizador("nome", "password");
        server.adicionarUtilizador(utilizador1);
        assertTrue(server.loginUtilizador("nome", "password"));
        assertFalse(server.loginUtilizador("nome2", "password2"));
    }

    @Test
    void testServerCancelarUtilizador() throws ExceptionUtilizadorPasswordNula, ExceptionUtilizadorNomeNulo, ExceptionUtilizadorJaUsado, ExceptionListaUtilizadoresVazia, ExceptionIndexUtilizadorInvalido {
        IUtilizador utilizador1 = new Utilizador("nome", "password");
        server.adicionarUtilizador(utilizador1);
        assertTrue(server.loginUtilizador("nome", "password"));
        server.cancelarUtilizador(0);
        assertFalse(server.loginUtilizador("nome", "password"));
    }

    @Test
    void testServerAdicionarEmprestimoUtilizadorEExtenderEmprestimoEGetLivroEmprestimoValido() throws ExceptionUtilizadorPasswordNula, ExceptionUtilizadorNomeNulo, ExceptionUtilizadorJaUsado, ExceptionRepositorioLivroNulo, ExceptionManagerRepositoriosLivrosComRepositorioJaExiste, ExceptionNomeEditoraNulo, ExceptionTermoResponsabilidadeEditoraNulo, ExceptionLivroHashFicheiroNulo, ExceptionLivroAutorNulo, ExceptionLivroEditoraNulo, ExceptionLivroTituloNulo, ExceptionLivroTamanhoFicheiroInvalido, ExceptionLivroJaExiste, ExceptionListaRepositoriosLivrosVazia, ExceptionLivroNulo, ExceptionIndiceLivroEmprestadoInvalido, ExceptionUtilizadorLivroJaEmprestado, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionIndiceRepositorioLivroInvalido, ExceptionRepositorioLivrosIndiceLivroInvalido, ExceptionTituloLivroVazio, ExceptionEmprestimoLivroNulo, ExceptionLivroNaoExiste, ExceptionTermosResponsabilidadeNaoAceites, ExceptionEmprestimoLivroDuraçaoInvalida, ExceptionIndexUtilizadorInvalido, ExceptionListaLivrosEmprestadosVazia, ExceptionEmprestimoLivroLimiteExtenderExcedido, ExceptionUtilizadorIndexEmprestimoLivroInvalido, ExceptionEmprestimoLivroDataEmprestimoExcedida {
        IUtilizador utilizador1 = new Utilizador("nome", "password");
        server.adicionarUtilizador(utilizador1);

        IRepositorioLivros repositorio = new RepositorioLivros();
        server.adicionarRepositorio(repositorio);

        IEditora editora = new Editora("editora1", "termo1");
        ILivro livro = new Livro("titulo1", "autor1", editora, 1, "hash");
        server.addLivro(livro);
        server.adicionarEmprestimoLivro("titulo1", 0, 2, true);
        assertThrows(ExceptionEmprestimoLivroDataEmprestimoExcedida.class, () -> server.getLivroEmprestimoUtilizador(0, 0));
        server.extenderEmprestimoLivro(0, 0);
        assertEquals(livro, server.getLivroEmprestimoUtilizador(0, 0));
    }


}
