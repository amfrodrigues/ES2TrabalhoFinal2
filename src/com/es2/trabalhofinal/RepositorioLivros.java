package com.es2.trabalhofinal;

import java.util.ArrayList;
import java.util.List;

public class RepositorioLivros implements IRepositorioLivros {
    private final List<ILivro> listaLivros;

    public RepositorioLivros() {
        this.listaLivros = new ArrayList<>();
    }

    public RepositorioLivros(List<ILivro> listaLivros) throws ExceptionRepositorioListaLivrosNulo {
        if (listaLivros == null) throw new ExceptionRepositorioListaLivrosNulo();
        this.listaLivros = listaLivros;
    }


    @Override
    public void addLivro(ILivro livro) throws ExceptionLivroJaExiste, ExceptionLivroNulo {
        if (livro == null) throw new ExceptionLivroNulo();
        if (this.listaLivros.contains(livro)) throw new ExceptionLivroJaExiste();
        this.listaLivros.add(livro);
    }

    @Override
    public ILivro getLivroPorIndice(int indiceLivro) throws ExceptionRepositorioLivrosIndiceLivroInvalido, ExceptionRepositorioLivrosListaLivrosVazia {
        if (this.listaLivros.isEmpty()) throw new ExceptionRepositorioLivrosListaLivrosVazia();
        if (indiceLivro < 0 || indiceLivro > this.listaLivros.size() - 1)
            throw new ExceptionRepositorioLivrosIndiceLivroInvalido();
        return this.listaLivros.get(indiceLivro);
    }

    @Override
    public int getIndiceLivro(String titulo) throws ExceptionLivroNaoExiste, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionTituloLivroVazio {
        if (this.listaLivros.isEmpty()) throw new ExceptionRepositorioLivrosListaLivrosVazia();
        if (titulo.equals("")) throw new ExceptionTituloLivroVazio();
        var contador = 0;
        for (ILivro livro : listaLivros) {
            if (livro.getTitulo().equals(titulo)) return contador;
            contador++;
        }
        throw new ExceptionLivroNaoExiste();
    }

    @Override
    public List<ILivro> getListaTodosLivros() throws ExceptionRepositorioLivrosListaLivrosVazia {
        if (this.listaLivros.isEmpty()) throw new ExceptionRepositorioLivrosListaLivrosVazia();
        return this.listaLivros;
    }
}
