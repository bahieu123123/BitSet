import java.util.*;

public class BitSet  implements Iterable<Integer>{
    // &, |, ~, <<, >>: Java bit operations
    private byte[] bits;
    private int cardinal;

    public BitSet(int cardinal) {
        if (cardinal <= 0) Collections.emptyList();
        else this.cardinal = cardinal;
        if (cardinal % 8 == 0) this.bits = new byte[cardinal / 8];
        else this.bits = new byte[cardinal / 8 + 1];
    }

    /**
     * check() выполняет задачу:Проверка принадлежности элемента множеству
     * @param element
     * @return
     */
    public boolean check(int element) {
        if (element < 0 && element >= cardinal) throw new IndexOutOfBoundsException();

        int indexOfBitSet = element / 8;
        byte numb = bits[indexOfBitSet];
        return ((numb >> (8 * (indexOfBitSet + 1) - element - 1)) & 1) == 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof BitSet) {
            BitSet other = (BitSet) obj;
            if (this.cardinal != other.cardinal) return false;
            for (int i = 0; i < this.bits.length; i++) {
                if (this.bits[i] != other.bits[i]) return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bits) + cardinal;
    }


    @Override
    public String toString() {
        StringBuilder SB = new StringBuilder();
        SB.append("{");
        for (int i = 0; i < this.cardinal; i++) {
            if (this.check(i)) {
                SB.append(i).append(", ");
            }
        }
        SB.delete(SB.length() - 2, SB.length());
        SB.append("}");
        return SB.toString();
    }

    public int getCardinal() {
        return cardinal;
    }

    /**
     * addElement выполняет задачу:Добавление заданного элемента
     * @param element
     * @return
     */
    public boolean addElement(int element) {
        if (!check(element)) {
            int indexofBitSet = element / 8;
            byte number = bits[indexofBitSet];
            number = (byte) (number | (1 << (8 * (indexofBitSet + 1) - element - 1)));
            bits[indexofBitSet] = number;
            return true;
        }
        return false;
    }

    /**
     * removeElement выполняет задачу:Удаление  заданного элемента
     * @param element
     * @return
     */
    public boolean removeElement(int element) {
        if (check(element)) {
            int indexofBitSet = element / 8;
            byte number = bits[indexofBitSet];
            number = (byte) (number ^ (1 << (8 * (indexofBitSet + 1) - element - 1)));
            bits[indexofBitSet] = number;
            return true;
        }
        return false;
    }

    /**
     * addMassive выполняет задачу:Добавление массива элементов
     * @param elements
     * @return
     */
    public int addMassive(int[] elements) {
        int result = 0;
        for (int element : elements) {
            if (addElement(element)) {
                result++;
            }
        }
        return result;
    }

    /**
     * removeMassive выполняет задачу:Удаление массива элементов
     * @param elements
     * @return
     */
    public int removeMassive(int[] elements) {
        int result = 0;
        for (int element : elements) {
            if (removeElement(element)) {
                result++;
            }
        }
        return result;
    }

    /**
     * or() выполняет oперации объединение
     * @param other
     * @return
     */
    public BitSet or(BitSet other) {
        if (this.cardinal != other.cardinal) throw new IllegalArgumentException();
        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = (byte) (this.bits[i] | other.bits[i]);
        }
        return this;
    }


    /**
     * and() выполняет oперации пересечение
     * @param other
     * @return
     */
    public BitSet and(BitSet other) {
        if (this.cardinal != other.cardinal) throw new IllegalArgumentException();

        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = (byte) (this.bits[i] & other.bits[i]);
        }
        return this;
    }


    /**
     * comlement() выполняет oперации дополнение
     * @return
     */
    public BitSet comlement() {
        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = (byte) ~this.bits[i];
        }
        return this;
    }

    /**Bonus.
     * Итератор по множеству.
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new BitSetIterator();
    }

    private class BitSetIterator implements Iterator<Integer> {
        private int cursor;

        public BitSetIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            for (int i = cursor; i < BitSet.this.cardinal; i++) {
                if (BitSet.this.check(i)) {
                    return true;
                }
                cursor++;
            }
            return false;
        }

        @Override
        public Integer next(){
            if (this.hasNext()){
                int current = cursor;
                cursor++;
                return current;
            }
            throw new NoSuchElementException();
        }
    }
}


