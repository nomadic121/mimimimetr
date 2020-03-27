package mimimimetr.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import mimimimetr.dto.CatDto;

@Data
@AllArgsConstructor
public class CatResultForm {

    private CatDto catDto;

    private Long voteCount;

}
