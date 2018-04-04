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
    public void check() {
        bitSet1.addindex(1);
        bitSet1.addindex(4);
        bitSet1.addindex(5);
        bitSet1.addindex(7);
        assertTrue(bitSet1.check(1));
        assertTrue(bitSet1.check(4));
        assertTrue(bitSet1.check(5));
        assertFalse(bitSet1.check(3));
    }


    @Test
    public void OR() {
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0)
                bitSet1.addindex(i);
            if (i % 3 == 0 && i < newsize)
                bitSet2.addindex(i);
        }
        assertEquals(bitSet1, bitSet1.OR(bitSet2));
    }

    @Test
    public void AND() {
        for (int i = 0; i < size; i++) {
            if (i < 6) bitSet1.addindex(i);
            if (i < 9) bitSet2.addindex(i);
        }

        assertEquals(bitSet1, bitSet1.AND(bitSet2));
    }


    @Test
    public void addindex() {
        bitSet1.addindex(2);
        assertTrue(bitSet1.addindex(4));
        assertTrue(bitSet1.addindex(6));
        assertTrue(bitSet1.addindex(8));
        assertFalse(bitSet1.addindex(2));
    }

    @Test
    public void removeindex() {
        bitSet1.addindex(2);
        bitSet1.addindex(7);
        assertTrue(bitSet1.removeindex(2));
        assertTrue(bitSet1.removeindex(7));
        assertFalse(bitSet1.removeindex(10));
    }

    @Test
    public void complement() {
        for (int i = 0; i < newsize; i++) {
            if (i < 2) newBitSet1.addindex(i);
            else newBitSet2.addindex(i);
        }

        assertEquals(newBitSet1, newBitSet1.COMLEMENT());
        for (int i = 0; i < size; i++) {
            if (i < 10) bitSet1.addindex(i);
            else bitSet2.addindex(i);
        }

        assertEquals(bitSet1, bitSet1.COMLEMENT());
    }

    @Test
    public void addMassive() {
        bitSet1.addindex(2);
        bitSet1.addindex(7);
        assertEquals(3, bitSet1.addMassive(new int[]{2, 3, 5, 8}));
        assertEquals(1, bitSet1.addMassive(new int[]{7, 10}));
    }

    @Test
    public void removeMassive() {
        bitSet1.addindex(3);
        bitSet1.addindex(7);
        bitSet1.addindex(5);
        assertEquals(1, bitSet1.removeMassive(new int[]{9, 8, 2, 3}));
        assertEquals(2, bitSet1.removeMassive(new int[]{7, 5, 2, 3}));

    }

}