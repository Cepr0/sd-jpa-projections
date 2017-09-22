package io.github.cepr0.projections.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PROTECTED;

/**
 * @author Sergei Poznanski, 2017-09-22
 */
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Entity
public class Profile extends BaseEntity {
	
	@NotNull
	private String data;
}
