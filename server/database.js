var fs = require("fs");
var file = "text.db";

var exists = fs.existsSync(file);

var sqlite3 = require("sqlite3").verbose();
var db = new sqlite3.Database(file);

db.serialize(function(){	

	if(!exists){
		createTables();		
	}
});

db.close();

var tables = [
	"Client" ,
 	"Parent",
 	"Group",
 	"Journey",
 	"ChildGroupAuth"
 ];

var dropTables = function()
{
	for(var i = 0; i < tables.length; i++)
	{
		db.run("DROP TABLE " + tables[i]);	
	}	
}

var createTables = function()
{
	//Consider ON DELETE CASCADE for foreign keys

	//Client
	db.run("CREATE TABLE Client("
		+ "id integer PRIMARY KEY,"
		+ "fname TEXT NOT NULL,"
		+ "sname TEXT NOT NULL,"
		+ "parent integer NOT NULL,"
		+ "group integer,"
		+ "FOREIGN KEY (parent) REFERENCES Parent (id)," 		
		+ "FOREIGN KEY (group) REFERENCES Group (id)"
		+ ");";
	);

	//Parent
	db.run("CREATE TABLE Parent("
		+ "phone integer PRIMARY KEY,"
		+ "fname TEXT NOT NULL,"
		+ "sname TEXT NOT NULL,"
		+ "child integer NOT NULL,"
		+ "FOREIGN KEY (child) REFERENCES Client (id)" 		
		+ ");";
	);	

	//Group
	db.run("CREATE TABLE Group("
		+ "id integer PRIMARY KEY,"
		+ "name TEXT NOT NULL,"
		+ "creator integer NOT NULL,"
		+ "FOREIGN KEY (creator) REFERENCES Parent (id)" 		
		+ ");"
	);	

	//Journey
	db.run("CREATE TABLE Journey("
		+ "id integer PRIMARY KEY,"
		+ "name TEXT NOT NULL,"
		+ "group integer NOT NULL,"
		+ "supervisor integer NOT NULL,"
		+ "startTime integer NOT NULL CHECK(startTime < 2400 AND startTime > -1),"
		+ "endTime integer CHECK(endTime < 2400 AND endTime > -1),"
		+ "startLatitude real CHECK(startLatitude < 90.0 AND startLatitude > -90.0),"
		+ "endLatitude real CHECK(endLatitude < 90.0 AND endLatitude > -90.0),"
		+ "startLongitude real CHECK(startLongitude < 80.0 AND startLongitude > -80.0),"
		+ "endLongitude real CHECK(endLongitude < 80.0 AND endLongitude > -80.0),"
		+ "FOREIGN KEY (group) REFERENCES Group (id),"
		+ "FOREIGN KEY (supervisor) REFERENCES Parent (id)" 		
		+ ");"
	);

	//ChildGroupAuth
	db.run("CREATE TABLE ChildGroupAuth("
		+ "child integer NOT NULL,"
		+ "parent integer NOT NULL,"
		+ "PRIMARY KEY (child, parent),"
		+ "FOREIGN KEY (child) REFERENCES Client(id),"
		+ "FOREIGN KEY (parent) REFERENCES Parent(id)"
		+ ");"
	);
}

module.exports = {
	group : {
		create : function(){
			/*
			var stmt = db.prepare("INSERT INTO Client VALUES (?)");	
			stmt.run("ColumnName #data")

			stmt.finalize();
			*/

			return "Hello!";
		},
		display : function(){},	
	},	

	client: {
		refresh : function(){}
	}
}