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

var clientCount = (function(){

})();

module.exports = {
	dbTest : function(db){
		db.each("SELECT * FROM Guardian", function(err, row){
			console.log(row);	
		});		
	},

	getConnection : function(){
		return new sqlite3.Database(file);
	}, 

	isChild : function(id){

	},
	
	fellowship : {		
		create : function(name, creator_phone_num, callback){

			var db = module.exports.getConnection();
			var stmt = db.prepare("INSERT INTO Fellowship VALUES (?,?,?)");			

			stmt.run([null, name, creator_phone_num], function(){
				db.get("SELECT last_insert_rowid()", function(err, row){				
					callback(row["last_insert_rowid()"]);
				});	
			});		

			stmt.finalize();
			db.close();	
		},

		delete : function(fellowship_id){
				
			var db = module.exports.getConnection();
			db.run("DELETE FROM Fellowship WHERE id = " + fellowship_id);

			db.close();
			//return void
		},

		getAll : function(callback){
			var db = module.exports.getConnection();

			db.each("SELECT * FROM Fellowship", function(err, row){				
				callback(row);		
			});

			db.close();
		},

		get : function(fellowship_id, callback){
			var db = module.exports.getConnection();

			db.get("SELECT * FROM Fellowship WHERE id = " + fellowship_id, function(err, row){				
				callback(row);
				//return phonenumber, fname, sname
			});

			db.close();

			//return name, list of children, list of parents
		},
		join : function(client_id, fellowship_id, callback){
			var db = module.exports.getConnection();	


			//return success boolean
			
		},	
		addJourney : function(journey_id, fellowship_id){
			//return void
			//assume journey already created
			var db = module.exports.getConnection();
			db.run("UPDATE Journey SET fellowship = " + fellowship_id + " WHERE id = " + journey_id);
			db.close();
		},
		deleteJourney : function(journey_id, fellowship_ip){
			//return void
			var db = module.exports.getConnection();
			db.run("UPDATE Journey SET fellowship = null WHERE id = " + journey_id)
			db.close();
		}	
	},	

	guardian : {
		create : function(fname, sname, phone_num){

			var db = module.exports.getConnection();
			var stmt = db.prepare("INSERT INTO Guardian VALUES (?,?,?)");
			stmt.run([parseInt(phone_num), fname, sname]);
			stmt.finalize();
			//return void
		},
		login : function(phone_num){
			//return void
		},
		get : function(phone_num, callback){
			
			var db = module.exports.getConnection();

			db.each("SELECT * FROM Guardian WHERE phone = " + phone_num, function(err, row){
				callback(row);
				//return phonenumber, fname, sname
			});

			db.close();
		},
		auth : function(client_id){
			//allowing child to join a group
			var db = module.exports.getConnection();

			var stmt = db.prepare("INSERT INTO ChildFellowshipAuth VALUES (?,?)");

			db.get("SELECT phone FROM Client INNER JOIN Guardian On Client.guardianPhone = Guardian.phone WHERE id = " + client_id, function(err, row){
				stmt.run([client_id, row['phone']]);
			});
			

			//return nothing
		},
		getAll : function(callback){
			var db = module.exports.getConnection();

			db.each("SELECT * FROM Guardian", function(err, row){				
				callback(row);		
			});

			db.close();
		}
	},

	client: {
		create : function(fname, sname, age, guardian_phone, callback){
			var db = module.exports.getConnection()
			
			var stmt = db.prepare("INSERT INTO Client VALUES (?,?,?,?,?,?)");	
			stmt.run([null, age, fname, sname, guardian_phone, null], function(err, row){
				db.get("SELECT last_insert_rowid() FROM Client", function(e, r){				
					callback(r["last_insert_rowid()"]);
				});	
			});

			stmt.finalize();
			
			db.close();

			//return id
		},
		login : function(client_id){
			//return nothing
		},
		getAll : function(callback)
		{
			var db = module.exports.getConnection();

			db.each("SELECT * FROM Client", function(err, row){				
				callback(row);		
			});

			db.close();
		}
	},

	journey : {
		create : function(fellowship_id, supervisorPhone, start_loc, end_loc, start_time, end_time, callback){
			var db = module.exports.getConnection()
			var stmt = db.prepare("INSERT INTO Journey VALUES (?,?,?,?,?,?,?,?,?)");	

			var vals = [null, null, supervisorPhone, start_time, end_time, start_loc[0], start_loc[1], end_loc[0], end_loc[1]];
			stmt.run(vals, function(err, row){
				db.get("SELECT last_insert_rowid() FROM Journey", function(e, r){				
					callback(r["last_insert_rowid()"]);
				});	
			});
			

			stmt.finalize();
			
			db.close();


			//return journey_id
		},
		start : function(journey_id, time, children_present){
			var db = module.exports.getConnection();

			db.run("UPDATE Journey SET startTime = " + time + " WHERE id = " + journey_id);

			db.close()
		},
		end : function(journey_id, time){
			var db = module.exports.getConnection();

			db.run("UPDATE Journey SET endTime = " + time + " WHERE id = " + journey_id);

			db.close()
		},
		getAll : function(callback){
			var db = module.exports.getConnection();

			db.each("SELECT * FROM Journey", function(err, row){				
				callback(row);		
			});

			db.close();
		}
	},

	createTables : function()
	{
		var db = module.exports.getConnection();

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
				+ "id integer PRIMARY KEY AUTOINCREMENT,"
				+ "name TEXT NOT NULL,"
				+ "creatorPhone integer NOT NULL,"
				+ "FOREIGN KEY (creatorPhone) REFERENCES Guardian (phone)" 		
				+ ");"
			);	

			//Client
			db.run("CREATE TABLE if not exists Client("
				+ "id integer PRIMARY KEY AUTOINCREMENT,"
				+ "age integer NOT NULL CHECK(age > 0),"
				+ "fname TEXT NOT NULL,"
				+ "sname TEXT NOT NULL,"
				+ "guardianPhone integer NOT NULL,"
				+ "fellowship integer,"
				+ "FOREIGN KEY (guardianPhone) REFERENCES Guardian (phone)," 		
				+ "FOREIGN KEY (fellowship) REFERENCES Fellowship (id)"
				+ ");"
			);				

			//Journey
			db.run("CREATE TABLE if not exists Journey("
				+ "id integer PRIMARY KEY AUTOINCREMENT,"			
				+ "fellowship integer,"
				+ "supervisorPhone integer NOT NULL,"
				+ "startTime integer NOT NULL CHECK(startTime < 2400 AND startTime > -1),"
				+ "endTime integer CHECK(endTime < 2400 AND endTime > -1),"
				+ "startLatitude real CHECK(startLatitude < 90.0 AND startLatitude > -90.0),"
				+ "endLatitude real CHECK(endLatitude < 90.0 AND endLatitude > -90.0),"
				+ "startLongitude real CHECK(startLongitude < 80.0 AND startLongitude > -80.0),"
				+ "endLongitude real CHECK(endLongitude < 80.0 AND endLongitude > -80.0),"
				+ "FOREIGN KEY (fellowship) REFERENCES Fellowship (id),"
				+ "FOREIGN KEY (supervisorPhone) REFERENCES Guardian (phone)" 		
				+ ");"
			);

			//ChildFellowshipAuth
			db.run("CREATE TABLE if not exists ChildFellowshipAuth("
				+ "child integer NOT NULL,"
				+ "guardianPhone integer NOT NULL,"
				+ "PRIMARY KEY (child, guardianPhone),"
				+ "FOREIGN KEY (child) REFERENCES Client(id),"
				+ "FOREIGN KEY (guardianPhone) REFERENCES Guardian(phone)"
				+ ");"
			);
		});

		db.close();
	}	
}