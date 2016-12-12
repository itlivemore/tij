package org.lgc.tij.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Snow{}
class Powder extends Snow{}
class Light extends Powder{}
class Heavy extends Powder{}
class Crusty extends Snow{}
class Slush extends Snow{}
/**Arrays.asList有关的问题
 * Created by lgc on 16-12-12.
 */
public class AsListInference {
    public static void main(String[] args) {
        List<Snow> snow1 = Arrays.asList(new Crusty(), new Slush(), new Powder());
        // 不能编译，Arrays.asList中只有Powder类型，因此它会创建List<Powder>而不是List<Snow>
//        List<Snow> snow2 = Arrays.asList(new Light(), new Heavy());
        List<Powder> snow2 = Arrays.asList(new Light(), new Heavy());

        List<Snow> snow3 = new ArrayList<Snow>();
        // 与上面Arrays.asList相比，Collections.addAll可以将Light和Heavy对象添加
        Collections.addAll(snow3, new Light(), new Heavy());

        // 可以在Arrays.asList中间插入一条“线索”，以告诉编译器对于由Arrays.asList产生的List类型，实际的目标类型该是什么。
        // 这称为显示类型参数说明
        List<Snow> snows4 = Arrays.<Snow>asList(new Light(), new Heavy());


    }
}
