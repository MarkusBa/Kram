CREATE TABLE `Prefixes` (
  `prefix` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `uri` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`prefix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Nodes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hash` bigint(20) NOT NULL DEFAULT '0',
  `lex` longtext CHARACTER SET utf8 COLLATE utf8_bin,
  `lang` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `datatype` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `type` int(10) unsigned NOT NULL DEFAULT '0',
    `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Hash` (`hash`)
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=utf8;

CREATE TABLE `Quads` (
  `ts`  TIMESTAMP NOT NULL,
  `s` int(11) NOT NULL,
  `p` int(11) NOT NULL,
  `o` int(11) NOT NULL,
  PRIMARY KEY (`ts`,`s`,`p`,`o`),
  KEY `SubjPredObj` (`s`,`p`,`o`),
  KEY `PredObjSubj` (`p`,`o`,`s`),
  KEY `ObjSubjPred` (`o`,`s`,`p`),
  KEY `GraPredObj` (`ts`,`p`,`o`),
  KEY `GraObjSubj` (`ts`,`o`,`s`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1; 


CREATE TABLE `Prefixes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,	
  `prefix` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `uri` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;