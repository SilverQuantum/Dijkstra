package pathfinders;

public class Heap {

    private Node[] data;
    private int last = 0;

    public Heap(int numElements) {
        double log2 = Math.ceil(Math.log(numElements-1)/Math.log(2));
        int size = (int) Math.pow(2, log2) + 1;
        data = new Node[size];
    }
    public Heap() {
        data = new Node[65];
    }

    private void heapifyDown(int current) {
        int min;
        int left = getLeft(current);
        int right = getRight(current);
        if (right >= last) {
            if (left >= last) {
                return;
            } else {
                min = left;
            }
        } else {
            if (data[left].score <= data[right].score) {
                min = left;
            } else {
                min = right;
            }
        }
        if (data[current].score > data[min].score) {
            Node temp = data[min];
            data[min] = data[current];
            data[current] = temp;
            data[current].heapIndex = min;
            heapifyDown(min);
        }
    }
    private void heapifyUp(int current){
        int pIndex = getParent(current);
        while (true) {
            if(current < 1 || pIndex < 0 || current > last|| data[current] == null)
                return;
            if(data[pIndex].score < data[current].score)
                break;
            Node temp = data[current];
            data[current] = data[pIndex];
            data[pIndex] = temp;
            current = pIndex;
            pIndex = getParent(current);
        }
        data[current].heapIndex = current;
    }
    
    public void reheapify(int index){
        heapifyUp(index);
        heapifyDown(index);
    }
    
    public void push(Node newNode) {
        if (last != data.length) {
            int current = last;
            data[current] = newNode;
            heapifyUp(current);
            last++;
        }
    }

    public Node pop() {
        Node top = data[0];
        if (last > 0) {
            data[0] = data[last - 1];
            data[last - 1] = null;
            last--;
            heapifyDown(0);
        }
        return top;
    }
    private int getParent(int index) {
        return (index - 1) / 2;
    }

    private int getLeft(int index) {
        return (index * 2) + 1;
    }

    private int getRight(int index) {
        return (index * 2) + 2;
    }
}
