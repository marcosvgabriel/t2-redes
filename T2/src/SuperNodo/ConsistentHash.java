import java.util.sortedMap;
import java.util.TreeMap;

public class ConsistentHash<T> {

    private int numberOfReplicas;
    private SortedMap<Integer, T> circle;

    public ConsistentHash() {
        this.numberOfReplicas = 3;
        this.circle = new TreeMap<>();
    }

    public ConsistentHash(int numberOfReplicas) {
        this.numberOfReplicas = numberOfReplicas;
    }

    public boolean isEmpty() {
        return circle.isEmpty();
    }

    public void addNode(T node) {
        //Adds a node to the hash ring (including a number of replicas)
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put((this.generateKey(node.toString()) + i) % 100, node);
        }
    }

    public void removeNode(T node) {
        //Removes node from the hash ring and its replicas
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove((this.generateKey(node.toString()) + i) % 100);
        }
    }

    public T getNodePosition(String stringKey) {
        if (circle.isEmpty()) {
            return null;
        }

        Integer intKey = this.generateKey(stringKey);

        if (!circle.containsKey(intKey)) {
            SortedMap<Integer, T> tailMap = circle.tailMap(intKey);
            intKey = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(intKey);
    }

    public Integer generateKey(String key) {
        //Given a string key it returns an Integer value, which represents a place on the hash ring.
        return key.hashCode() % 100;
    }
}
