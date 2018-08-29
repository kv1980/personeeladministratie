insert into werknemers(familienaam,voornaam,email,jobtitelid,salaris,paswoord,geboorte,rijksregisternr,versie)
VALUES ('testchef','testchef','testchef@vdab.be',(select id from jobtitels where naam='President'),3000,'testchef','1970-01-01',70010100188,0);

insert into werknemers(familienaam,voornaam,email,chefid,jobtitelid,salaris,paswoord,geboorte,rijksregisternr,versie)
VALUES ('testondergeschikte1','testondergeschikte1','testondergeschikte1@vdab.be',(select id from werknemers where familienaam='testchef'),(select id from jobtitels where naam='Manager'),2000,'testtestondergeschikte1','1980-01-01',80010100107,0),
	   ('testondergeschikte2','testondergeschikte2','testondergeschikte2@vdab.be',(select id from werknemers where familienaam='testchef'),(select id from jobtitels where naam='Manager'),2000,'testtestondergeschikte2','1990-01-01',80010100123,0);