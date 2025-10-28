package br.com.rafaelblomer.business.exceptions;

public class RetiradaProdutoIlegalException extends RuntimeException{
    private static final long serialVersionUID = -1115806424832555213L;

    public RetiradaProdutoIlegalException(String msg) {
        super(msg);
    }
}
