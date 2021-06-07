package cz.fi.muni.pa165.yellow_yak.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Competition DTO Mixin
 */
@JsonIgnoreProperties({ "imageMimeType", "image" })
public abstract class CompetitionDTOMixin {
}
