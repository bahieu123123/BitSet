import java.util.*;

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

    //Проверка принадлежности элемента множеству
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

    //Добавление заданного элемента
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

    //Удаление  заданного элемента
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

    //Добавление массива элементов
    public int addMassive(int[] indexs) {
        int result = 0;
        for (int index : indexs) {
            if (addindex(index)) {
                result++;
            }
        }
        return result;
    }

    //Удаление массива элементов
    public int removeMassive(int[] indexs) {
        int result = 0;
        for (int index : indexs) {
            if (removeindex(index)) {
                result++;
            }
        }
        return result;
    }

    //Операции:объединение
    public BitSet or(BitSet other) {
        if (this.size != other.size) throw new IllegalArgumentException();
        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = (byte) (this.bits[i] | other.bits[i]);
        }
        return this;
    }


    //Операции:пересечение
    public BitSet and(BitSet other) {
        if (this.size != other.size) throw new IllegalArgumentException();

        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = (byte) (this.bits[i] & other.bits[i]);
        }
        return this;
    }


    //Операции:дополнение
    public BitSet comlement() {
        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = (byte) ~this.bits[i];
        }
        return this;
    }
}


