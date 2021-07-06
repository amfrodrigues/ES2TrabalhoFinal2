import com.es2.trabalhofinal.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestesLivro {
    IEditora editora;


    @BeforeAll
    static void setupBeforeAll() {
    }

    @AfterAll
    static void tearDownAfterAll() {
    }

    @BeforeEach
    void setupAfterEach() {
        try {
            editora = new Editora("valido", "valido");
        } catch (ExceptionTermoResponsabilidadeEditoraNulo exceptionTermoResponsabilidadeEditoraNulo) {
            exceptionTermoResponsabilidadeEditoraNulo.printStackTrace();
        } catch (ExceptionNomeEditoraNulo exceptionNomeEditoraNulo) {
            exceptionNomeEditoraNulo.printStackTrace();
        }
    }

    @AfterEach
    void tearDownAfterEach() {
    }

    @Test
    void testLivroTituloNulo() {
        assertThrows(ExceptionLivroTituloNulo.class, () -> {
            ILivro livro = new Livro(null, "valido", editora, 1, "valido");
        });
    }

    @Test
    void testLivroAutorNulo() {
        assertThrows(ExceptionLivroAutorNulo.class, () -> {
            ILivro livro = new Livro("valido", null, editora, 1, "valido");
        });
    }

    @Test
    void testLivroHashFicheiroNulo() {
        assertThrows(ExceptionLivroHashFicheiroNulo.class, () -> {
            ILivro livro = new Livro("valido", "valido", editora, 1, null);
        });
    }

    @Test
    void testLivroTamanhoFicheiroNulo() {
        assertThrows(ExceptionLivroTamanhoFicheiroInvalido.class, () -> {
            ILivro livro = new Livro("valido", "vaido", editora, 0, "valido");
        });
    }

    @Test
    void testLivroEditoraNulo() {
        assertThrows(ExceptionLivroEditoraNulo.class, () -> {
            ILivro livro = new Livro("valido", "valido", null, 1, "valido");
        });
    }

    @Test
    void testLivroValido() throws ExceptionLivroHashFicheiroNulo, ExceptionLivroAutorNulo, ExceptionLivroEditoraNulo, ExceptionLivroTituloNulo, ExceptionLivroTamanhoFicheiroInvalido {
        String titulo = "titulo";
        String autor = "autor";
        int tamanho = 1;
        String hash = "hash";
        ILivro livro = new Livro(titulo, autor, editora, tamanho, hash);

        assertEquals(titulo, livro.getTitulo());
        assertEquals(autor, livro.getAutor());
        assertEquals(tamanho, livro.getTamanhoFicheiro());
        assertEquals(hash, livro.getHashFicheiro());
        assertEquals(editora, livro.getEditora());
    }

    @Test
    void testGetLivroEmPFD() throws ExceptionLivroHashFicheiroNulo, ExceptionLivroAutorNulo, ExceptionLivroEditoraNulo, ExceptionLivroTituloNulo, ExceptionLivroTamanhoFicheiroInvalido {
        String titulo = "titulo";
        String autor = "autor";
        int tamanho = 1;
        String hash = "hash";
        ILivro livro = new Livro(titulo, autor, editora, tamanho, hash);

        assertEquals(titulo + ".pdf", livro.getLivroEmPDF());
    }

    @Test
    void testGetLivroEmEpub() throws ExceptionLivroHashFicheiroNulo, ExceptionLivroAutorNulo, ExceptionLivroEditoraNulo, ExceptionLivroTituloNulo, ExceptionLivroTamanhoFicheiroInvalido {
        String titulo = "titulo";
        String autor = "autor";
        int tamanho = 1;
        String hash = "hash";
        ILivro livro = new Livro(titulo, autor, editora, tamanho, hash);

        assertEquals(titulo + ".epub", livro.getLivroEmEPub());
    }

}
