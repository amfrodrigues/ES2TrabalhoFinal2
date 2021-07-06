import com.es2.trabalhofinal.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestesRepositorioLivros {
    IRepositorioLivros repositorio;
    ILivro livro1, livro2;

    @BeforeAll
    static void setupBeforeAll() {
    }

    @AfterAll
    static void tearDownAfterAll() {
    }

    @BeforeEach
    void setupAfterEach() throws ExceptionNomeEditoraNulo, ExceptionTermoResponsabilidadeEditoraNulo, ExceptionLivroHashFicheiroNulo, ExceptionLivroAutorNulo, ExceptionLivroEditoraNulo, ExceptionLivroTituloNulo, ExceptionLivroTamanhoFicheiroInvalido {
        repositorio = new RepositorioLivros();
        IEditora editora = new Editora("nome", "termo1");
        livro1 = new Livro("titulo1", "autor1", editora, 1, "hash1");
        livro2 = new Livro("titulo2", "autor2", editora, 1, "hash2");
    }

    @AfterEach
    void tearDownAfterEach() {
        repositorio = null;
    }

    @Test
    void testRepositorioLivrosComListaLivrosNula() {
        assertThrows(ExceptionRepositorioListaLivrosNulo.class, () -> new RepositorioLivros(null));
    }

    @Test
    void testRepositorioLivroAddLivroNulo() {
        assertThrows(ExceptionLivroNulo.class, () -> repositorio.addLivro(null));
    }

    @Test
    void testRepositorioLivroAddLivroJaExiste() throws ExceptionLivroJaExiste, ExceptionLivroNulo {
        repositorio.addLivro(livro1);
        assertThrows(ExceptionLivroJaExiste.class, () -> repositorio.addLivro(livro1));
    }

    @Test
    void testRepositorioLivroAddLivroEGetIndiceLivroValido() throws ExceptionLivroJaExiste, ExceptionLivroNulo, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionLivroNaoExiste, ExceptionTituloLivroVazio {
        repositorio.addLivro(livro1);
        assertEquals(0, repositorio.getIndiceLivro("titulo1"));
    }


    @Test
    void testRepositorioLivroGetLivroPorIndiceListaLivrosVazia() {
        assertThrows(ExceptionRepositorioLivrosListaLivrosVazia.class, () -> repositorio.getLivroPorIndice(0));
    }

    @Test
    void testRepositorioLivroGetLivroPorIndiceComIndiceInvalido() throws ExceptionLivroJaExiste, ExceptionLivroNulo {
        repositorio.addLivro(livro1);
        assertThrows(ExceptionRepositorioLivrosIndiceLivroInvalido.class, () -> repositorio.getLivroPorIndice(-1));
        assertThrows(ExceptionRepositorioLivrosIndiceLivroInvalido.class, () -> repositorio.getLivroPorIndice(1));
    }

    @Test
    void testRepositorioLivrosGetLivroPorIndice() throws ExceptionLivroJaExiste, ExceptionLivroNulo, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionRepositorioLivrosIndiceLivroInvalido {
        repositorio.addLivro(livro1);
        assertEquals(livro1, repositorio.getLivroPorIndice(0));
    }

    @Test
    void testRepositorioLivrosGetIndiceLivroListaComLivrosVazia() {
        assertThrows(ExceptionRepositorioLivrosListaLivrosVazia.class, () -> repositorio.getIndiceLivro("titulo"));
    }

    @Test
    void testRepositorioLivrosGetIndiceLivroListaComTituloVazio() throws ExceptionLivroJaExiste, ExceptionLivroNulo {
        repositorio.addLivro(livro1);
        assertThrows(ExceptionTituloLivroVazio.class, () -> repositorio.getIndiceLivro(""));
    }

    @Test
    void testRepositorioLivroGetIndiceLivroComLivroInexistente() throws ExceptionLivroJaExiste, ExceptionLivroNulo {
        repositorio.addLivro(livro1);
        assertThrows(ExceptionLivroNaoExiste.class, () -> repositorio.getIndiceLivro("titulo2"));
    }

    @Test
    void testRepositorioLivroGetIndiceValido() throws ExceptionLivroJaExiste, ExceptionLivroNulo, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionTituloLivroVazio, ExceptionLivroNaoExiste {
        repositorio.addLivro(livro1);
        repositorio.addLivro(livro2);
        assertEquals(0, repositorio.getIndiceLivro("titulo1"));
        assertEquals(1, repositorio.getIndiceLivro("titulo2"));
    }

    @Test
    void testRepositorioLivroGetListaTodosLivrosComListaLivrosVazia() {
        assertThrows(ExceptionRepositorioLivrosListaLivrosVazia.class, () -> repositorio.getListaTodosLivros());
    }

    @Test
    void testRepositorioLivroGetListaTodosLivros() throws ExceptionLivroJaExiste, ExceptionLivroNulo, ExceptionRepositorioLivrosListaLivrosVazia {
        repositorio.addLivro(livro1);
        repositorio.addLivro(livro2);

        List<ILivro> testListaLivros = new ArrayList<>();
        testListaLivros.add(livro1);
        testListaLivros.add(livro2);

        assertEquals(testListaLivros, repositorio.getListaTodosLivros());
    }
}
