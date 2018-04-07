/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import graph.ConcreteEdgesGraph;
import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus(语料库) of text, which it uses to derive（起源，得自） a
 * word affinity（密切联系） graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited（划清界限） in the corpus by spaces, newlines（换行符？？？）, or the ends of the file.
 * Edges in the graph count adjacencies（临界）: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates(使形成，使发生) a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight（最大的权值） weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
  //  private  ConcreteEdgesGraph<String> graph1=new ConcreteEdgesGraph<String>();
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {

        FileInputStream fileInputStream=new FileInputStream(corpus);
        Scanner scanner=new Scanner(fileInputStream);
        String line="";
        while (scanner.hasNext()){
            line+=scanner.nextLine();
        }
    //    String line=scanner.nextLine(); //存储字符串
        Set<String> words=new HashSet<>();
        String[] strings=line.split(" ");
        for (String string:strings
             ) {
            words.add(string.toLowerCase());
        }
        for (String word :words
             ) {
            graph.add(word);
        }
        Map<String,Integer> countOfWords=null;
        for (String word:words
             ) {
            int i=0;
            for (int j = 0; j <strings.length ; j++) {
                if (strings[j].toLowerCase().equals(word)){
                    i=j;//找到每一个单词的起始位置
                    break;
                }
            }
            countOfWords=new HashMap<>();//对于每个字符串，都要有一个map
            for (int j = i+1; j <strings.length ; j++) {
                if(!countOfWords.containsKey(strings[j].toLowerCase())){
                    countOfWords.put(strings[j].toLowerCase(),1);
                }else {
                    int a=countOfWords.get(strings[j].toLowerCase());
                    countOfWords.replace(strings[j].toLowerCase(),a+1);
                }
            }
            for (String target: countOfWords.keySet()
                 ) {
                graph.set(strings[i].toLowerCase(),target,countOfWords.get(target));
            }
        }
  //      System.out.println("success");
    }
    
    // TODO checkRep
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String[] inputWords=input.split(" ");
     //   Set<String> stringSetLower=new HashSet<>();
        List<String> stringListLower=new ArrayList<>();
        List<String> stringList=new LinkedList<>();//专门用于插入单词
        for (String word:inputWords
             ) {
            stringListLower.add(word.toLowerCase());
            stringList.add(word);
           // stringSetLower.add(word.toLowerCase());
        }
        int index=0;
        for (int i = 0; i <stringListLower.size()-1 ; i++) {
            String source=stringListLower.get(i);
            String target=stringListLower.get(i+1);
            String bridge=findMax(source,target);
            if (bridge==null) continue;
            index=findIndex(stringList,source);
            stringList.add(index+1,bridge);//插入到了index的前面
        }
        String result="";
        for (String str:stringList
             ) {
            result+=str;
            result+=" ";
        }
        //throw new RuntimeException("not implemented");
        return result.trim();
    }
    private int findIndex(List<String> list,String source){
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).toLowerCase().equals(source)){
                return i;
            }
        }
        return -1;
    }
    private boolean judgeStringInSet(Set<String> set,String word){
        for (String str:set
             ) {
            if (str.equals(word)){
                return true;
            }
        }
        return false;
    }
    private String findMax(String source,String target){
        Map<String,Integer> sources=graph.sources(target);
        Map<String,Integer> targets=graph.targets(source);
        Map<String ,Integer> common=new HashMap<>();//寻找相同临界的顶点
        String result=null;
        for (String word:targets.keySet()
                ) {
            if (judgeStringInSet(sources.keySet(),word)){//这一行有问题
                common.put(word,sources.get(word)+targets.get(word));//保存的键值为二者之和
            }
        }
        int max=0;
        int numberOfMax=1;
        for (String word:common.keySet()
                ) {
            if(common.get(word)==max){//向进行这一段判断
                numberOfMax++;
            }
            if(common.get(word)>max){
                max=common.get(word);
                result=word;
                numberOfMax=1;
            }
        }
        if (max==0||numberOfMax>=2){return null;}//如果没有一条路径，或者是有多条路径，就相当于是没有路。
        return result;
      //  return (common.get(result)==1||numberOfMax!=1)?null:result;//返回对应权值最大的字符串,如果最大权值为1，那么什么也不返回

    }
    
    // TODO toString()
    
}
