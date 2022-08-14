package ds.tree.binarytree;

import java.util.Stack;

abstract public class BinaryTree<KEYTYPE>{
    protected BinaryTreeNode<KEYTYPE> root = null;
    protected int size = 0;

    abstract public void insert(KEYTYPE key);
    abstract public void delete(KEYTYPE key);
    abstract public BinaryTreeNode<KEYTYPE> search(KEYTYPE key);
    public int size(){
        return this.size;
    }

    public void print(){
        Stack<BinaryTreeNode<KEYTYPE>> nodeStack = new Stack<BinaryTreeNode<KEYTYPE>>();
        nodeStack.push(this.root);
        // int level = 1;
        
        while (!nodeStack.isEmpty()){
            BinaryTreeNode<KEYTYPE> node = nodeStack.peek().left;
            while(node !=null){
                nodeStack.push(node);
                node = node.left;
            }

            node = nodeStack.pop();
            System.out.println(node.key);
            if (node.right!=null){
                nodeStack.push(node.right);
            }
        }
    }

}
