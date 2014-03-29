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
@Table(name="Prefixes")
public class Prefixes {  
      
	@Id
	@GeneratedValue
	private Integer id;

	private String prefix;
	private String uri;
	private Date ts;
        private boolean deleted;

  
	public Prefixes() {
	}

	public Prefixes(String uri, String prefix){
        	this.prefix = prefix;
       		this.uri = uri;
    	}

    @Column(name = "deleted")
    public boolean getDeleted(){
        return deleted;
    }

    public void setDeleted(boolean deleted1){
        deleted = deleted1;
    }

  
	@Column(name = "ts")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	@Column(name = "prefix")
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(name = "uri")
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

        @Override
    	public String toString(){
    		return id.toString() + " " + prefix + " " + uri;
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
