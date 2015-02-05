
public class BinaryTreeNode
	<DataType extends Comparable<DataType>>
{
	DataType data;
	BinaryTreeNode<DataType> left, right;
	BinaryTreeNode(DataType data) {
		this.data = data;
	}
	
	void add(DataType moreData) {
		// figure out which side to place it
		if (data.compareTo(moreData) <= 0) {
			// it goes to the right
			if (right == null) {
				// create a node with the data and call it a day
				right = new BinaryTreeNode<DataType>(moreData); 
			} else {
				// keep looking recursively
				right.add(moreData);
			}
		} else {
			// it goes to the left
			if (left == null) {
				// create a node and call it a day
				left = new BinaryTreeNode<DataType>(moreData);
			} else {
				// keep looking recursively
				left.add(moreData);
			}
		}
		// put it there
	}
	//similar to add function
	boolean contains (DataType data) {
		int result = this.data.compareTo(data);
		if (result < 0) {
			if (right == null) { 
				return false;
			} else {
				return right.contains(data);
			}
		} else if (result == 0) {
			return true;
		}
			if (left == null) {
				return false;
			} else {
				return left.contains(data);
			}
	}
	public static void main(String[] args) {
		System.out.println("Hello".compareTo("World"));
	}
}
