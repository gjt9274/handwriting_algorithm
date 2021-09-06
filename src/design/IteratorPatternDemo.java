package design;

public class IteratorPatternDemo {
    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();
        Iterator iter = nameRepository.getIterator();
        while (iter.hasNext()) {
            String name = (String) iter.next();
            System.out.println(name);
        }
    }
}

// 1. 定义迭代器接口
interface Iterator{
    boolean hasNext();
    Object next();
}

// 2. 容器接口
interface Container{
    Iterator getIterator();
}

// 3. 实现容器接口
class NameRepository implements Container{
    // 容器的底层存储
    String[] names = {"Robert", "John", "Julie", "Lora"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }
    private class NameIterator implements Iterator{
        int index;
        @Override
        public boolean hasNext() {
            if (index < names.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}