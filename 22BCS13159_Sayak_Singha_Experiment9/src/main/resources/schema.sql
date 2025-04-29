CREATE TABLE IF NOT EXISTS tbl_APP (
		_id BIGINT NOT NULL,
		_name TEXT(250) NOT NULL,
	    _author TEXT(250) NOT NULL,
		_auth_tag TEXT NOT NULL,
		_size INT NOT NULL,
		_valid BOOLEAN,
		_date_added DATE,
		PRIMARY KEY(_id)
);