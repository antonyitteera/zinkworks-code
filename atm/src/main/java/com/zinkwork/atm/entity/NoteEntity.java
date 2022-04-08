package com.zinkwork.atm.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Note")
public class NoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native",strategy = "native")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "atmEntity_id",nullable = false)
	private AtmEntity atmEntity;
	private Integer note;
	private Integer count;
	
	public NoteEntity(Integer note, Integer count) {
		this.note=note;
		this.count=count;
	}
	
}
