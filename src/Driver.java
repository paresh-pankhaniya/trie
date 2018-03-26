import java.util.HashMap;
import java.util.Map;

public class Trie {
    private static TrieNode root;
    public static void main(String arg[]) {
        String key[] = {"the","there","their","a","apple","by","bye"};
        int keyLen = key.length;
        root = getNode();
        for(int i = 0;i < keyLen; i++) {
            insert(root,key[i]);
        }
        searchKey(root,"their");
        searchKey(root,"aaa");
        searchKey(root,"a");
        searchKey(root,"bye");
        System.out.println("**************************************");
        printTrie(root,"");
    }
    private static boolean insert(TrieNode root,String str) {
        TrieNode newRoot = root;
        for(int it = 0;it < str.length(); it++ ) {
            char ch = str.charAt(it);
            if(!newRoot.child.containsKey(ch)) {
                TrieNode childNode = getNode();
                newRoot.child.put(ch,childNode);
            }
            newRoot = newRoot.child.get(ch);
        }
        newRoot.isLeaf = true;
        return true;
    }
    private static TrieNode getNode() {
        TrieNode newNode = new TrieNode();
        newNode.child = new HashMap<Character,TrieNode>();
        return newNode;
    }
    private static void searchKey(TrieNode root,String key) {
        TrieNode newRoot = root;
        if(root == null) {
            System.out.println("trie does'not exist");
        }
        for(int i = 0;i < key.length();i++) {
            char ch = key.charAt(i);
            if(!newRoot.child.containsKey(ch)) {
                System.out.println("key "+ key +" does'not exist");
                return;
            }
            newRoot = newRoot.child.get(ch);
        }
        if(newRoot != null && newRoot.isLeaf) {
            System.out.println("trie contains key ="+key);
        } else {
            System.out.println("key "+ key +" does'not exist");
        }
    }
    public static void printTrie(TrieNode root, String str) {
        TrieNode temp = root;
        if(temp == null) return;
        
        for(Map.Entry<Character, TrieNode> entry : temp.child.entrySet()){
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
}
class TrieNode {
    HashMap<Character,TrieNode> child;
    boolean isLeaf = false;
}
