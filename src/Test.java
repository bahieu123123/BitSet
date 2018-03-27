import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {
    int size = 24;
    BitSet bitSet1 = new BitSet(size);
    BitSet bitSet2 = new BitSet(size);
    int newsize = 16;
    BitSet newBitSet1 = new BitSet(newsize);
    BitSet newBitSet2 = new BitSet(newsize);


    @Test
    public void OR() {
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0)
                bitSet1.addindex(i);
            if (i % 3 == 0 && i < newsize)
                bitSet2.addindex(i);
        }
        assertEquals(bitSet1, bitSet1.OR(bitSet1));
    }

    @Test
    public void AND() {
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) bitSet1.addindex(i);
            if (i % 3 == 0) bitSet2.addindex(i);
        }

        assertEquals(bitSet1, bitSet1.AND(bitSet2));
    }



    @Test
    public void addindex() {
        bitSet1.addindex(1);
        bitSet1.addindex(4);
        bitSet1.addindex(5);
        bitSet1.addindex(9);
    }

    @Test
    public void removeindex() {
        bitSet1.removeindex(1);
        bitSet1.removeindex(5);
    }
    @Test
    public void complement() {
        for (int i = 0; i < newsize; i++) {
            if (i < 2) newBitSet1.addindex(i);
            else newBitSet2.addindex(i);
        }

        assertEquals(newBitSet1, newBitSet1.COMLEMENT());
        for (int i = 0; i < size; i++) {
            if(i < 10) bitSet1.addindex(i);
            else bitSet2.addindex(i);
        }

        assertEquals(bitSet1, bitSet1.COMLEMENT());
    }

}