package mimimimetr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatResultsDto {

    private Long id;

    private String name;

    private String image;

    private int voteCount;

}
