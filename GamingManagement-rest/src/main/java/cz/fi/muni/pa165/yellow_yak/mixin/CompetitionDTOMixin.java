package cz.fi.muni.pa165.yellow_yak.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class shows an example of Jackson mix-ins in case we do not want to modify one DTO
 * in the API layer
 */
@JsonIgnoreProperties({ "imageMimeType", "image" })
public abstract class CompetitionDTOMixin {
}
