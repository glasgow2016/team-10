var fs = require("fs");
var file = "text.db";

var exists = fs.existsSync(file);

var sqlite3 = require("sqlite3").verbose();

var tables = [
	"Client" ,
 	"Guardian",
 	"Fellowship",
 	"Journey",
 	"ChildFellowshipAuth"
 ];

var dbTest = function(db){
	var x = db.run("SELECT * FROM Client");
	console.log(x);
}

module.exports = {

	getConnection : function(){
		return new sqlite3.Database(file);
	}, 

	disconnect : function(db){
		db.close()
	},
	
	group : {
		
		create : function(){
			var db = this.getConnection()
			/*
			var stmt = db.prepare("INSERT INTO Client VALUES (?)");	
			stmt.run("ColumnName #data")

			stmt.finalize();
			*/
			this.disconnect(db);
			
		},
		display : function(){
			/*
			module.exports.clients.push("Hello");
			
			return module.exports.clients;
			*/
		},
		join : function(){},	
		addJourney: function(){}
	},	

	client: {
		refresh : function(){},	
		create : function(name, age, parentPhoneNum){}
	},

	journey : {
		create : function(){}
	},


	createTables : function()
	{
		var db = this.getConnection();

		db.serialize(function(){	
			//Consider ON DELETE CASCADE for foreign keys
			//db.run("DROP TABLE Test");
			
			//Guardian
			db.run("CREATE TABLE if not exists Guardian("
				+ "phone integer PRIMARY KEY,"
				+ "fname TEXT NOT NULL,"
				+ "sname TEXT NOT NULL"	
				+ ");"
			);	

			//Fellowship
			db.run("CREATE TABLE if not exists Fellowship("
				+ "id integer PRIMARY KEY,"
				+ "name TEXT NOT NULL,"
				+ "creator integer NOT NULL,"
				+ "FOREIGN KEY (creator) REFERENCES Guardian (phone)" 		
				+ ");"
			);	

			//Client
			db.run("CREATE TABLE if not exists Client("
				+ "id integer PRIMARY KEY,"
				+ "fname TEXT NOT NULL,"
				+ "sname TEXT NOT NULL,"
				+ "guardian integer NOT NULL,"
				+ "fellowship integer,"
				+ "FOREIGN KEY (guardian) REFERENCES Guardian (phone)," 		
				+ "FOREIGN KEY (fellowship) REFERENCES Fellowship (id)"
				+ ");"
			);				

			//Journey
			db.run("CREATE TABLE if not exists Journey("
				+ "id integer PRIMARY KEY,"
				+ "name TEXT NOT NULL,"
				+ "fellowship integer NOT NULL,"
				+ "supervisor integer NOT NULL,"
				+ "startTime integer NOT NULL CHECK(startTime < 2400 AND startTime > -1),"
				+ "endTime integer CHECK(endTime < 2400 AND endTime > -1),"
				+ "startLatitude real CHECK(startLatitude < 90.0 AND startLatitude > -90.0),"
				+ "endLatitude real CHECK(endLatitude < 90.0 AND endLatitude > -90.0),"
				+ "startLongitude real CHECK(startLongitude < 80.0 AND startLongitude > -80.0),"
				+ "endLongitude real CHECK(endLongitude < 80.0 AND endLongitude > -80.0),"
				+ "FOREIGN KEY (fellowship) REFERENCES Fellowship (id),"
				+ "FOREIGN KEY (supervisor) REFERENCES Guardian (phone)" 		
				+ ");"
			);

			//ChildGroupAuth
			db.run("CREATE TABLE if not exists ChildFellowshipAuth("
				+ "child integer NOT NULL,"
				+ "guardian integer NOT NULL,"
				+ "PRIMARY KEY (child, guardian),"
				+ "FOREIGN KEY (child) REFERENCES Client(id),"
				+ "FOREIGN KEY (guardian) REFERENCES Guardian(phone)"
				+ ");"
			);
		});

		this.disconnect(db);
	}	
}

