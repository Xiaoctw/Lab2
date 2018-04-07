package poet;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class testInput {
    @Test
    public void test1() throws IOException {
        File file=new File("D:\\ideaproject\\PoeticWalk\\src\\poet\\mugar-omni-theater.txt");
        GraphPoet graphPoet=new GraphPoet(file);
    }
}
