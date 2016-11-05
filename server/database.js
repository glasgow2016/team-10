var fs = require("fs");
var file = "text.db";

var exists = fs.existsSync(file);

var sqlite3 = require("sqlite3").verbose();
var db = new sqlite3.Database(file);

db.serialize(function(){
	
	if(!exists){
		//createTables();
		//populateTables();
	}
});

db.close();

var createTables = function()
{
	//create tables on first run through
	db.run("CREATE TABLE Client");
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




