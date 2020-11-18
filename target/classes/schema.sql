drop table if exists address_book;
create table IF NOT EXISTS address_book (
	id bigint,
	name varchar(50) not null,
	phn_number varchar(10) not null,
	primary key (id)
);
