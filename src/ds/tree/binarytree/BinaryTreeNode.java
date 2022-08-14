package ds.tree.binarytree;

public class BinaryTreeNode<KEYTYPE>{
    public KEYTYPE key;
    public BinaryTreeNode<KEYTYPE> left = null;
    public BinaryTreeNode<KEYTYPE> right = null;
    public BinaryTreeNode(KEYTYPE key){
        this.key = key;
    }
}