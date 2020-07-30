package per.cc.test;

import java.util.*;

class TireNode{
    public Map<String, TireNode> children = new HashMap<>();
    public boolean isFile;
    public String fileContent;
    public String id;

    public TireNode(String id, boolean isFile, String fileContent){
        this.id = id;
        this.isFile = isFile;
        this.fileContent = fileContent;
    }

    public void append(String content){
        if(isFile){
            if(fileContent != null){
                fileContent += content;
            }else{
                fileContent = content;
            }
        }
    }
}

public class FileSystem {

    TireNode root;

    public FileSystem() {
        root = new TireNode("/", false, null);
    }

    public List<String> ls(String path) {
        String [] nodes = path.split("/");
        TireNode cur = root;
        for(String n : nodes){
            if(cur.children.containsKey(n)){
                cur = cur.children.get(n);
            }else{
                return null;
            }
        }
        if(cur.isFile){
            return Arrays.asList(cur.id);
        }else{
            List<String> res = new ArrayList<>(cur.children.keySet());
            System.out.println(res.size());
            // Collections.sort(res);
            return res;
        }
    }

    public void mkdir(String path) {
        String [] nodes = path.split("/");
        if(nodes.length < 1){
            return;
        }
        TireNode cur = root;
        for(String n : nodes){
            if(cur.children.containsKey(n)){
                cur = cur.children.get(n);
            }else{
                TireNode t = new TireNode(n, false, null);
                cur.children.put(n, t);
                cur = t;
            }
        }
    }

    public void addContentToFile(String filePath, String content) {
        String [] nodes = filePath.split("/");
        if(nodes.length < 1){
            return;
        }
        TireNode cur = root;
        for(int i = 0; i < nodes.length - 1; i++){
            if(cur.children.containsKey(nodes[i])){
                cur = cur.children.get(nodes[i]);
            }else{
                TireNode t = new TireNode(nodes[i], false, null);
                cur.children.put(nodes[i], t);
                cur = t;
            }
        }
        String fileName = nodes[nodes.length - 1];
        if(cur.children.containsKey(fileName)){
            cur = cur.children.get(fileName);
            cur.append(content);
        }else{
            TireNode t = new TireNode(fileName, true, content);
            cur.children.put(fileName, t);
        }
    }

    public String readContentFromFile(String filePath) {
        String [] nodes = filePath.split("/");
        if(nodes.length < 1){
            return null;
        }
        TireNode cur = root;
        for(String n : nodes){
            if(cur.children.containsKey(n)){
                cur = cur.children.get(n);
            }else{
                return null;
            }
        }
        if(!cur.isFile){
            return null;
        }else{
            return cur.fileContent;
        }
    }

    public static void main(String[] args) {
        FileSystem f = new FileSystem();
        f.mkdir("/a/b/c");
        List<String> ls = f.ls("/");
        ls.forEach(System.out::print);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */