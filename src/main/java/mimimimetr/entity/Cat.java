package mimimimetr.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cat {

    private Long id;

    private String name;

    private String image;

    private String message;

}
