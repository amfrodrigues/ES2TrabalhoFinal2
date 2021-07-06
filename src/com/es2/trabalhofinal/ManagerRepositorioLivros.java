package com.es2.trabalhofinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManagerRepositorioLivros implements IManagerRepositorioLivros {
    private final List<IRepositorioLivros> listaRepositoriosLivros;
    private final Random random = new Random();

    public ManagerRepositorioLivros(List<IRepositorioLivros> listaRepositoriosLivros) throws ExceptionManagerRepositorioLivrosListaRepositoriosNula {
        if (listaRepositoriosLivros == null) throw new ExceptionManagerRepositorioLivrosListaRepositoriosNula();
        this.listaRepositoriosLivros = listaRepositoriosLivros;
    }

    public ManagerRepositorioLivros() {
        this.listaRepositoriosLivros = new ArrayList<>();
    }

    @Override
    public void addLivro(ILivro livro) throws ExceptionLivroJaExiste, ExceptionListaRepositoriosLivrosVazia, ExceptionLivroNulo {
        if (listaRepositoriosLivros.isEmpty()) throw new ExceptionListaRepositoriosLivrosVazia();
        for (IRepositorioLivros repositorio : listaRepositoriosLivros) {
            repositorio.addLivro(livro);
        }
    }

    @Override
    public ILivro getLivroPorIndice(int indiceRepositorio, int indiceLivro) throws ExceptionRepositorioLivrosIndiceLivroInvalido, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionListaRepositoriosLivrosVazia, ExceptionIndiceRepositorioLivroInvalido {
        if (listaRepositoriosLivros.isEmpty()) throw new ExceptionListaRepositoriosLivrosVazia();
        if (indiceRepositorio > this.listaRepositoriosLivros.size() - 1 || indiceRepositorio < 0)
            throw new ExceptionIndiceRepositorioLivroInvalido();
        IRepositorioLivros repositorio = this.listaRepositoriosLivros.get(indiceRepositorio);
        return repositorio.getLivroPorIndice(indiceLivro);
    }

    @Override
    public int getIndiceLivro(int indiceRepositorio, String titulo) throws ExceptionLivroNaoExiste, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionListaRepositoriosLivrosVazia, ExceptionTituloLivroVazio, ExceptionIndiceRepositorioLivroInvalido {
        if (listaRepositoriosLivros.isEmpty()) throw new ExceptionListaRepositoriosLivrosVazia();
        if (indiceRepositorio > this.listaRepositoriosLivros.size() - 1 || indiceRepositorio < 0)
            throw new ExceptionIndiceRepositorioLivroInvalido();
        IRepositorioLivros repositorio = this.listaRepositoriosLivros.get(indiceRepositorio);
        return repositorio.getIndiceLivro(titulo);
    }

    @Override
    public List<ILivro> getListaTodosLivros(int indiceRepositorio) throws ExceptionListaRepositoriosLivrosVazia, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionIndiceRepositorioLivroInvalido {
        if (listaRepositoriosLivros.isEmpty()) throw new ExceptionListaRepositoriosLivrosVazia();
        if (indiceRepositorio > this.listaRepositoriosLivros.size() - 1 || indiceRepositorio < 0)
            throw new ExceptionIndiceRepositorioLivroInvalido();
        IRepositorioLivros repositorio = this.listaRepositoriosLivros.get(indiceRepositorio);
        return repositorio.getListaTodosLivros();
    }

    @Override
    public void addRepositorio(IRepositorioLivros repositorio) throws ExceptionRepositorioLivroNulo, ExceptionManagerRepositoriosLivrosComRepositorioJaExiste {
        if (repositorio == null) throw new ExceptionRepositorioLivroNulo();
        if (this.listaRepositoriosLivros.contains(repositorio))
            throw new ExceptionManagerRepositoriosLivrosComRepositorioJaExiste();
        this.listaRepositoriosLivros.add(repositorio);
    }

    @Override
    public int getRandomRepositorio() throws ExceptionListaRepositoriosLivrosVazia {
        if (listaRepositoriosLivros.isEmpty()) throw new ExceptionListaRepositoriosLivrosVazia();
        return random.nextInt(this.listaRepositoriosLivros.size());
    }
}
