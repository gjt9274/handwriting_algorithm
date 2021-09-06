package others;

import java.util.BitSet;

public class MyBloomFilter {

    private static final int DEFAULT_SIZE = 2 << 24;

    private static final int[] SEEDS = new int[]{3,13,146,71,91,134};

    private BitSet bits = new BitSet(DEFAULT_SIZE);

    private SimpleHash[] hashFunc = new SimpleHash[SEEDS.length];

    public MyBloomFilter(){
        for (int i = 0; i < SEEDS.length; i++) {
            hashFunc[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    public void add(Object value) {
        for (SimpleHash func : hashFunc) {
            bits.set(func.hash(value), true);
        }
    }

    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash func : hashFunc) {
            ret = ret && bits.get(func.hash(value));
        }
        return ret;
    }

    public static class SimpleHash{
        private int cap;
        private int seed;
        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }
    }
}
