package mimimimetr.util;

import mimimimetr.entity.Cat;

import java.util.LinkedList;
import java.util.List;

public class CatsFabrik {

    public static List<Cat> getCatList() {
        Cat barsik = Cat.builder()
                .id(1L)
                .message("meov")
                .image("https://img.huffingtonpost.com/asset/5dcc613f1f00009304dee539.jpeg")
                .name("barsik")
                .build();
        Cat murzik = Cat.builder()
                .id(2L)
                .message("meov")
                .image("https://static.themoscowtimes.com/image/article_1360/11/portrait-of-a-cat.jpg")
                .name("murzik")
                .build();
        List<Cat> cats = new LinkedList<Cat>() {
            {
                add(barsik);
                add(murzik);
            }
        };
        return cats;
    }
}
