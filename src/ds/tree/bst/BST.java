package ds.tree.bst;

import java.util.ArrayList;

import ds.tree.binarytree.*;

abstract public class BST<KEYTYPE> extends BinaryTree<KEYTYPE> {
    
    /** provide implementation to compare your keys
    * returns 1 when key1 is greater than key2 
    * returns 0 if key1 is equal to key2
    * return -1 if key1 is less than key2
    */
    abstract public int compare(KEYTYPE key1, KEYTYPE key2);

    /**
     * this method inserts node in this 
     * binary search tree
     */
    public void insert(KEYTYPE key){
        BinaryTreeNode<KEYTYPE> parent = null;
        BinaryTreeNode<KEYTYPE> child = this.root;
        String direction = "";
        while (true){
            if (child==null)
                break;
            parent = child;
            if (this.compare(key, child.key)==1){
                child = child.right;
                direction = "right";
            }
            else {
                child = child.left;
                direction = "left";
            }     
        } 
        BinaryTreeNode<KEYTYPE> newNode = new BinaryTreeNode<KEYTYPE>(key);
        if (direction=="right")
            parent.right = newNode;
        else if (direction=="left")
            parent.left = newNode;
        else
            this.root = newNode;
        this.size+=1;
    }

    /**
     * @param node node from which left or right sub-tree will be detached
     * @param direction defines if left sub-tree will be detached or the right
     * one
     */
    public void detach(BinaryTreeNode<KEYTYPE>node, String direction){
        if (direction=="left")
            node.left = null;
        else
            node.right = null;
    }

    /**
     * delete node with specified key value from bst
     */
    public void delete(KEYTYPE key){
        ArrayList<BinaryTreeNode<KEYTYPE>> nodes = this.searchNodeAndParent(key);
        BinaryTreeNode<KEYTYPE> parentOfToBeDeletedNode = nodes.get(0), toBeDeletedNode = nodes.get(1);
        BinaryTreeNode<KEYTYPE> toBeDeletedNodeLeft = toBeDeletedNode.left;
        BinaryTreeNode<KEYTYPE> toBeDeletedNodeRight = toBeDeletedNode.right;
        String direction = (parentOfToBeDeletedNode.left == toBeDeletedNode) ?"left" : "right";
        if (nodes!=null){
            this.detach(parentOfToBeDeletedNode, direction);
            ArrayList<BinaryTreeNode<KEYTYPE>> inorderSuccessorList = this.inorderSuccesor(toBeDeletedNode);
            if (inorderSuccessorList!=null){
                BinaryTreeNode<KEYTYPE> inorderParent =inorderSuccessorList.get(0);
                BinaryTreeNode<KEYTYPE> inorderSucc =inorderSuccessorList.get(1);
                String directionOfSuccessor = (inorderParent.left == inorderSucc) ?"left" : "right";
                this.detach(inorderParent, directionOfSuccessor);
                if (direction=="left"){
                    parentOfToBeDeletedNode.left = inorderSucc;
                }
                else {
                    parentOfToBeDeletedNode.right = inorderSucc;
                }
                inorderSucc.left = toBeDeletedNodeLeft;
                inorderSucc.right = toBeDeletedNodeRight;
            }
        }
        this.size-=1;
    }

    /**
     * @param child node of which we want to find inorder successor
     * @return null if no succesor is found else returns arraylist with parent of successor on 0th index
     * and successor itself on 1st index  
     */
    public ArrayList<BinaryTreeNode<KEYTYPE>> inorderSuccesor(BinaryTreeNode<KEYTYPE> child){
        BinaryTreeNode<KEYTYPE> next = (child.left!=null ? child.left : (child.right!=null?child.right : null) );
        BinaryTreeNode<KEYTYPE> parent = null;
        
        if (next==null)
            return null;
        while(next!=null){
            parent = child;
            child = next;
            next = (child.left!=null ? child.left : (child.right!=null?child.right : null) );
        }
        ArrayList<BinaryTreeNode<KEYTYPE>> res = new ArrayList<BinaryTreeNode<KEYTYPE>>();
        res.add(parent);
        res.add(child);
        return res;
    }


    protected ArrayList<BinaryTreeNode<KEYTYPE>> searchNodeAndParent(KEYTYPE key){
        BinaryTreeNode<KEYTYPE> parent = null;
        BinaryTreeNode<KEYTYPE> child = this.root;
        ArrayList<BinaryTreeNode<KEYTYPE>> res = new ArrayList<BinaryTreeNode<KEYTYPE>>();
        while (child!=null){
            int comparison = this.compare(key, child.key);
            if (comparison==0){
                res.add(parent);
                res.add(child);
                return res;
            }
            parent = child;
            if (comparison==1){
                child = child.right;
            }
            else
                child = child.left;
        }
        return null;
    }
    /**
     * searches for node with specified key value
     */
    public BinaryTreeNode<KEYTYPE> search(KEYTYPE key){
        ArrayList<BinaryTreeNode<KEYTYPE>> nodes = searchNodeAndParent(key);
        if (nodes==null)
            return null;
        return nodes.get(1);
    }
}
