package cz.fi.muni.pa165.yellow_yak.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/***
 * Player DTO Mixin
 */
@JsonIgnoreProperties({ "passwordHash"})
public class PlayerDTOMixin {
    
}
