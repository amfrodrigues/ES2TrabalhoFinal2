package com.es2.trabalhofinal;

public class Livro implements ILivro {
    private final String titulo;
    private final String autor;
    private final IEditora editora;
    private final int tamanhoFicheiro;
    private final String hashFicheiro;

    public Livro(String titulo, String autor, IEditora editora, int tamanhoFicheiro, String hashFicheiro) throws ExceptionLivroTituloNulo, ExceptionLivroAutorNulo, ExceptionLivroHashFicheiroNulo, ExceptionLivroEditoraNulo, ExceptionLivroTamanhoFicheiroInvalido {
        if (titulo == null) throw new ExceptionLivroTituloNulo();
        if (autor == null) throw new ExceptionLivroAutorNulo();
        if (hashFicheiro == null) throw new ExceptionLivroHashFicheiroNulo();
        if (editora == null) throw new ExceptionLivroEditoraNulo();
        if (tamanhoFicheiro < 1) throw new ExceptionLivroTamanhoFicheiroInvalido();
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.tamanhoFicheiro = tamanhoFicheiro;
        this.hashFicheiro = hashFicheiro;
    }


    @Override
    public IEditora getEditora() {
        return this.editora;
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

    @Override
    public String getAutor() {
        return this.autor;
    }

    @Override
    public String getHashFicheiro() {
        return this.hashFicheiro;
    }

    @Override
    public int getTamanhoFicheiro() {
        return this.tamanhoFicheiro;
    }

    @Override
    public String getLivroEmPDF() {
        return this.titulo + ".pdf";
    }

    @Override
    public String getLivroEmEPub() {
        return this.titulo + ".epub";
    }
}
