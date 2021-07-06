package com.es2.trabalhofinal;

import java.util.List;

public interface IManagerRepositorioLivros {
    void addLivro(ILivro livro) throws ExceptionLivroJaExiste, ExceptionListaRepositoriosLivrosVazia, ExceptionLivroNulo;

    ILivro getLivroPorIndice(int indiceRepositorio, int indiceLivro) throws ExceptionRepositorioLivrosIndiceLivroInvalido, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionListaRepositoriosLivrosVazia, ExceptionIndiceRepositorioLivroInvalido;

    int getIndiceLivro(int indiceRepositorio, String titulo) throws ExceptionLivroNaoExiste, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionListaRepositoriosLivrosVazia, ExceptionTituloLivroVazio, ExceptionIndiceRepositorioLivroInvalido;

    List<ILivro> getListaTodosLivros(int indiceRepositorio) throws ExceptionListaRepositoriosLivrosVazia, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionIndiceRepositorioLivroInvalido;

    void addRepositorio(IRepositorioLivros repositorio) throws ExceptionRepositorioLivroNulo, ExceptionManagerRepositoriosLivrosComRepositorioJaExiste;

    int getRandomRepositorio() throws ExceptionListaRepositoriosLivrosVazia;
}
