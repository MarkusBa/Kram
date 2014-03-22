package de.bort.entities;


import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;  
import javax.persistence.Table;  
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
  
@Entity  
@Table(name="Quads")
public class Quads {  
      
	@Id
	@GeneratedValue
	private Integer id;

	private Integer s;
	private Integer p;
	private Integer o;
	private Date ts;

	public Quads() {
	}

	@Column(name = "ts")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	@Column(name = "s")
	public Integer getS() {
		return s;
	}

	public void setS(Integer s) {
		this.s = s;
	}

	@Column(name = "p")
	public Integer getP() {
		return p;
	}

	public void setP(Integer p) {
		this.p = p;
	}

	@Column(name = "o")
	public Integer getO() {
		return o;
	}

	public void setO(Integer o) {
		this.o = o;
	}



      

      
        //Here you need to generate getters and setters  
  
}  

//| Nodes | CREATE TABLE `Nodes` (
//		  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
//		  `hash` bigint(20) NOT NULL DEFAULT '0',
//		  `lex` longtext CHARACTER SET utf8 COLLATE utf8_bin,
//		  `lang` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
//		  `datatype` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
//		  `type` int(10) unsigned NOT NULL DEFAULT '0',
//		  PRIMARY KEY (`id`),
//		  UNIQUE KEY `Hash` (`hash`)
//		) ENGINE=InnoDB DEFAULT CHARSET=utf8 |
