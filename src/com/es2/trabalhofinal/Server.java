package com.es2.trabalhofinal;

import java.util.ArrayList;
import java.util.List;

public class Server implements IServer {
    private final List<IUtilizador> listaUtilizadores;
    private final IManagerRepositorioLivros managerRepositorioLivros;

    public Server(IManagerRepositorioLivros managerRepositorioLivros) throws ExceptionManagerRepositorioLivrosNulo {
        if (managerRepositorioLivros == null) throw new ExceptionManagerRepositorioLivrosNulo();
        this.listaUtilizadores = new ArrayList<>();
        this.managerRepositorioLivros = managerRepositorioLivros;
    }

    @Override
    public boolean loginUtilizador(String nome, String password) throws ExceptionListaUtilizadoresVazia {
        if (this.listaUtilizadores.isEmpty()) throw new ExceptionListaUtilizadoresVazia();
        for (IUtilizador utilizador : listaUtilizadores) {
            if (utilizador.checkLogin(nome, password)) return true;
        }
        return false;
    }

    @Override
    public void adicionarUtilizador(IUtilizador utilizador) throws ExceptionUtilizadorJaExiste {
        if (listaUtilizadores.contains(utilizador)) throw new ExceptionUtilizadorJaExiste();
        this.listaUtilizadores.add(utilizador);
    }

    @Override
    public void cancelarUtilizador(int indexUtilizador) throws ExceptionIndexUtilizadorInvalido, ExceptionListaUtilizadoresVazia {
        if (this.listaUtilizadores.isEmpty()) throw new ExceptionListaUtilizadoresVazia();
        if (indexUtilizador < 0 || indexUtilizador > this.listaUtilizadores.size() - 1)
            throw new ExceptionIndexUtilizadorInvalido();
        this.listaUtilizadores.get(indexUtilizador).desativarUtilizador();
    }

    @Override
    public void adicionarRepositorio(IRepositorioLivros repositorio) throws ExceptionRepositorioLivroNulo, ExceptionManagerRepositoriosLivrosComRepositorioJaExiste {
        managerRepositorioLivros.addRepositorio(repositorio);
    }

    @Override
    public void adicionarEmprestimoLivro(String tituloLivro, int indexUtilizador, int diasEmprestimo, boolean termosAceites) throws ExceptionIndiceLivroEmprestadoInvalido, ExceptionIndiceRepositorioLivroInvalido, ExceptionListaRepositoriosLivrosVazia, ExceptionUtilizadorLivroJaEmprestado, ExceptionTermosResponsabilidadeNaoAceites, ExceptionEmprestimoLivroDuraçaoInvalida, ExceptionEmprestimoLivroNulo, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionTituloLivroVazio, ExceptionLivroNaoExiste {
        if (!termosAceites) throw new ExceptionTermosResponsabilidadeNaoAceites();
        IUtilizador utilizador = this.listaUtilizadores.get(indexUtilizador);
        int indiceRepositorio = managerRepositorioLivros.getRandomRepositorio();
        int indexLivro = managerRepositorioLivros.getIndiceLivro(indiceRepositorio, tituloLivro);
        IEmprestimoLivro emprestimoLivro = new EmprestimoLivro(indexLivro, indiceRepositorio, diasEmprestimo);


        utilizador.adicionarEmprestimoLivro(emprestimoLivro);
    }

    @Override
    public void extenderEmprestimoLivro(int indexUtilizador, int indexEmprestimoLivro) throws ExceptionIndexUtilizadorInvalido, ExceptionListaLivrosEmprestadosVazia, ExceptionUtilizadorIndexEmprestimoLivroInvalido, ExceptionEmprestimoLivroLimiteExtenderExcedido {
        if (indexUtilizador < 0 || indexUtilizador > this.listaUtilizadores.size() - 1)
            throw new ExceptionIndexUtilizadorInvalido();
        IUtilizador utilizador = this.listaUtilizadores.get(indexUtilizador);
        IEmprestimoLivro emprestimoLivro = utilizador.getLivroEmprestimo(indexEmprestimoLivro);
        emprestimoLivro.extenderEmprestimo();
    }

    @Override
    public List<IEmprestimoLivro> getAllEmprestimosUtilizador(int indexUtilizador) throws ExceptionUtilizadorListaEmprestimosVazia, ExceptionIndexUtilizadorInvalido {
        if (indexUtilizador < 0 || indexUtilizador > this.listaUtilizadores.size() - 1)
            throw new ExceptionIndexUtilizadorInvalido();
        return this.listaUtilizadores.get(indexUtilizador).getAllEmprestimosUtilizador();
    }

    @Override
    public ILivro getLivroEmprestimoUtilizador(int indexUtilizador, int indexEmprestimoLivro) throws ExceptionListaLivrosEmprestadosVazia, ExceptionRepositorioLivrosIndiceLivroInvalido, ExceptionListaRepositoriosLivrosVazia, ExceptionRepositorioLivrosListaLivrosVazia, ExceptionUtilizadorIndexEmprestimoLivroInvalido, ExceptionIndiceRepositorioLivroInvalido, ExceptionIndexUtilizadorInvalido, ExceptionEmprestimoLivroDataEmprestimoExcedida {
        if (indexUtilizador < 0 || indexUtilizador > this.listaUtilizadores.size() - 1)
            throw new ExceptionIndexUtilizadorInvalido();
        IUtilizador utilizador = this.listaUtilizadores.get(indexUtilizador);
        IEmprestimoLivro emprestimoLivro = utilizador.getLivroEmprestimo(indexEmprestimoLivro);
        emprestimoLivro.checkEmprestimoValido();
        return managerRepositorioLivros.getLivroPorIndice(emprestimoLivro.getIndexRepositorioEmprestimo(), emprestimoLivro.getIndexLivroEmprestimo());
    }

    @Override
    public void addLivro(ILivro livro) throws ExceptionLivroJaExiste, ExceptionListaRepositoriosLivrosVazia, ExceptionLivroNulo {
        managerRepositorioLivros.addLivro(livro);
    }


}
