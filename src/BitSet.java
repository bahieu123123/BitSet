import static java.lang.Integer.max;

public class BitSet {
    private byte[] bits;
    private int size;

    public BitSet(int size) {
        if (size <= 0) throw new NumberFormatException();
        else this.size = size;
        if (size % 8 == 0) this.bits = new byte[size / 8];
        else this.bits = new byte[size / 8 + 1];
    }

    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        SB.append("{");
        for (int i = 0; i < this.size; i++) {
            int indexofBitSet = i / 8;
            if (((bits[indexofBitSet] >> (8 * (indexofBitSet + 1) - i - 1)) & 1) == 1) {
                SB.append(i).append(", ");
            }
        }
        SB.delete(SB.length() - 2, SB.length());
        SB.append("}");
        return SB.toString();
    }


    public void addindex(int index) {
        if (index < 0 && index >= size) throw new IndexOutOfBoundsException();
        int indexofBitSet = index / 8;
        byte number = bits[indexofBitSet];
        number = (byte) (number | (1 << (8 * (indexofBitSet + 1) - index - 1)));
        bits[indexofBitSet] = number;
    }

    public void removeindex(int index) {
        if (index < 0 && index >= size) throw new IndexOutOfBoundsException();
        int indexofBitSet = index / 8;
        byte number = bits[indexofBitSet];
        number = (byte) (number ^ (1 << (8 * (indexofBitSet + 1) - index - 1)));
        bits[indexofBitSet] = number;
    }

    public BitSet OR(BitSet other) {
        if (this.size != other.size) throw new NullPointerException();
        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = (byte) (this.bits[i] | other.bits[i]);
        }
        return this;
    }

    public BitSet AND(BitSet other) {
        if (this.size != other.size) throw new NullPointerException();
        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = (byte) (this.bits[i] & other.bits[i]);
        }
        return this;
    }
    public BitSet COMLEMENT(){
        for (int i = 0; i < this.bits.length; i++){
            this.bits[i] = (byte) ~this.bits[i];
        }
        return this;
    }
}
