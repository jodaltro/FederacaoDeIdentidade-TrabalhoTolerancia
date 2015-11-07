/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

/**
 *
 * @author kinst
 */

public class StringKey implements de.uniba.wiai.lspi.chord.service.Key {

    private final String theString;

    public StringKey(String theString) {
        this.theString = theString;
    }

    //@Override
    @Override
    public byte[] getBytes() {
        return this.theString.getBytes();
    }

    //@Override
    @Override
    public int hashCode() {
        return this.theString.hashCode();
    }

    //@Override
    @Override
    public boolean equals(Object o) {
        if (o instanceof StringKey) {
            return ((StringKey) o).theString.equals(this.theString);
        }
        return false;
    }
}
