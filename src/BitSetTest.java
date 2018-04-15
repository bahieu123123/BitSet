import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {
    int cardinal = 24;
    BitSet bitSet1 = new BitSet(cardinal);
    BitSet bitSet2 = new BitSet(cardinal);
    BitSet bitSet3 = new BitSet(cardinal);
    int newcardinal = 16;
    BitSet newBitSet1 = new BitSet(newcardinal);
    BitSet newBitSet2 = new BitSet(newcardinal);

    @Test
    public void getCardinal() {
        assertEquals(24, bitSet1.getCardinal());
    }

    @Test
    public void check() {
        bitSet1.addElement(1);
        bitSet1.addElement(4);
        bitSet1.addElement(5);
        bitSet1.addElement(7);
        assertTrue(bitSet1.check(1));
        assertTrue(bitSet1.check(4));
        assertTrue(bitSet1.check(5));
        assertFalse(bitSet1.check(3));
    }


    @Test
    public void or() {
        for (int i = 0; i < cardinal; i++) {
            if (i % 3 == 0)
                bitSet1.addElement(i);
            if (i % 2 == 0 && i < newcardinal)
                bitSet2.addElement(i);
        }
        bitSet3.addMassive(new int[]{0, 2, 3, 4, 6, 8, 9, 10, 12, 14, 15, 18, 21});
        assertEquals(bitSet3, bitSet1.or(bitSet2));
    }

    @Test
    public void and() {
        for (int i = 0; i < cardinal; i++) {
            if (i < 15 && i%2==0) bitSet1.addElement(i);
            if (i < 10 && i%3==0) bitSet2.addElement(i);
        }
        bitSet3.addMassive(new int[]{0, 6});
        assertEquals(bitSet3, bitSet2.and(bitSet1));
    }


    @Test
    public void addElement() {
        bitSet1.addElement(2);
        assertTrue(bitSet1.addElement(4));
        assertTrue(bitSet1.addElement(6));
        assertTrue(bitSet1.addElement(8));
        assertFalse(bitSet1.addElement(2));
    }

    @Test
    public void removeElement() {
        bitSet1.addElement(2);
        bitSet1.addElement(7);
        assertTrue(bitSet1.removeElement(2));
        assertTrue(bitSet1.removeElement(7));
        assertFalse(bitSet1.removeElement(10));
    }

    @Test
    public void complement() {
        for (int i = 0; i < newcardinal; i++) {
            if (i < 2) newBitSet1.addElement(i);
            else newBitSet2.addElement(i);
        }

        assertEquals(newBitSet1, newBitSet1.comlement());
        for (int i = 0; i < cardinal; i++) {
            if (i < 10) bitSet1.addElement(i);
            else bitSet2.addElement(i);
        }

        assertEquals(bitSet1, bitSet1.comlement());
    }

    @Test
    public void addMassive() {
        bitSet1.addElement(2);
        bitSet1.addElement(7);
        assertEquals(3, bitSet1.addMassive(new int[]{2, 3, 5, 8}));
        assertEquals(1, bitSet1.addMassive(new int[]{7, 10}));
    }

    @Test
    public void removeMassive() {
        bitSet1.addElement(3);
        bitSet1.addElement(7);
        bitSet1.addElement(5);
        assertEquals(1, bitSet1.removeMassive(new int[]{9, 8, 2, 3}));
        assertEquals(2, bitSet1.removeMassive(new int[]{7, 5, 2, 3}));

    }

}