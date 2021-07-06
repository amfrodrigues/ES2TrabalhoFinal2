package com.es2.trabalhofinal;

public interface ILivro {
    IEditora getEditora();

    String getTitulo();

    String getAutor();

    String getHashFicheiro();

    int getTamanhoFicheiro();

    String getLivroEmPDF();

    String getLivroEmEPub();
}
