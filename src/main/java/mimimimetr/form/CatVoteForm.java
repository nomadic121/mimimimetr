package mimimimetr.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import mimimimetr.dto.CatDto;

@Data
@AllArgsConstructor
public class CatVoteForm {

    private CatDto cat1;

    private CatDto cat2;

}
