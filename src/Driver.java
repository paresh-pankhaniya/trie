import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Trienode {
	HashMap<Character, Trienode> hm = new HashMap<>();
	boolean isLeaf;
}

public class Driver {
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter string");
		Trie tr = new Trie();
		Trienode root = new Trienode();
		for (int i = 0; i < 3; i++) {
			String str = sc.next();
			if (!tr.search(root, str)) {
				tr.insert(root, str);
			} else {
				System.out.println("String :" + str + " do exists");
			}
		}
		tr.printTrie(root,"");
	}
}

class Trie {

	void insert(Trienode root, String str) {
		System.out.println("String ' "+str+" 'is  not found,insert is called");
		Trienode temp = root;
		for(char ch:str.toCharArray()){
			if(!temp.hm.containsKey(ch)) {
				temp.hm.put(ch, new Trienode());
			}
			temp = temp.hm.get(ch);
		}
		temp.isLeaf = true;
	}

	public void printTrie(Trienode root, String str) {
		Trienode temp = root;
		if(temp == null) return;
		
		for(Map.Entry<Character, Trienode> entry : temp.hm.entrySet()){
			//System.out.print(entry.getKey());
			if(temp != null) {
				str+=entry.getKey();
				if(entry.getValue().isLeaf ) {
					System.out.println(str);
				}
				printTrie(entry.getValue(), str);
				str = str.substring(0,str.length()-1);
			}
		}
	}

	boolean search(Trienode root, String str) {
		System.out.println("Searching for String ' "+str+" '");
		if(root.hm.size() == 0) {
			return false;
		}
		Trienode temp = root;
		for(char ch:str.toCharArray()) {
			if(!temp.hm.containsKey(ch)) {
				return false;
			}
			temp = temp.hm.get(ch);
			
		}
		return (temp != null && temp.isLeaf);
	}
}