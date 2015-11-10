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
 * @author Jodaltro
 * Class responsible for the temporary storage of the users. 
 */
/**
 * @author Jodaltro
 *
 */
public class User {

    private String name;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }
    private String passPt1;
    private String passPt2;
    private String passPt3;
    private String passPt4;
    private String passPt5;
    private String passPt6;
    private String passPt7;
    private String passPt8;
    
    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getPassPt1() {
        return passPt1;
    }

    public void setPassPt1(String _passPt1) {
        this.passPt1 = _passPt1;
    }

    public String getPassPt2() {
        return passPt2;
    }

    public void setPassPt2(String _passPt2) {
        this.passPt2 = _passPt2;
    }

    public String getPassPt3() {
        return passPt3;
    }

    public void setPassPt3(String _passPt3) {
        this.passPt3 = _passPt3;
    }

    public String getPassPt4() {
        return passPt4;
    }

    public void setPassPt4(String _passPt4) {
        this.passPt4 = _passPt4;
    }

    public String getPassPt5() {
        return passPt5;
    }

    public void setPassPt5(String _passPt5) {
        this.passPt5 = _passPt5;
    }

    public String getPassPt6() {
        return passPt6;
    }

    public void setPassPt6(String _passPt6) {
        this.passPt6 = _passPt6;
    }

    public String getPassPt7() {
        return passPt7;
    }

    public void setPassPt7(String _passPt7) {
        this.passPt7 = _passPt7;
    }

    public String getPassPt8() {
        return passPt8;
    }

    public void setPassPt8(String _passPt8) {
        this.passPt8 = _passPt8;
    }

    public User() {
        this.name = null;
        this.passPt1 = null;
        this.passPt2 = null;
        this.passPt3 = null;
        this.passPt4 = null;
        this.passPt5 = null;
        this.passPt6 = null;
        this.passPt7 = null;
        this.passPt8 = null;
    }

    /**
     * @param _password
     * split the password in 8 parts and add a number in the and of each piece.
     */
    public void breakPassword(String _password) {
        passPt1 = _password.substring(0, 4) + "1";
        passPt2 = _password.substring(4, 8) + "2";
        passPt3 = _password.substring(8, 12) + "3";
        passPt4 = _password.substring(12, 16) + "4";
        passPt5 = _password.substring(16, 20) + "5";
        passPt6 = _password.substring(20, 24) + "6";
        passPt7 = _password.substring(24, 28) + "7";
        passPt8 = _password.substring(28, 32) + "8";
    }

    
    /**
     * @return
     * rebuilds the password
     */
    public String unitePassword() {
        return passPt1 + passPt2 + passPt3 + passPt4 + passPt5 + passPt6 + passPt7 + passPt8;
    }

    /**
     * @param _password
     * @return
     * encrypt the password.
     */
    public String encryptPassword(String _password) {
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(_password.getBytes(), 0, _password.length());
            BigInteger i = new BigInteger(1, m.digest());
            _password = String.format("%1$032X", i);
            return _password;

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}