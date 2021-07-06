package com.es2.trabalhofinal;

import java.util.List;

public interface IRepositorioLivros {
    void addLivro(ILivro livro) throws ExceptionLivroJaExiste, ExceptionLivroNulo;

    ILivro getLivroPorIndice(int indiceLivro) throws ExceptionRepositorioLivrosIndiceLivroInvalido, ExceptionRepositorioLivrosListaLivrosVazia;

    int getIndiceLivro(String titulo) throws ExceptionLivroNaoExiste, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionTituloLivroVazio;

    List<ILivro> getListaTodosLivros() throws ExceptionRepositorioLivrosListaLivrosVazia;

}
