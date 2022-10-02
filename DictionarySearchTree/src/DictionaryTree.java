public class DictionaryTree {

	public WordNode root;

	public DictionaryTree() {
		root = null;
	}

	public void addWordNode(String word) {
		WordNode temp = new WordNode(word);
		
		if (root == null) {
			root = temp;
		} else {
			insert(root, temp);
		}
	}

	private void insert(WordNode myRoot, WordNode temp) {
		assert(true);
		while (true) {
			if (temp.getWord().compareTo(myRoot.getWord()) == 0)
			return;

			if (temp.getWord().compareTo(myRoot.getWord()) <= -1) {
				if (myRoot.left != null)
					myRoot = myRoot.left;
				else {
					myRoot.left = temp;
					break;
				}
			} else {
				if (myRoot.right != null) {
					myRoot = myRoot.right;
				} else {
					myRoot.right = temp;
					break;
				}
			}

		}
		assert(myRoot != null): "Tree is null";
	}
	
	public Boolean spellCheck(WordNode Root,WordNode temp) {
		assert(Root != null):"Tree is empty";
		if (Root == null) {
			return false;
		}
		
		Boolean isPresent = false;
		
		while (Root != null) {
			//System.out.println("Root before: " + Root.getWord());
			//System.out.println("temp before: " + temp.getWord());
			if (temp.getWord().compareTo(Root.getWord()) == 0) {
				isPresent = true;
			}
			//System.out.println("Root compare: " + (temp.getWord().compareTo(Root.getWord()) == 0));
			//System.out.println("Root before " + root.getWord());
			if (temp.getWord().compareTo(Root.getWord()) <= -1) {
				//System.out.println(root.getWord());
				Root = Root.left;
				//System.out.println("Root after left: " + Root.getWord());
			} else if (temp.getWord().compareTo(Root.getWord()) > -1) {
				//System.out.println(root.getWord());
				Root = Root.right;
				//System.out.println("Root after right: " + Root.getWord());
			} else {
				isPresent = true;
				break;
			}
		}
		
		assert(isPresent != null):"isPresent is somehow Null";
		
		return isPresent;
		
	}
	
	
	public WordNode removeWord(WordNode Root, String temp) {
		assert (temp != null):"Temp is null";
		if (Root == null) {
			return Root;
		}
		
		//System.out.println("Root before cycle " + Root.getWord());
		//System.out.println("Temp Before cycle " + temp);
		
		//System.out.println("Root after cycle " + Root.getWord());
		
		//System.out.println("temp to root value " + temp.compareTo(Root.word));

		if (temp.compareTo(Root.word) <= -1) {
			Root.left = removeWord(Root.left, temp);
		} else if (temp.compareTo(Root.word) > 0) {
			Root.right = removeWord(Root.right, temp);
		} else {
			if (Root.left == null) {
				return Root.right;
			} else if (Root.right == null) 
				return Root.left;
				
				Root.word = successor(Root.right);
				
				//System.out.println("Root.word is " + Root.word);
						
				Root.right = removeWord(Root.right, Root.word);
				
				//System.out.println("Root.right is " + Root.right);
			
			

			// Root.right = removeWord(Root.right, Root.word);
		}
		assert(Root != null): "Root is null";
		//System.out.println("Root after cycle " + Root.getWord());
		return Root;
	}

	public static WordNode minValue(WordNode newRoot) {
		if (newRoot.left == null) {
			return newRoot;
		} else {
			return minValue(newRoot.left);
		}
	}

	public static String successor(WordNode root){
		String str = root.word;
        while(root.left != null){
        	str = root.left.word;
            root = root.left;
        }
        //System.out.println("Root for successor " + root.word);
        return str;
    }

	public void inOrder() {
		inOrderRecursive(root);
	}

	private void inOrderRecursive(WordNode myRoot) {
		if (myRoot != null) {
			inOrderRecursive(myRoot.left);
			System.out.println(myRoot.getWord());
			inOrderRecursive(myRoot.right);
		}
	}

}
