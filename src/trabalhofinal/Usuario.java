/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author kinst
 */
public class Usuario {

    private String nome;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    private String senhaPt1;
    private String senhaPt2;
    private String senhaPt3;
    private String senhaPt4;
    private String senhaPt5;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenhaPt1() {
        return senhaPt1;
    }

    public void setSenhaPt1(String senhaPt1) {
        this.senhaPt1 = senhaPt1;
    }

    public String getSenhaPt2() {
        return senhaPt2;
    }

    public void setSenhaPt2(String senhaPt2) {
        this.senhaPt2 = senhaPt2;
    }

    public String getSenhaPt3() {
        return senhaPt3;
    }

    public void setSenhaPt3(String senhaPt3) {
        this.senhaPt3 = senhaPt3;
    }

    public String getSenhaPt4() {
        return senhaPt4;
    }

    public void setSenhaPt4(String senhaPt4) {
        this.senhaPt4 = senhaPt4;
    }

    public String getSenhaPt5() {
        return senhaPt5;
    }

    public void setSenhaPt5(String senhaPt5) {
        this.senhaPt5 = senhaPt5;
    }

    public String getSenhaPt6() {
        return senhaPt6;
    }

    public void setSenhaPt6(String senhaPt6) {
        this.senhaPt6 = senhaPt6;
    }

    public String getSenhaPt7() {
        return senhaPt7;
    }

    public void setSenhaPt7(String senhaPt7) {
        this.senhaPt7 = senhaPt7;
    }

    public String getSenhaPt8() {
        return senhaPt8;
    }

    public void setSenhaPt8(String senhaPt8) {
        this.senhaPt8 = senhaPt8;
    }
    private String senhaPt6;
    private String senhaPt7;
    private String senhaPt8;

    public Usuario() {
        this.nome = null;
        this.senhaPt1 = null;
        this.senhaPt2 = null;
        this.senhaPt3 = null;
        this.senhaPt4 = null;
        this.senhaPt5 = null;
        this.senhaPt6 = null;
        this.senhaPt7 = null;
        this.senhaPt8 = null;
    }

    public void quebraSenha(String entrada) {
        senhaPt1 = entrada.substring(0, 4) + "1";
        senhaPt2 = entrada.substring(4, 8) + "2";
        senhaPt3 = entrada.substring(8, 12) + "3";
        senhaPt4 = entrada.substring(12, 16) + "4";
        senhaPt5 = entrada.substring(16, 20) + "5";
        senhaPt6 = entrada.substring(20, 24) + "6";
        senhaPt7 = entrada.substring(24, 28) + "7";
        senhaPt8 = entrada.substring(28, 32) + "8";
    }

    public String juntaSenha() {
        return senhaPt1 + senhaPt2 + senhaPt3 + senhaPt4 + senhaPt5 + senhaPt6 + senhaPt7 + senhaPt8;
    }

    public String criptografaSenha(String senha) {
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(), 0, senha.length());
            BigInteger i = new BigInteger(1, m.digest());
            senha = String.format("%1$032X", i);
            return senha;

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}