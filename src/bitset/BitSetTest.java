package bitset;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {
    int cardinal = 24;
    bitset.BitSet bitSet1 = new bitset.BitSet(cardinal);
    bitset.BitSet bitSet2 = new bitset.BitSet(cardinal);
    bitset.BitSet bitSet3 = new bitset.BitSet(cardinal);
    int newcardinal = 16;
    bitset.BitSet newBitSet1 = new bitset.BitSet(newcardinal);
    bitset.BitSet newBitSet2 = new bitset.BitSet(newcardinal);

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
            if (i < 6) newBitSet1.addElement(i);
            else newBitSet2.addElement(i);
        }

        assertEquals(newBitSet1, newBitSet2.complement());
        for (int i = 0; i < cardinal; i++) {
            if(i < 10) bitSet1.addElement(i);
            else bitSet2.addElement(i);
        }

        assertEquals(bitSet2, bitSet1.complement());
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
    @Test
    public void iterator() {
        int cursor = 0;
        bitset.BitSet ex = new bitset.BitSet(10);
        for (int i = 0; i < ex.getCardinal(); i++) {
            if (i % 2 == 0) ex.addElement(i);
        }

        Iterator it = ex.iterator();

        while (it.hasNext()) {
            assertEquals(cursor, it.next());
            cursor += 2;
        }
    }

}