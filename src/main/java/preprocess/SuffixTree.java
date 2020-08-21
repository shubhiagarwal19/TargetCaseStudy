/*
 * Suffix Tree Class to build the suffix tree from the text
 * and search a pattern in the text
 * @author: Shubhi Agarwal
 */
package preprocess;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree {
    private static final String END = "#";
    private static final int UNKNOWN_INDEX = -1;
    private Node root;
    private String data;

    //constructor
    public SuffixTree(String text) {
        //Root has no parent so its index is set as -1
        root = new Node("", UNKNOWN_INDEX);
        data = text;
    }

    //Build the suffix tree for the input text.
    public void buildTree(String text){
        int n = text.length();
        for (int i = 0; i < n; i++)
        {
            String substring = text.substring(i) + END;
            createSuffixTree(substring, i);
        }
    }

    //create suffix tree
    private void createSuffixTree(String suffix, int index) {
        //first find if there is any common node string present in the tree
        List<Node> nodes = findCommonNodeInTree(suffix, root);
        //if no common node found in the tree then add suffix as child node to root
        if (nodes.size() == 0)
        {
            Node newChild = new Node(suffix, index);
            root.getChildren().add(newChild);
        }
        else
        {
            // if the node list has has only one item
            // then this item is going to be the parent of our suffix string
            Node lastNode = nodes.remove(nodes.size() - 1);
            String newText = suffix;

            //if more than one item in the node list then update the suffix string
            //remove common string upto last node from suffix
            //to get newText
            if (nodes.size() > 0)
            {
                StringBuilder b = new StringBuilder();
                for (Node n: nodes)
                {
                    b.append(n.getText());
                }
                newText = newText.substring(b.length());
            }
            // split the current node into parent and child and add new text as new child to parent
            extendNode(lastNode, newText, index);
        }
    }

    //extending node by adding new text as child to the existing node(parent)
    private void extendNode(Node node, String newText, int index)
    {
        //get node text that needs to be splitted
        String currentText = node.getText();

        //find the common string between the new text and the current text
        // so that common string can be made parent and remaining strings children
        String commonPrefix = getCommonPrefix(currentText, newText);
        int commonLen = commonPrefix.length();

        //if the current text and commonPrefix are not equal, then split the current text
        //make common prefix as parent
        //make remaining String as child
        if (!commonPrefix.equals(currentText))
        {
            String remaining = currentText.substring(commonLen);
            splitNode(node, commonPrefix, remaining);
        }

        //remove common prefix from new text and add the remaining string as child
        String childText = newText.substring(commonLen);
        Node newChild = new Node(childText , index);
        node.getChildren().add(newChild);
    }


    //find the common prefix of two strings
    private String getCommonPrefix(String str1, String str2) {
        int minlength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minlength; i++)
        {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, minlength);
    }

    //splitting node into parent and child node
    //SuffixIndex for leaf nodes will be >= 0 and for non-leaf nodes will be -1
    private void splitNode(Node parent, String newParent, String newChild) {
        //set the index of newChild string same as index of the parent node
        Node child = new Node(newChild, parent.getIndex());

        int numOfChildren = parent.getChildren().size();
        //if parent node has any children, then their new parent is our newChild now
        if (numOfChildren > 0) {
            for(int i = 0; i< numOfChildren; i++)
            {
                Node ch = parent.getChildren().remove(0);
                child.getChildren().add(ch);
            }
        }
        // parent node now becomes an internal node with index -1
        // update the parent text, add new child to children list
        parent.setText(newParent);
        parent.getChildren().add(child);
        parent.setIndex(UNKNOWN_INDEX);
    }

    //Traversing tree to find common nodes present in the tree if any
    private List<Node> findCommonNodeInTree(String pattern, Node rootNode) {
        List<Node> nodes = new ArrayList<>();
        // check if any child of root node matches the pattern string
        for (int i = 0; i < rootNode.getChildren().size(); i++)
        {
            Node currentNode = rootNode.getChildren().get(i);
            String nodeText = currentNode.getText();
            //if first index matches for both pattern string and current node string
            if (pattern.charAt(0) == nodeText.charAt(0))
            {
                //add the current node to the list
                nodes.add(currentNode);

                //if the length of pattern string is less than the current node string
                //means pattern string is sub-string of the current node
                //return.
                if (pattern.length() <= nodeText.length())
                {
                    return nodes;
                }
                else
                {
                    //if pattern length is greater node text length
                    //means current node is an internal node
                    //compare the nonCommonPatternString with the children nodes
                    //find the common node if any
                    //add them to the list as well
                    int minLength = Math.min(nodeText.length(), pattern.length());
                    if (pattern.length() > minLength) {
                        String nonCommonPatternString = pattern.substring(minLength);
                        List<Node> childNodes = findCommonNodeInTree(nonCommonPatternString, currentNode);
                        if (childNodes.size() > 0) {
                            nodes.addAll(childNodes);
                        }
                    }
                }
                return nodes;
            }
        }
        return nodes;
    }

    // search pattern and return count of the indexes
    public int search(String pattern)
    {
        List<Integer> indexes = new ArrayList<>();
        List<Node> nodes = findCommonNodeInTree(pattern, root);

        if (nodes.size() > 0) {
            Node lastNode = nodes.get(nodes.size() - 1);
            if (lastNode != null) {
                indexes = getPatternIndexes(lastNode);
            }
        }
        return indexes.size();
    }

    // return the list of indexes of the pattern searched
    private List<Integer> getPatternIndexes(Node node) {
        List<Integer> indexes = new ArrayList<Integer>();
        //if node has no children, means node string ends with #
        // add node index to the list
        if (node.getText().endsWith(END))
        {
            indexes.add(node.getIndex());
        }
        //if node has children then get the index of every child and add to the list
        else
        {
            int size = node.getChildren().size();
            for (int i = 0; i < size; i++)
            {
                Node childNode = node.getChildren().get(i);
                List<Integer> childIndexes = getPatternIndexes(childNode);
                indexes.addAll(childIndexes);
            }
        }
        return indexes;
    }
}