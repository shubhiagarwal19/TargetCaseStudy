package preprocess;
/*
 * Node Class  of the suffix tree
 * @author: Shubhi Agarwal
 */
import java.util.ArrayList;
import java.util.List;

public class Node {
    private String text;
    private List<Node> children;
    private int index;

    public Node(String str, int index) {
        this.text = str;
        this.index = index;
        this.children = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void setText(String str) {
        this.text = str;
    }
    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
