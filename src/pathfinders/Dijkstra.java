package pathfinders;
import graph.Graph;
import java.util.LinkedList;

public class Dijkstra implements PathFinder{
    Node[] nodes;
    Heap openList;
    
    @Override
    public int[] findPath(Graph g) {
        nodes  = new Node[g.getNumberOfNodes()];
        openList = new Heap(g.getNumberOfNodes());
        addToOpenList(0, 0, null); 
        int current = nodes[0].index;
        while(true){
            current = getMinOpenIndex();
            if(nodes[current].index == -1 || nodes[current].index == 1)
                //-1 is if openList contain no nodes
                break;
            int[][] neighbors = g.getNeighborsOfNode(nodes[current].index);
            for(int[] i : neighbors)
                addToOpenList(i[0], nodes[current].score + i[1], nodes[current]);
            addToClosedList(nodes[current].index);
        }
        current = nodes[1].index;
            
        LinkedList<Integer> temp = new LinkedList<Integer>();
        while(nodes[current].prev != null){
            temp.addFirst(current);
            current = nodes[current].prev.index;
        }
        int[] path = new int[temp.size()+1];
        path[0] = 0;
        int i = 1;
        for(Integer index: temp){
            path[i] = index;
            i++;
        }            
        return path; //returns sequence of node index
    }
    private int getMinOpenIndex(){
        int n = -1;
        //Node node = openList.pop();
        for(int i = 0; i < nodes.length; i++){// xxx
            if(nodes[i] != null){
                // n = node.index;
                // ==================
                if(nodes[i].state == Node.OPEN){
                    if(n == -1){
                        n = i;
                    }
                    else if(nodes[i].score < nodes[n].score){
                        n = i;
                    }
                }
            }
        }
        return n;
    }
    private void addToOpenList(int index, int score, Node prev){
        if(nodes[index] == null){
            nodes[index] = new Node(index, score, Node.OPEN, prev);
            openList.push(nodes[index]);
        }else if(nodes[index].state == Node.OPEN){
            if( score < nodes[index].score){
                    nodes[index].score = score;
                    nodes[index].prev = prev;
                    openList.reheapify(nodes[index].heapIndex);
            }
        }else if(nodes[index].state != Node.CLOSED)
            nodes[index].state = Node.OPEN;
    }
       
    private void addToClosedList(int index){
        if(nodes[index] != null)
            nodes[index].state = Node.CLOSED;
    }
    
}
