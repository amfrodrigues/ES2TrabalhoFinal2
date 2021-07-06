import com.es2.trabalhofinal.Editora;
import com.es2.trabalhofinal.ExceptionNomeEditoraNulo;
import com.es2.trabalhofinal.ExceptionTermoResponsabilidadeEditoraNulo;
import com.es2.trabalhofinal.IEditora;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestesEditora {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @AfterAll
    static void tearDownAfterAll() {
    }

    @BeforeEach
    void setupAfterEach() {
    }

    @AfterEach
    void tearDownAfterEach() {
    }

    @Test
    void testEditoraNomeNulo() {
        assertThrows(ExceptionNomeEditoraNulo.class, () -> new Editora(null, "valido"));
    }

    @Test
    void testEditoraTermoResponsabilidadeNulo() {
        assertThrows(ExceptionTermoResponsabilidadeEditoraNulo.class, () -> new Editora("valido", null));
    }

    @Test
    void testEditoraValido() throws ExceptionNomeEditoraNulo, ExceptionTermoResponsabilidadeEditoraNulo {
        String nome = "nomeValido";
        String termoResponsabilidade = "termoValido";
        IEditora editora = new Editora(nome, termoResponsabilidade);

        assertEquals(nome, editora.getNome());
        assertEquals(termoResponsabilidade, editora.getTermoResponsabilidade());
    }
}
