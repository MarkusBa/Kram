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
@Table(name="Nodes")
public class Nodes {  
      
    @Id  
    @GeneratedValue  
    private Integer id;  
    
    private BigInteger hash;
    private String lex;
    private String lang;
    private String datatype;
    private Integer type; 
    private Date ts;
      
    public Nodes() {}

    @Column(name="hash")
	public BigInteger getHash() {
		return hash;
	}

	public void setHash(BigInteger hash) {
		this.hash = hash;
	}

    @Column(name="lex")
	public String getLex() {
		return lex;
	}

	public void setLex(String lex) {
		this.lex = lex;
	}

    @Column(name="lang")
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

    @Column(name="datatype")
	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

    @Column(name="type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

  @Column(name="ts")
  @Temporal(TemporalType.TIMESTAMP)
	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
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
