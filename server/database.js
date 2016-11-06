var fs = require("fs");
var file = "text.db";
var sqlite3 = require("sqlite3").verbose();

module.exports = {

	//return an instance of the database
	getConnection : function(){
		return new sqlite3.Database(file);
	}, 
	
	fellowship : {		
		//create a group and add it to the database
		create : function(name, creator_phone_num, callback){

			var db = module.exports.getConnection();
			var stmt = db.prepare("INSERT INTO Fellowship VALUES (?,?,?,?)");			

			stmt.run([null, name, creator_phone_num, 0], function(){
				db.get("SELECT last_insert_rowid()", function(err, row){				
					callback(row["last_insert_rowid()"]);
				});	
			});		

			stmt.finalize();
			db.close();	
		},

		//remove a single group
		delete : function(fellowship_id){
				
			var db = module.exports.getConnection();
			db.run("DELETE FROM Fellowship WHERE id = " + fellowship_id);

			db.close();
			//return void
		},

		//return all of the groups in the database
		getAll : function(callback){
			var db = module.exports.getConnection();

			db.each("SELECT * FROM Fellowship", function(err, row){				
				callback(row);		
			});

			db.close();
		},

		//return a group's details
		get : function(fellowship_id, callback){
			var db = module.exports.getConnection();

			db.all("SELECT * FROM Fellowship INNER JOIN Client ON Client.fellowship = Fellowship.id WHERE Fellowship.id = " + fellowship_id, function(e, r){
		
				ret = {};
				ret.clients = [];
				ret.name = r[0].name;
				ret.points = r[0].points;
									
				r.forEach(function(n){
					ret.clients.push(n.id);
				});

				callback(ret);				
			});
				
			db.close();

			//return name, list of children, list of parents
		},

		getFirst : function(callback){
			var db = module.exports.getConnection();

			db.all("SELECT * FROM Fellowship", function(err, rows){
				callback(rows[0].id);
			});
		},

		//alllow a child to join the group
		join : function(client_id, fellowship_id, callback){
			var db = module.exports.getConnection();	

			db.run("UPDATE Client SET fellowship = " + fellowship_id + " WHERE Client.id = " + client_id, function(err, row){
				callback(true);
			});
			db.close();
			//return success boolean
			
		},	

		//add an existing journey to the group
		addJourney : function(journey_id, fellowship_id){
			//return void
			//assume journey already created
			var db = module.exports.getConnection();
			db.run("UPDATE Journey SET fellowship = " + fellowship_id + " WHERE id = " + journey_id);
			db.close();
		},
		//remove a journey from the group
		deleteJourney : function(journey_id, fellowship_id){
			//return void
			var db = module.exports.getConnection();
			db.run("UPDATE Journey SET fellowship = null WHERE id = " + journey_id)
			db.close();
		},

		addPoints : function(fellowship_id, points){
			var db = module.exports.getConnection();
			db.run("UPDATE Fellowship SET points = points  + " + points + " WHERE id = " + fellowship_id);
			db.close();
		}
	},	

	//aka parent
	guardian : {
		//create an instance of a parent and add it to the database
		create : function(fname, sname, phone_num){

			var db = module.exports.getConnection();
			var stmt = db.prepare("INSERT INTO Guardian VALUES (?,?,?)");
			stmt.run([parseInt(phone_num), fname, sname]);
			stmt.finalize();
			//return void as we already know guardian primary key
		},

		//dummy login function
		login : function(phone_num){
			//return void
		},

		//get a specific parent by their phone number
		get : function(phone_num, callback){
			
			var db = module.exports.getConnection();

			db.each("SELECT * FROM Guardian WHERE phone = " + phone_num, function(err, row){
				callback(row);				
			});

			db.close();
		},

		//allow a child to join a group
		auth : function(client_id){
			//allowing child to join a group
			var db = module.exports.getConnection();

			var stmt = db.prepare("INSERT INTO ChildFellowshipAuth VALUES (?,?)");

			db.get("SELECT phone FROM Client INNER JOIN Guardian On Client.guardianPhone = Guardian.phone WHERE id = " + client_id, function(err, row){
				stmt.run([client_id, row['phone']]);
			});			

			//return nothing
		},

		//return all of the parents in the database
		getAll : function(callback){
			var db = module.exports.getConnection();

			db.each("SELECT * FROM Guardian", function(err, row){				
				callback(row);		
			});

			db.close();
		}
	},

	//aka child
	client: {
	
		//create a child and add to the database
		//returns the child's ID
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
		},

		getPoints : function(client_id, callback){
			var db = module.exports.getConnection();

			db.get("SELECT points FROM Client WHERE id = " + client_id, function(err, row){
				callback(row);
			});

		},

		//dummy login functionality
		login : function(client_id){
			//return nothing
		},

		//get all of the children
		getAll : function(callback)
		{
			var db = module.exports.getConnection();

			db.each("SELECT * FROM Client", function(err, row){				
				callback(row);		
			});

			db.close();
		},
		addPoints : function (client_id, points){
			var db = module.exports.getConnection();
			db.run("UPDATE Client SET points = points  + " + points + " WHERE id = " + client_id);
			db.close();
		}		
	},

	journey : {

		//Creates a journey in the database
		//returns journey ID
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
		},

		//timestamps the start of a journey
		start : function(journey_id, time, children_present){
			var db = module.exports.getConnection();

			db.run("UPDATE Journey SET startTime = " + time + " WHERE id = " + journey_id);

			db.close()
		},

		//timestamps the end of a journey
		end : function(journey_id, time){
			var db = module.exports.getConnection();

			db.run("UPDATE Journey SET endTime = " + time + " WHERE id = " + journey_id);

			db.close();
		},

		//returns all of the journeys for a given fellowship
		get : function(fellowship_id, callback){
			var db = module.exports.getConnection();			
			db.all("SELECT * FROM Journey WHERE Journey.fellowship = " + fellowship_id, function(err, rows){
				callback(rows);
			});
		},

		//return all journeys
		getAll : function(callback){
			var db = module.exports.getConnection();

			db.each("SELECT * FROM Journey", function(err, row){				
				callback(row);		
			});

			db.close();
		}
	},

	//scripts for creating all of the database tables
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
				+ "points integer DEFAULT (0),"
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
				+ "points integer DEFAULT (0),"
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