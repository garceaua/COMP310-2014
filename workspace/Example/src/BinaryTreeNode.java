
public class BinaryTreeNode
	<DataType extends Comparable<DataType>>
{
	DataType data;
	BinaryTreeNode<DataType> left, right, parent;
	BinaryTreeNode(DataType data) {
		this.data = data;
		this.parent=null;
	}
	
	private BinaryTreeNode(DataType data,BinaryTreeNode<DataType> parent) {
		this.data = data;
		this.parent = parent;
	}

	
	void add(DataType moreData) {
		BinaryTreeNode<DataType> node = findNode(moreData);
		if (node.data.compareTo(moreData) <=0){
			node.right=new BinaryTreeNode<DataType>(moreData, this);
		} else {
			node.left=new BinaryTreeNode<DataType>(moreData, this);
		}
	}
	
	
	//Return a Node with some data in it, or the parent of that node. 
	BinaryTreeNode<DataType> findNode (DataType data) {
		int result = this.data.compareTo(data);
		//The data we're looking for is not in this node
		if (result < 0) {
			if (right == null) { 
				return this;
			} else {
				return right.findNode(data);
			}
			//The data we're looking for is in this node
		} else if (result == 0) {
			return this;
		}
			if (left == null) {
				return this;
				//The data we're looking for is not in this node
			} else {
				return left.findNode(data);
			}
	}
	
	boolean contains (DataType data) {
		BinaryTreeNode<DataType> node = findNode(data);
		return node.data.equals(data);
	}
	
	
	boolean remove(DataType item) {
		BinaryTreeNode<DataType> node = findNode(item);
		//if we didn't find the item
		if (!node.data.equals(item)) {
			return false;
		}
		//if we got here, we got work to do
		// if the node has no left and no right child
		if (node.left == null && node.right == null) {
			if (node.parent != null) {
				if ( node.parent.left == node) {
					node.parent.left = null;
				}else {
					node.parent.right = null;
				}
			}
		}
		//if the node has one child figure out what to do
		if (node.left == null || node.right == null) {
			if (node.parent != null) {
				if ( node.left == item) {
					parent.left = node.right;
				}else {
					parent.right= node.right;
				}
			
				if (node.right == item) {
					parent.left = node.left;
				}else {
					parent.right = node.left;
				}
			}
		}
		//if the node has 2 kids it gets messy
		BinaryTreeNode<DataType> pointer = node;
		//for (pointer=node.left; pointer.right != null; pointer = pointer.right);
		
		BinaryTreeNode<DataType> succ = node.right;
		
		while (succ.left != null){ 
			pointer = succ;
			succ = succ.left;
		}
			node =succ;
			pointer.left= succ.right;
			
			if (succ.left ==null) {
				node =succ;
				node.right = succ.right;
			}
		
		return false;
	}
	
	
	//public static void main(String[] args) {
		//System.out.println("Hello".compareTo("World"));
	//}
}
