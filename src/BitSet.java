import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class BitSet {
    // &, |, ~, <<, >>: Java bit operations
    private byte[] bits;
    private int size;

    public BitSet(int size) {
        if (size <= 0) throw new IllegalArgumentException();
        else this.size = size;
        if (size % 8 == 0) this.bits = new byte[size / 8];
        else this.bits = new byte[size / 8 + 1];
    }

    public boolean check(int element) {
        if (element < 0 && element >= size) throw new IndexOutOfBoundsException();

        int indexOfBitSet = element / 8;
        byte numb = bits[indexOfBitSet];
        return ((numb >> (8 * (indexOfBitSet + 1) - element - 1)) & 1) == 1;
    }

    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        SB.append("{");
        for (int i = 0; i < this.size; i++) {
            if (this.check(i) == true) {
                SB.append(i).append(", ");
            }
        }
        SB.delete(SB.length() - 2, SB.length());
        SB.append("}");
        return SB.toString();
    }

    public boolean addindex(int index) {
        if (!check(index)) {
            int indexofBitSet = index / 8;
            byte number = bits[indexofBitSet];
            number = (byte) (number | (1 << (8 * (indexofBitSet + 1) - index - 1)));
            bits[indexofBitSet] = number;
            return true;
        }
        return false;
    }


    public boolean removeindex(int index) {
        if (check(index)) {
            int indexofBitSet = index / 8;
            byte number = bits[indexofBitSet];
            number = (byte) (number ^ (1 << (8 * (indexofBitSet + 1) - index - 1)));
            bits[indexofBitSet] = number;
            return true;
        }
        return false;
    }


    public BitSet OR(BitSet other) {
        if (this.size != other.size) throw new IllegalArgumentException();
        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = (byte) (this.bits[i] | other.bits[i]);
        }
        return this;
    }


    public BitSet AND(BitSet other) {
        if (this.size != other.size) throw new IllegalArgumentException();

        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = (byte) (this.bits[i] & other.bits[i]);
        }
        return this;
    }


    public BitSet COMLEMENT() {
        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = (byte) ~this.bits[i];
        }
        return this;
    }

    public int addMassive(int[] indexs) {
        int result = 0;
        for (int index : indexs) {
            if (addindex(index)) {
                result++;
            }
        }
        return result;
    }

    public int removeMassive(int[] indexs) {
        int result = 0;
        for (int index : indexs) {
            if (removeindex(index)) {
                result++;
            }
        }
        return result;
    }

}


