package cz.fi.muni.pa165.yellow_yak.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "passwordHash"})
public class PlayerDTOMixin {
    
}
