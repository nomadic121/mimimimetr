package mimimimetr.util;

import mimimimetr.entity.CatEntity;

import java.util.LinkedList;
import java.util.List;

public class CatsFabrik {

    private static int number = 1;

    public static CatEntity getCat() {
        if (number == 1) {
            number++;
            return CatEntity.builder()
                    .name("barsik")
                    .image("https://img.huffingtonpost.com/asset/5dcc613f1f00009304dee539.jpeg")
                    .message("meov")
                    .build();
        } else {
            number--;
            return CatEntity.builder()
                    .name("murzik")
                    .image("https://static.themoscowtimes.com/image/article_1360/11/portrait-of-a-cat.jpg")
                    .message("meov")
                    .build();
        }
    }

    public static List<CatEntity> getCatList() {
        CatEntity barsik = CatEntity.builder()
                .id(1L)
                .message("meov")
                .image("https://img.huffingtonpost.com/asset/5dcc613f1f00009304dee539.jpeg")
                .name("barsik")
                .build();
        CatEntity murzik = CatEntity.builder()
                .id(2L)
                .message("meov")
                .image("https://static.themoscowtimes.com/image/article_1360/11/portrait-of-a-cat.jpg")
                .name("murzik")
                .build();
        List<CatEntity> catEntities = new LinkedList<CatEntity>() {
            {
                add(barsik);
                add(murzik);
            }
        };
        return catEntities;
    }

}
