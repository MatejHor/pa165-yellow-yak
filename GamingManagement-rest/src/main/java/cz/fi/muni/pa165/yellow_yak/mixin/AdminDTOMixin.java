package cz.fi.muni.pa165.yellow_yak.mixin;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminDTOMixin {
    private String email;
    private String password;
}
