package com.evn.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.Nullable;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@Entity
@RequiredArgsConstructor
public class Configuration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idConfiguration;
	private String subject;
	private String creator;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateCreate;
	private float price;
	@Nullable
	private String editor;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Nullable
	private Date dateEdit;
}
